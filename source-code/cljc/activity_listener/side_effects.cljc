
(ns activity-listener.side-effects
    (:require [activity-listener.env :as env]
              [common-state.api      :as common-state]
              [time.api              :as time]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn reg-activity!
  ; @description
  ; Registers the actual time as the most recent action of a specific activity.
  ;
  ; @param (keyword) activity-id
  ;
  ; @usage
  ; (reg-activity! :my-activity)
  [activity-id]
  #?(:clj  (common-state/assoc-state! :activity-listener activity-id :last-activity (time/epoch-ms))
     :cljs (common-state/assoc-state! :activity-listener activity-id :last-activity (time/elapsed))))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn lock-activity!
  ; @description
  ; Registers a lock timeout for a specific activity.
  ;
  ; @param (keyword) activity-id
  ; @param (ms) unlock-timeout
  ;
  ; @usage
  ; (lock-activity! :my-activity 420)
  [activity-id unlock-timeout]
  #?(:clj  (let [epoch-ms (time/epoch-ms)] (common-state/assoc-state! :activity-listener activity-id :locked-until (+ unlock-timeout epoch-ms)))
     :cljs (let [elapsed  (time/elapsed)]  (common-state/assoc-state! :activity-listener activity-id :locked-until (+ unlock-timeout elapsed)))))

(defn unlock-activity!
  ; @description
  ; Removes the lock timeout (if any) of a specific activity.
  ;
  ; @param (keyword) activity-id
  ;
  ; @usage
  ; (unlock-activity! :my-activity)
  [activity-id unlock-timeout]
  (common-state/dissoc-state! :activity-listener activity-id :locked-until))
