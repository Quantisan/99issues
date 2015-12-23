(ns ninety-nine-issues.views
    (:require [re-frame.core :as re-frame]))

(def languages [[:javascript "Javascript"]
                [:python "Python"]
                [:java "Java"]])

(defn select-language-page []
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
       [:select {:on-change (fn [e]
                              (re-frame/dispatch [:user-select-language
                                                  (let [select-element (.-target e)
                                                        index (.. select-element -selectedIndex)]
                                                    (.. select-element -options (item index) -label))]))
                 :defaultValue ""}
        [:option {:value "" :disabled true } "Select your language"]
        (for [[k v] languages]
          ^{:key (str "select-" (name k))}
          [:option {:value k} v])]]]]]])

(defn issue-page []
  (let [language (re-frame/subscribe [:language])]
    [:div
     [:p @language " issue"]]))

(defmulti pages identity)
(defmethod pages :select-language [] [select-language-page])
(defmethod pages :issue [] [issue-page])
(defmethod pages :default [] [:div])

(defn current-page []
  (let [active-page (re-frame/subscribe [:active-page])]
    (fn []
      (pages @active-page))))

