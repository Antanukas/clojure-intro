(ns clojure-intro.sequences)

;faktorial without recursion nor iteration
;Hint (range 1 5) => (1 2 3 4)
(defn no-iteration-factorial [n]
  (apply * (range 1N (inc (bigint n)))))

(defn give-me-odds [& args]
  (filter odd? args))

(defn give-me-first-5 [& args]
  (take 5 args))

(defn give-me-distinct [& args]
  (distinct args))

(defn give-me-distinct-without-using-distinct [& args]
  (set args))

;(concat-me-collections 1 [2 3 4] [5 6 7] => [2 3 4 5 6 7 1]
(defn concat-me-collections [elem coll1 coll2]
  (cons elem (concat coll1 coll2)))

;Don't try running (repeat "a") in REPL :)
(defn duplicate-five-times [item]
  (repeat 5 item))

(defn did-you-try-interleave? [c1 c2 & colls]
  (interleave c1 c2 colls))

(defn insert-zero-in-between [coll]
  (interpose 0 coll))

(defn discard-those-three-elements [coll]
  (drop 3 coll))

;given sorted sequence of ages [4 5 6 16 18 20 33] return only adults
(defn only-adults [coll]
  (drop-while #(< % 18) coll))

(defn good-old-flatten [coll]
  (flatten coll))

(defn create-pairs-for-me [coll]
  (partition 2 coll))

(defn is-all-even? [coll]
  (every? even? coll))

;use fn to create predicate
(defn remove-longer-then-two-string [string-coll]
  (remove (fn [s] (> (count s) 2)) string-coll))
