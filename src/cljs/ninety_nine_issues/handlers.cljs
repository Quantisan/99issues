(ns ninety-nine-issues.handlers
    (:require [re-frame.core :as re-frame :refer [register-handler]]
              [ninety-nine-issues.db :as db]))

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

