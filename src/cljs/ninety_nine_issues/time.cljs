(ns ninety-nine-issues.time
    (:require [goog.date.DateTime :as DateTime]
              [goog.date :as date]))

(defn parse [dt] (date/fromIsoString dt))

(defn now [] (DateTime/fromTimestamp (goog.now)))


(defn interval [dt1, dt2] (- (.getTime dt2) (.getTime dt1)))

(defn time-left-since-date [dt] 
    (let [t (interval dt (now)) 
         days (int (/ t 1000 60 60 24))] 
    (cond 
         (= days 0) "today"
         (= days 1) "yesterday"
         :else (str days " days ago"))))






 