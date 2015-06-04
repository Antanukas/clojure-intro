(ns clojure-intro.sequences-advanced)

;
(defn prefix-of? [seq candidate]
  "Tells if candidate is a prefix of seq"
  false)

;(tails '(1 2 3 4)) => ((1 2 3 4) (2 3 4) (3 4) (4) ())
;Hint (cons '(1 2 3) 5) => (5 1 2 3)
(defn tails [coll]
  [])

;(str-cat ["I" "am" "Legend"])  ;=> "I am Legend"
;(str-cat ["I" "am" "back"])    ;=> "I am back"
;(str-cat ["more" " " "space"]) ;=> "more   space"
;(str-cat []). Use reduce, interpose
(defn str-cat  [string-coll]
  "I am random")

;don't use built in interpose
;Hint drop-last, flatten
;Try creating collection of pairs [[item1 sep] [item2 sep] ...]
(defn my-interpose [x coll]
  [])

;don't use count
;Hint cons, interleave, iterate, last
;("a", "b", "c") => ("a" 0 "b" 1 "c" 2)
(defn my-count [coll]
  [])

;conj for lists
;Hint reduce, conj
(defn my-reverse [coll]
  [])

;Write the function my-map that works just like standard map.
;It takes one or more sequences and a function f that takes as many
;parameters as there are sequences
;Don't use recursion nor map itself
;Implement by starting with helper fns

;(apply-and-append inc [] [1]) => [2]
;(apply-and-append inc [1 2 3] [3]) => [1 2 3 4]
;Hints: conj, apply
(defn apply-and-append [f acc f-args]
  "Applies f to f-args and appends them to acc vector"
  [])

;(apply-on-every inc [[1] [2] [3]]) => [2 3 4]
;(apply-on-every + [[1 4] [2 5] [3 6]]) => [5 7 9]
;Hints: reduce, partial, use apply-and-append
(defn apply-on-every [f coll]
  "Passes each element in coll as argument to f and returns coll of results"
  [])

;(zip [1 2 3] [4 5 6]) => ((1 4) (2 5) (3 6))
;(zip [1 2 3]) => ((1) (2) (3))
(defn zip [& colls]
  "Given multiple collections creates collection of grouped items"
  [])

;Actual map implementation
;Combine helpers
(defn my-map [f & colls]
  [])