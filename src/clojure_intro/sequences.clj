(ns clojure-intro.sequences)

;f(x) + g(x)
;(sum-fn inc dec 4) ;=> 8
;(sum-fn inc identity 5) ;=> 11
;(sum-fn identity - 10) ;=> 0
(defn sum-fn [f g x]
  nil)

;(filter (equal-to-fn 2) [2 1 3 2.0])    ;=> (2 2.0)
;(filter (equal-to-fn 2) [3 4 5 6])
;Hint use fn or #()
(defn equal-to-fn [n]
  nil)

;(filter (set->predicate #{1 2 3})     [0 2 4 6])       ;=> (2)
;(filter (set->predicate #{1 2 3 nil}) [2 nil 4 nil 6]) ;=> (2 nil nil)
;Hint contains?
(defn set->predicate [a-set]
  "Given a set returns fn of single x argument
  that returns true if x is in the set"
  nil)

;some? identity
(defn first-not-null [& args]
  "Returns first not null argument or null"
  nil)

;(filter (pred-and pos? even?) [1 2 -4 0 6 7 -3]) => [2 6]
;(filter (pred-and pos? odd?) [1 2 -4 0 6 7 -3]) ;=> [1 7]
;(filter (pred-and (complement nil?) empty?) [[] '() nil {} #{}]) => [[] '() {} #{}]
(defn pred-and [pred1 pred2]
  "Given 2 single ar predicate returns 'and' predicate"
  nil)

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
  nil)

(defn give-me-odds [& args]
  "Returns only odd numbers from given arguments"
  nil)

(defn give-me-first-5 [& args]
  "Returns first 5 arguments"
  nil)

(defn give-me-distinct [& args]
  "Returns only distinct arguments"
  nil)

;(append-and-concat 1 [2 3 4] [5 6 7] => [1 2 3 4 5 6 7]
(defn append-and-concat [elem coll1 coll2]
  "Appends elem to begining of 2 concated collections"
  nil)

;Don't try running (repeat "a") in REPL :)
(defn duplicate-five-times [item]
  "Repeates item five times"
  nil)

;Just flatten it damn it
(defn good-old-flatten [coll]
  nil)

(defn is-all-even? [coll]
  "Returns true if all numbers in collection are even"
  nil)

;Removes empty collections from collection of collections
(defn not-empty-colls [coll-of-colls]
  nil)

;factorial without recursion nor iteration
;Hint (range 1 5) => (1 2 3 4), Use apply or reduce
(defn no-iteration-factorial [n]
  nil)