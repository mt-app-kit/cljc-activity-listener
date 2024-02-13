
(ns activity-listener.api
    (:require [activity-listener.env          :as env]
              [activity-listener.side-effects :as side-effects]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (activity-listener.env/*)
(def idle-time          env/idle-time)
(def idle-time-elapsed? env/idle-time-elapsed?)

; @redirect (activity-listener.side-effects/*)
(def reg-activity! side-effects/reg-activity!)
