(ns ninety-nine-issues.views
    (:require [re-frame.core :as re-frame]))

(defn main-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div "Hello from " @name
       [issue]
       [next-issue]])))

(defn issue []
  (let [issue (re-frame/subscribe [:issue])]
    (fn []
      [:div 
       [:h1 (:title @issue)]
       [:h2 (:project @issue)]])))

(defn next-issue []
  (let [issue (re-frame/subscribe [:issue])]
    (fn []
      ;; hide if issues has no more issues? if current issue == total issues?
      ;; if current issue is last issue?
      [:div
       {:on-click #(re-frame/dispatch [:swipe "forward"])}
       [:span ">>>>"]])))
