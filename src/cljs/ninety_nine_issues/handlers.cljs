(ns ninety-nine-issues.handlers
  (:require [re-frame.core :as r :refer [path trim-v]]
            [ninety-nine-issues.db :as db]

            [cljs.core.async :refer [<!]]
            [cljs-http.client :as http])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(r/register-handler
  :initialize-db
  (fn  [_ _]
    db/default-db))

(defn issue-obj [{:keys [title description created_at user]}]
  {:title title
   :description description
   :created_at created_at
   :avatars [(:avatar_url user)] ; TODO fetch full list
   :creator (:login user)})

(r/register-handler
  :search-issues
  [(path [:issues]) trim-v]
  (fn [old [language]]
    (go
      (let [response (<! (http/get
                           "https://api.github.com/search/issues?q=language:javascript+label:easy"
                           {:with-credentials? false}))
            responses (map issue-obj (-> response :body :items))

            #_{:title ""
               :description ""
               :stars 0
               :recent 3 ; number of hours since last commit
               :contribution-count 0
               :contributors 0 }
            #_{:title
               :description
               :maintainer?
               :avatars of participants
               :opened n days ago
               }

            ]
        ))
    nil))
