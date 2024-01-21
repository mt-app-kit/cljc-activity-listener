
(ns activity-listener.api
    (:require [activity-listener.env          :as env]
              [activity-listener.side-effects :as side-effects]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (activity-listener.env/*)
(def idle-time          env/idle-time)
(def idle-time-elapsed? env/idle-time-elapsed?)
(def time-left          env/time-left)

; @redirect (activity-listener.side-effects/*)
(def reg-last-activity! side-effects/reg-last-activity!)
(def start-countdown!   side-effects/start-countdown!)
