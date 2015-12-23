(ns ninety-nine-issues.handlers
    (:require [re-frame.core :as re-frame :refer [register-handler]]
              [ninety-nine-issues.db :as db]))

(register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(register-handler
  :select-language
  [re-frame/trim-v]
  (fn [db [lang]]
    (println lang)
    db))

