(ns spa-routing.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [pushy.core :as pushy]
            [bidi.bidi :as bidi]
            [spa-routing.events]
            [spa-routing.subs]
            [spa-routing.routes :as routes]
            [spa-routing.views :as views]
            [spa-routing.config :as config]))

(defn start-routing! []
  (pushy/start! (pushy/pushy #(re-frame/dispatch [:set-active-panel %])
                             #(:handler (routes/match %)))))

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (start-routing!)
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
