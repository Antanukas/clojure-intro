(ns clojure-intro.sequences-test
  (:require [clojure.test :refer :all]
            [clojure-intro.sequences :refer :all]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]]))

(defspec sun-fn-test 100
  (prop/for-all [x gen/int]
    (= (sum-fn inc dec x) (+ x x))))

(defspec equal-to-test 100
  (prop/for-all [n gen/int
                 coll (gen/vector gen/int)]
    (=
      (set coll)
      (set (concat
             (filter (equal-to-fn n) coll)
             (filter (complement (equal-to-fn n)) coll))))))

(deftest set->predicate-test
  (is (= '(2) (filter (set->predicate #{1 2 3}) [0 2 4 6])))
  (is (= '(2 nil nil) (filter (set->predicate #{1 2 3 nil}) [2 nil 4 nil 6]))))

(defspec first-not-null-test 200
  (prop/for-all [x (gen/such-that (complement empty?) (gen/vector gen/string))]
    (= (apply first-not-null x) (some identity x))))


(deftest pred-and-test
  (is (= [2 6] (filter (pred-and pos? even?) [1 2 -4 0 6 7 -3])))
  (is (= [1 7] (filter (pred-and pos? odd?) [1 2 -4 0 6 7 -3])))
  (is (= [[] '() {} #{}] (filter (pred-and (complement nil?) empty?) [[] '() nil {} #{}]))))

(defspec blank?-test 1000
  (prop/for-all [s (gen/one-of [gen/string (gen/return nil)])]
    (= (blank? s) (clojure.string/blank? s))))

(defspec give-me-odds-test 100
  (prop/for-all [ints (gen/vector gen/int)]
    (= (apply give-me-odds ints) (filter odd? ints))))

(defspec give-me-first-5-test 100
  (prop/for-all [ints (gen/vector gen/int)]
    (= (apply give-me-first-5 ints) (take 5 ints))))

(defspec give-me-distinct-test 100
  (prop/for-all [ints (gen/vector gen/int)]
    (= (apply give-me-distinct ints)
      (distinct ints))))

(defspec concat-me-collections-test 100
  (prop/for-all [coll1 (gen/vector gen/int)
                 coll2 (gen/vector gen/char)
                 x gen/string]
    (= (append-and-concat x coll1 coll2) (flatten [x coll1 coll2]))))

(defspec duplicate-five-times-test 100
  (prop/for-all [x gen/string]
    (= (duplicate-five-times x) (repeat 5 x))))

(defspec did-you-try-interleave?-test 100
  (prop/for-all [coll1 (gen/vector gen/int)
                 coll2 (gen/vector gen/int)]
    (= (sutarpuok-dvi-kolekcijas coll1 coll2) (interleave coll1 coll2))))

(defspec insert-zero-in-between-test 100
  (prop/for-all [coll (gen/vector gen/string)]
    (= (insert-zero-in-between coll) (interpose 0 coll))))

(defspec discard-those-three-elements-test 100
  (prop/for-all [coll (gen/vector gen/int)]
    (= (discard-those-three-elements coll) (drop 3 coll))))

(defspec only-adults-test 100
  (prop/for-all [ages (gen/vector (gen/fmap
                                    #(Math/abs (mod % 120))
                                    gen/int))]
    (= (only-adults ages) (drop-while #(< % 18) ages))))

(defspec good-old-flatten-test 100
  (prop/for-all [coll (gen/vector gen/int)]
    (= (good-old-flatten coll) (flatten coll))))

(defspec create-pairs-test 100
  (prop/for-all [coll (gen/vector gen/int)]
    (= (create-pairs coll) (partition 2 coll))))

(defspec is-all-even?-test 100
  (prop/for-all [coll (gen/vector gen/int)]
    (= (is-all-even? coll) (every? even? coll))))

(defspec not-empty-colls-test 100
  (prop/for-all [coll-of-colls (gen/vector (gen/vector gen/int))]
    (= (not-empty-colls coll-of-colls) (filter (complement empty?) coll-of-colls))))

(deftest no-iteration-factorial-test
  (is (= (no-iteration-factorial 5) 120))
  (is (= (no-iteration-factorial 8) 40320))
  (is (= (no-iteration-factorial 11) 39916800)))
