(ns clojure-intro.sequences)

;f(x) + g(x)
;(sum-fn inc dec 4) ;=> 8
;(sum-fn inc identity 5) ;=> 11
;(sum-fn identity - 10) ;=> 0
(defn sum-fn [f g x]
  (+ (f x) (g x)))

;(filter (equal-to 2) [2 1 3 2.0])    ;=> (2 2.0)
;(filter (equal-to 2) [3 4 5 6])
;Hint use fn or #()
(defn equal-to-fn [n]
  (fn [k] (= k n)))

;(filter (set->predicate #{1 2 3})     [0 2 4 6])       ;=> (2)
;(filter (set->predicate #{1 2 3 nil}) [2 nil 4 nil 6]) ;=> (2 nil nil)
;Hint contains?
(defn set->predicate [a-set]
  "Given a set returns fn of single x argument
  that returns true if x is in the set"
  (fn [x] (contains? a-set x)))


(defn first-not-null [& args]
  "Returns first not null argument or null"
  (some identity args))

;(filter (pred-and pos? even?) [1 2 -4 0 6 7 -3]) => [2 6]
;(filter (pred-and pos? odd?) [1 2 -4 0 6 7 -3]) ;=> [1 7]
;(filter (pred-and (complement nil?) empty?) [[] '() nil {} #{}]) => [[] '() {} #{}]
(defn pred-and [pred1 pred2]
  "Given 2 single ar predicate returns 'and' predicate"
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
;Return true for nil despite java implementation
;Some Java interop done for you
;Remember string is also a sequence
(defn- is-whitespace? [char] (Character/isWhitespace char))
(defn blank? [s]
  "Tells if string s contains only blanks"
  (empty? (remove is-whitespace? s)))

(defn give-me-odds [& args]
  "Returns only odd numbers from given arguments"
  (filter odd? args))

(defn give-me-first-5 [& args]
  "Returns first 5 arguments"
  (take 5 args))

(defn give-me-distinct [& args]
  "Returns only distinct arguments"
  (distinct args))

;(append-and-concat 1 [2 3 4] [5 6 7] => [1 2 3 4 5 6 7]
(defn append-and-concat [elem coll1 coll2]
  "Appends elem to begining of 2 concated collections"
  (cons elem (concat coll1 coll2)))

;Don't try running (repeat "a") in REPL :)
(defn duplicate-five-times [item]
  "Repeates item five times"
  (repeat 5 item))

;Just flatten it damn it
(defn good-old-flatten [coll]
  (flatten coll))

(defn is-all-even? [coll]
  "Returns true if all numbers in collection are even"
  (every? even? coll))

;Removes empty collections from collection of collections
(defn not-empty-colls [coll-of-colls]
  (remove empty? coll-of-colls))

;factorial without recursion nor iteration
;Hint (range 1 5) => (1 2 3 4), Use apply or reduce
(defn no-iteration-factorial [n]
  (apply * (range 1 (inc n))))