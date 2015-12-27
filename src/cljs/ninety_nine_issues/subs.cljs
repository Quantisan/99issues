(ns ninety-nine-issues.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame :refer [register-sub]]))

(defn keyed-reaction [k]
  (fn [db]
    (reaction (k @db))))

(register-sub
 :active-page
 (keyed-reaction :active-page))

(register-sub
  :language
  (keyed-reaction :language))

(register-sub
  :issue
  (fn [db]
    (reaction (first (:issues @db)))))

