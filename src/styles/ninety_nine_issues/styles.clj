(ns ninety-nine-issues.styles
    (:require [garden.def :refer [defstylesheet defstyles]]
            [garden.units :refer [px]]))

(defstyles screen
  [:body
   {:font-family "monospace"
    :font-size (px 12)
    :line-height 1.5
    :display "flex"
    :height "100vh"
    :align-items "center"
    :justify-content "center"}]
  [:h1
   {:color "red"}]
  [:.container
   {:width "80vw"}]
  [:.issue
   {:padding [[(px 100)]]}]
  [:.next-issue
   {:background "rgba(0, 0, 0, .1)"
    :position "fixed"
    :font-size (px 50)
    :cursor "pointer"
    :padding [[(px 20) (px 40)]]
    :right 0
    :top "calc(50% - 40px)"}])
