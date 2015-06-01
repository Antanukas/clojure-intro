(ns clojure-intro.sequences)

;first class functions
;f(x) + g(x)
;(sum-fn inc dec 4) ;=> 8
;(sum-fn inc identity 5) ;=> 11
;(sum-fn identity - 10) ;=> 0
(defn sum-fn [f g x]
  (+ (f x) (g x)))

;(filter (equal-to 2) [2 1 3 2.0])    ;=> (2 2.0)
;(filter (equal-to 2) [3 4 5 6])
;Hint use fn
(defn equal-to [n]
  (fn [k] (= k n)))

;(filter (set->predicate #{1 2 3})     [0 2 4 6])       ;=> (2)
;(filter (set->predicate #{1 2 3 nil}) [2 nil 4 nil 6]) ;=> (2 nil nil)
;Hint contains?
(defn set->predicate [a-set]
  (fn [x] (contains? a-set x)))

;(filter (pred-and pos? even?) [1 2 -4 0 6 7 -3]) => [2 6]
;(filter (pred-and pos? odd?) [1 2 -4 0 6 7 -3]) ;=> [1 7]
;(filter (pred-and (complement nil?) empty?) [[] '() nil {} #{}]) => [[] '() {} #{}]
(defn pred-and [pred1 pred2]
  (fn [x] (and (pred1 x) (pred2 x))))

;From Apache Commons
;public static boolean isWhitespace(CharSequence cs) {
;    if (cs == null) {
;        return false;
;    }
;    int sz = cs.length();
;    for (int i = 0; i < sz; i++) {
;    if (Character.isWhitespace(cs.charAt(i)) == false) {
;        return false;
;    }
;    return true;
;}
;Do it in clojure
;(blank? " \t\n\t ") ;=> true
;(blank? "  \t a")   ;=> false
;(blank? "")         ;=> true
;Some Java interop done for you
(defn- is-whitespace? [char]
  (Character/isWhitespace char))
(defn blank? [s]
  (empty? (filter #(not (is-whitespace? %1)) s)))

(defn give-me-odds [& args]
  (filter odd? args))

(defn give-me-first-5 [& args]
  (take 5 args))

(defn give-me-distinct [& args]
  (distinct args))

;(concat-me-collections 1 [2 3 4] [5 6 7] => [1 2 3 4 5 6 7]
(defn concat-me-collections [elem coll1 coll2]
  (cons elem (concat coll1 coll2)))

;Don't try running (repeat "a") in REPL :)
(defn duplicate-five-times [item]
  (repeat 5 item))

(defn did-you-try-interleave? [coll1 coll2]
  (interleave coll1 coll2))

(defn insert-zero-in-between [coll]
  (interpose 0 coll))

(defn discard-those-three-elements [coll]
  (drop 3 coll))

;given sorted sequence of ages [4 5 6 16 18 20 33] return only adults
(defn only-adults [coll]
  (drop-while #(< % 18) coll))

(defn good-old-flatten [coll]
  (flatten coll))

; [1 2 3 4] => [[1 2] [3 4]]
(defn create-pairs [coll]
  (partition 2 coll))

(defn is-all-even? [coll]
  (every? even? coll))

;use fn to create predicate. remove
(defn not-empty-colls [coll-of-colls]
  (remove empty? coll-of-colls))

;faktorial without recursion nor iteration
;Hint (range 1 5) => (1 2 3 4), Use apply or reduce
(defn no-iteration-factorial [n]
  (apply * (range 1 (inc n))))