(ns ninety-nine-issues.views
    (:require [re-frame.core :as re-frame]))

(def languages [[:javascript "Javascript"]
                [:python "Python"]
                [:java "Java"]])

(defn main-panel []
  [:div
   [:div.row
    [:div.medium-8.large-7.columns
     [:h1 "99 Issues"]]]

   [:div.row
    [:div.medium-8.large-7.columns
     [:p "Find beginner Github issues to work on"]]]

   [:div.row
    [:div.medium-8.large-7.columns.text-center
     [:form
      [:label
       [:select {:on-change (fn [e] (re-frame/dispatch [:select-language (.. e -target -value)]))}
        [:option {:value "" :disabled true :selected true} "Select your language"]
        (for [[k v] languages]
          ^{:key (str "select-" (name k))}
          [:option {:value k} v])]]]]]])

