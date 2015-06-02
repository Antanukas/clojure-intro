(ns clojure-intro.sequences-advanced)

;
(defn prefix-of? [seq candidate]
  "Tells if candidate is a prefix of seq"
  (= candidate (take (count candidate) seq)))

;(tails '(1 2 3 4)) => ((1 2 3 4) (2 3 4) (3 4) (4) ())
;Hint (cons '(1 2 3) 5) => (5 1 2 3)
(defn tails [coll]
  (cons coll (map (fn [item] (drop item coll)) coll)))

;(str-cat ["I" "am" "Legend"])  ;=> "I am Legend"
;(str-cat ["I" "am" "back"])    ;=> "I am back"
;(str-cat ["more" " " "space"]) ;=> "more   space"
;(str-cat []). Use reduce, interpose
(defn str-cat  [string-coll]
  (reduce str (interpose " " string-coll)))

;don't use built in interpose
;Hint drop-last, flatten
(defn my-interpose [x coll]
  (drop-last (flatten (map #(seq [%1 x]) coll))))

;don't use count
;Hint cons, interleave, iterate, last
(defn my-count [coll]
  (last (cons 0 (interleave coll (iterate inc 1)))))

;conj for lists
;Hint reduce, conj
(defn my-reverse [coll]
  (reduce (fn [acc-list, elem] (conj acc-list elem)) '() coll))

;Write the function my-map that works just like standard map.
;It takes one or more sequences and a function f that takes as many
;parameters as there are sequences
;Don't use recursion nor map itself
;Implement by starting with helper fns

;(reduce (helper inc) '() [[1] [2] [3]]) => (4 3 2)
;(reduce (helper +) '() [[1 1] [2 2] [3 3]]) => (6 4 2)
;Hints: conj, apply
(defn apply-and-append [f acc f-args]
  "Applies f to f-args and appends them to acc vector"
  (conj (vec acc) (apply f f-args)))

;(helper-map inc [[1] [2] [3]]) => [2 3 4]
;(helper-map + [[1 4] [2 5] [3 6]]) => [5 7 9]
;Hints: reduce, partial
(defn apply-on-every [f coll]
  "Applies f to each item in coll and returns resulting seq"
  (reduce (partial apply-and-append f) '() coll))

;Actual map implementation
(defn my-map [f & colls]
  (let [mapping-fn-inputs (partition (count colls) (apply interleave colls))]
    (apply-on-every f mapping-fn-inputs)))