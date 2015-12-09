(ns ninety-nine-issues.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [ninety-nine-issues.handlers]
              [ninety-nine-issues.subs]
              [ninety-nine-issues.views :as views]
              [ninety-nine-issues.config :as config]))

(when config/debug?
  (println "dev mode"))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init [] 
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
