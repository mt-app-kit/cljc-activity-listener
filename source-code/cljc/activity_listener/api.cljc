
(ns activity-listener.api
    (:require [activity-listener.env          :as env]
              [activity-listener.side-effects :as side-effects]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (activity-listener.env/*)
(def idle-time              env/idle-time)
(def idle-time-elapsed?     env/idle-time-elapsed?)
(def idle-time-not-elapsed? env/idle-time-not-elapsed?)
(def activity-locked?       env/activity-locked?)
(def activity-not-locked?   env/activity-not-locked?)

; @redirect (activity-listener.side-effects/*)
(def reg-activity!    side-effects/reg-activity!)
(def lock-activity!   side-effects/lock-activity!)
(def unlock-activity! side-effects/unlock-activity!)
