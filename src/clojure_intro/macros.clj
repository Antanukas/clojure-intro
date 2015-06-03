(ns clojure-intro.macros
  (:require [clojure.string :as str]
            [clojure.pprint :refer [pprint]]))

;if def specialios formos
;funkcijos
; simboliai

(defmacro unless [condition body]
  (if condition nil body))

;(defn helper [form]
;  (if (or (number? form) (< (count form) 3))
;    form
;    (helper (cons (list (second form) (first form) (nth form 2))
;              (drop 3 form)))))

(defn helper [form]
  (if (seq? form)
    (let [[a op b & rest] form
          infix (list op a (helper b))]
      (if rest (helper (cons infix rest)) infix))
    form))

(defmacro |> [form]
  (helper form))

;2 + 2 + 2 + 2
(+ (+ (+ 2 2) 2) 2)


