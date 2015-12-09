(ns ninety-nine-issues.handlers
    (:require [re-frame.core :as re-frame]
              [ninety-nine-issues.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))
