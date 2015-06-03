(ns clojure-intro.basics-test
  (:require [clojure.test :refer :all]
            [clojure-intro.basics :refer :all]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]]))

(defn- fn-test
  ([my-fn correct-fn gen]
   (prop/for-all [x gen]
     (= (my-fn x) (correct-fn x))))
  ([my-fn correct-fn gen1 gen2]
   (prop/for-all [x gen1
                  y gen2]
     (= (my-fn x y) (correct-fn x y)))))

(defspec is-odd-test 100
  (fn-test is-odd? odd? gen/int))

(defspec sum-test 100
  (fn-test sum + gen/int gen/int))

(defspec subtr-test 100
  (fn-test subtr - gen/int gen/int))

(defspec divide-test 100
  (fn-test divide / gen/int (gen/such-that (complement zero?) gen/int)))

(defspec multiply-test 100
  (prop/for-all [x (gen/such-that (complement empty?) (gen/vector (gen/fmap bigint gen/int)))]
    (= (apply multiply  x) (apply *  x))))

(defspec inc-test 100
  (fn-test increase-by-one inc gen/int))

(defspec string-concat-test 100
  (fn-test concat-two-strings str gen/string gen/string))

(defspec my-reverse-test 100
  (fn-test my-reverse clojure.string/reverse gen/string))

(defspec is-true-test 100
  (fn-test is-true? true? gen/int))

(defspec my-not-test 100
  (fn-test my-not not gen/int))

(defspec my-and-test 100
  (prop/for-all [x gen/int
                 y gen/int]
    (= (my-and x y) (and x y))))

(defspec my-or-test 100
  (prop/for-all [x gen/int
                 y gen/int]
    (= (my-or x y) (or x y))))

(defspec my-nil-test 100
  (fn-test my-is-nil? nil? gen/int))

(deftest give-me-some-vector-test
  (is (vector? (give-me-some-vector))))

(deftest give-me-some-list-test
  (is (list? (give-me-some-list))))

(defn of-type [type-factory elem]
  (some #(instance? (type (type-factory)) %) elem))

(deftest give-me-some-maps-test
  (def list-of-maps (give-me-some-maps))

  (is (every? map? list-of-maps) "Every item is map")
  (is (of-type hash-map list-of-maps)
      "Some items are instances of hash-map")
  (is (of-type sorted-map list-of-maps)
      "Some items are instances of sorted-map"))



(deftest give-me-some-sets-test
  (def list-of-sets (give-me-some-sets))

  (is (every? set? list-of-sets) "Every item is set")
  (is (of-type hash-set list-of-sets)
      "Some Items are instances of hash-set")
  (is (of-type sorted-set list-of-sets)
      "Some Items are instances of sorted-set"))

(deftest make-list-test
  (is (list? (make-list 1 2)) "Returned type shoulb be list")
  (is (= (make-list 0) '(0)) "Make single element list")
  (is (= (make-list 0 2 3) '(0 2 3)) "Make multiple elements list")
  (is (= (make-list "Clojure" 42 [:a :b ]) '("Clojure" 42 [:a :b ]))
      "Make various types list"))

(deftest make-vector-test
  (is (vector? (make-vector 1 2 )) "Returned type should be vector")
  (is (= (make-vector "single") ["single"]) "Make single element vector")
  (is (= (make-vector 1 2 3 ) [1 2 3]) "Make multiple elements vector")
  (is (= (make-vector "clj" 1 :A) ["clj", 1, :A])
      "Make various types vector"))

(deftest make-hash-map-test
  (is (map? (make-hash-map 1 2)) "Returned type should be hash-map")
  (is (= (make-hash-map 1 "single") {1 "single"}) "Make single pair hashmap")
  (is (= (make-hash-map 1 2 3 4 ) {1 2, 3 4}) "Make multiple pairs hashmap"))

(deftest make-set-test
  (is (set? (make-set 0)), "Returned type should be set")
  (is (= (make-set) #{}) "Should make zero elements set")
  (is (= (make-set 1 2 3) #{1 2 3}) "Should make multiple elements set")
  (is (= (make-set 1 2 3 2 1) #{1 2 3}) "Should remove duplicates from set"))

