
(ns activity-listener.state
    #?(:cljs (:require [reagent.core :refer [atom] :rename {atom ratom}])))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @ignore
;
; @description
; ...
;
; @atom (map)
; {:my-countdown (ms)}
(defonce COUNTDOWNS #?(:clj  (atom  {})
                       :cljs (ratom {})))

; @ignore
;
; @description
; ...
;
; @atom (map)
; {:my-activity (ms)}
(defonce LAST-ACTIVITIES #?(:clj  (atom  {})
                            :cljs (ratom {})))
