(ns clojure-intro.sequences-advanced)

(defn prefix-of? [candidate sequence]
  (= candidate (take (count candidate) sequence)))

;=> (tails '(1 2 3 4))
;=> ((1 2 3 4) (2 3 4) (3 4) (4) ())
; (cons '(1 2 3) 5) => (5 1 2 3)
(defn tails [coll]
  (cons coll (map (fn [item] (drop item coll)) coll)))

;(str-cat ["I" "am" "Legend"])  ;=> "I am Legend"
;(str-cat ["I" "am" "back"])    ;=> "I am back"
;(str-cat ["more" " " "space"]) ;=> "more   space"
;(str-cat []). Use reduce
(defn str-cat  [string-coll]
  (reduce str (interpose " " string-coll)))

;don't use built in interpose
(defn my-interpose [x coll]
  (flatten (map #(seq [%1 x]) coll)))

;don't use count
(defn my-count [coll]
  (last (interleave coll (iterate inc 1))))

;conj for lists
(defn my-reverse [coll]
  (reduce (fn [acc-list, elem] (conj acc-list elem)) '() coll))

;Write the function my-map that works just like standard map.
;It takes one or more sequences and a function f that takes as many
;parameters as there are sequences
(defn my-map [f & colls]
  (let [zipped (partition (count colls) (apply interleave colls))
        func (fn [acc seq-elem] (cons (apply f seq-elem) acc))]
    (reduce func (empty zipped) zipped)))