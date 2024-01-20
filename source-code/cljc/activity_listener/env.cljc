
(ns activity-listener.env
    (:require [activity-listener.state :as state]
              [time.api                :as time]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn idle-time
  ; @description
  ; Returns how many MS elapsed since the last activity has been registered with the given activity ID.
  ;
  ; @param (keyword) activity-id
  ;
  ; @usage
  ; (idle-time :my-activity)
  ; =>
  ; 420
  ;
  ; @return (ms)
  [activity-id]
  (if-let [last-activity (get @state/LAST-ACTIVITIES activity-id)]
          #?(:clj  (- (time/epoch-ms) last-activity)
             :cljs (- (time/elapsed)  last-activity))))

(defn idle-time-elapsed?
  ; @description
  ; Returns TRUE if the activity has been idle for at least the given 'elapsed-ms' value.
  ;
  ; @param (keyword) activity-id
  ; @param (ms) elapsed-ms
  ;
  ; @usage
  ; (idle-time-elapsed? :my-activity 420)
  ; =>
  ; true
  ;
  ; @return (boolean)
  [activity-id elapsed-ms]
  (if-let [idle-time (idle-time activity-id)]
          (>= idle-time elapsed-ms)))
