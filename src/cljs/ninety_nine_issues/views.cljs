(ns ninety-nine-issues.views
    (:require [re-frame.core :as re-frame :refer [dispatch]]))

(def languages [[:javascript "Javascript"]
                [:python "Python"]
                [:java "Java"]])

(defn select-language-page []
  [:div
   [:div.row
    [:div.medium-8.large-8.columns
     [:h1 "99 Issues"]]]
   [:div.row
    [:div.medium-8.large-8.columns
     [:img {:src "img/jayz-tagline.jpg"}]]]

   [:div.row {:style {:margin-top "2em"}}
    [:div.medium-8.large-8.columns.text-center
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
  (let [language (re-frame/subscribe [:language])
        issue    (re-frame/subscribe [:issue])
        loading? (re-frame/subscribe [:loading?])]
    (fn []
      [:div
       [:div.row
        [:div.medium-8.large-8.columns.align-middle
         [:a {:href "/"
                 ;; TODO put this in CSS
                 :style {:font-size "20px"
                         :background "rgba(0, 0, 0, .1)"
                         :padding "10px 20px"
                         :cursor "pointer"}}
          "Home page"]]]

       [:div.row
        [:div.medium-8.large-8.columns.text-center
         [:h1 @language " issue"]]]

       [:div.row
        [:div.medium-8.large-8.columns
         (cond
           @loading?
           [:div.row
            [:div.columns
             [:div.callout
              [:p "Loading issues from Github..."]]]]

           @issue
           [:div
            [:div.row
             [:div.columns
              [:h3 (:title @issue)]]]]

           ;; if there are no more issues in the queue for user
           (empty? @issue)
           [:div.row
            [:div.columns
             [:div.callout.warning
              [:p "We've ran out of issues for you. Refresh the page to restart."]]]])]

        ;; Swipe action button for next issue
        [:div.medium-2.large-2.columns.align-middle
         [:span {:on-click #(dispatch [:next-issue])
                 ;; TODO put this in CSS
                 :style {:font-size "80px"
                         :background "rgba(0, 0, 0, .1)"
                         :padding "20px 40px"
                         :cursor "pointer"}}
          ">"]]]])))

(defmulti pages identity)
(defmethod pages :select-language [] [select-language-page])
(defmethod pages :issue [] [issue-page])
(defmethod pages :default [] [:div])

(defn current-page []
  (let [active-page (re-frame/subscribe [:active-page])]
    (fn []
      (pages @active-page))))

