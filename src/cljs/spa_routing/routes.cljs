(ns spa-routing.routes
  (:require
   [bidi.bidi :as bidi]))

(def routes
  ["/" {"" :home-panel
        "about" :about-panel}])

(def match (partial bidi/match-route routes))

(def path (partial bidi/path-for routes))
