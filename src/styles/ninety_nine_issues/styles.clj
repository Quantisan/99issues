(ns ninety-nine-issues.styles
    (:require [garden.def :refer [defstylesheet defstyles]]
            [garden.units :refer [px]]))

(defstyles screen
  [:body
   {:font-family "sans-serif"
    :font-size (px 12)
    :line-height 1.5}]
  [:h1
   {:color "red"}]
  [:.next-issue
   {:color "green"}])
