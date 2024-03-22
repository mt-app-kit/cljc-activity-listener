
(ns activity-listener.env
    (:require [common-state.api :as common-state]
              [time.api         :as time]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn idle-time
  ; @description
  ; Returns how many MS elapsed since the most recent action of a specific activity has been registered.
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
  (if-let [last-activity (common-state/get-state :activity-listener activity-id :last-activity)]
          #?(:clj  (- (time/epoch-ms) last-activity)
             :cljs (- (time/elapsed)  last-activity))))

(defn idle-time-elapsed?
  ; @description
  ; Returns TRUE if a specific activity has been idle for at least the given 'elapsed-ms' value.
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

(defn idle-time-not-elapsed?
  ; @description
  ; Returns TRUE if a specific activity has not been idle for at least the given 'elapsed-ms' value.
  ;
  ; @param (keyword) activity-id
  ; @param (ms) elapsed-ms
  ;
  ; @usage
  ; (idle-time-not-elapsed? :my-activity 420)
  ; =>
  ; true
  ;
  ; @return (boolean)
  [activity-id elapsed-ms]
  (-> (idle-time-elapsed? activity-id elapsed-ms) not))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn activity-locked?
  ; @description
  ; Returns TRUE if a lock timeout is registered for a specific activity and has not yet elapsed.
  ;
  ; @param (keyword) activity-id
  ;
  ; @usage
  ; (activity-locked? :my-activity)
  ; =>
  ; true
  ;
  ; @return (boolean)
  [activity-id]
  (if-let [locked-until (common-state/get-state :activity-listener activity-id :locked-until)]
          #?(:clj  (< (time/epoch-ms) locked-until)
             :cljs (< (time/elapsed)  locked-until))))

(defn activity-not-locked?
  ; @description
  ; Returns TRUE if either a lock timeout is not registered for a specific activity or it has already elapsed.
  ;
  ; @param (keyword) activity-id
  ;
  ; @usage
  ; (activity-not-locked? :my-activity)
  ; =>
  ; true
  ;
  ; @return (boolean)
  [activity-id]
  (-> (activity-locked? activity-id) not))
