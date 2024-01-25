
(ns activity-listener.side-effects
    (:require [activity-listener.env   :as env]
              [activity-listener.state :as state]
              [time.api                :as time]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn reg-last-activity!
  ; @description
  ; Registers the actual time as when the last activity happened of the given activity ID.
  ;
  ; @param (keyword) activity-id
  ;
  ; @usage
  ; (reg-last-activity! :my-activity)
  [activity-id]
  #?(:clj  (swap! state/LAST-ACTIVITIES assoc activity-id (time/epoch-ms))
     :cljs (swap! state/LAST-ACTIVITIES assoc activity-id (time/elapsed))))
