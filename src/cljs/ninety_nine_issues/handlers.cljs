(ns ninety-nine-issues.handlers
    (:require [re-frame.core :as re-frame]
              [ninety-nine-issues.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/register-handler
 :swipe
 (fn [state event]
   ;; this is where we should change the state to reflect a
   ;; issue!
   (js/console.log "change app state " (last event))
   db/default-db))
