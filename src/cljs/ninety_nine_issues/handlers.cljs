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

(r/register-handler
  :search-issues
  [(path [:issues]) trim-v]
  (fn [old [language]]
    (go
      (let [response (<! (http/get
                           "https://api.github.com/search/issues?q=language:javascript+label:easy"
                           {:with-credentials? false}))]
        (println (:body response))))

    nil))

