(ns ninety-nine-issues.views
    (:require [re-frame.core :as re-frame]))

(declare issue next-issue)

(defn main-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div
       {:class "container"}
       [issue]
       [next-issue]])))

(defn issue []
  (let [issue (re-frame/subscribe [:issue])]
    (fn []
      [:div
       {:class "issue"}
       [:h1 (:title @issue)]
       [:h2 (:project @issue)]])))

(defn next-issue []
  (let [issue (re-frame/subscribe [:issue])]
    (fn []
      ;; hide if issues has no more issues? if current issue == total issues?
      ;; if current issue is last issue?
      [:div
       {:on-click #(re-frame/dispatch [:swipe "forward"])
        :class "next-issue"}
       [:span ">"]])))
