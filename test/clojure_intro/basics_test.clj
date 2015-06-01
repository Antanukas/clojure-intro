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

(defspec first-not-null-test 200
  (prop/for-all [x (gen/such-that (complement empty?) (gen/vector gen/string))]
    (= (apply first-not-null x) (some identity x))))
