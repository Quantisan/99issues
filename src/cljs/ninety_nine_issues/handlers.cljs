(ns ninety-nine-issues.handlers
  (:require [re-frame.core :as re-frame :refer [dispatch register-handler]]
            [ninety-nine-issues.db :as db]

            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(defn set-hash!
  "Set the browser location hash"
  [loc]
  (set! (.-hash js/window.location) loc))

(register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(register-handler
  :set-active-page
  [(re-frame/path :active-page) re-frame/trim-v]
  (fn [_ [page]]
    page))

(register-handler
  :user-select-language
  [re-frame/trim-v]
  (fn [db [lang]]
    (set-hash! (str "/" (name lang)))

    db))

(register-handler
  :set-language
  [(re-frame/path :language) re-frame/trim-v]
  (fn [_ [language]]
    language))

(defn parse-issue-json [coll]
  (let [{:keys [html_url labels closed_at number milestone comments state title
                labels_url updated_at comments_url locked id score events_url
                url body user assignee created_at]} coll]
    {:title title 
     :body body}))

(register-handler
  :fetch-issues
  [re-frame/trim-v]
  (fn [db [language]]
    (go (let [response (<! (http/get "https://api.github.com/search/issues"
                                     {:query-params {"q" (str "language:" language " label:easy")}
                                      :with-credentials? false}))]
          (dispatch [:load-issues (->> response
                                       (:body)
                                       (:items)
                                       (js->clj)
                                       (map parse-issue-json))])))

    db))

(register-handler
  :load-issues
  [re-frame/trim-v]
  (fn [db [issues]]
    (-> db
        (assoc :issues (shuffle issues))
        ;; set loading? flag to false
        (assoc :loading? false))))

(register-handler
  :next-issue
  (fn [db _]
    (assoc db :issues (rest (:issues db)))))

