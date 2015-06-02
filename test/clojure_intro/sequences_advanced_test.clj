(ns clojure-intro.sequences-advanced-test
  (:require [clojure.test :refer :all]
            [clojure-intro.sequences-advanced :refer :all]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]]))

(deftest prefix-of?-test
  (is (= (prefix-of? [1 2 3 4] [1 2 3] ) true))
  (is (= (prefix-of? [1 2 3 4] []) true))
  (is (= (prefix-of? [1] [1 2 3] ) false))
  (is (= (prefix-of? nil [1 2 3 4]) false))
  (is (= (prefix-of? [1 2 3] nil) false)))

(defspec tails-test 100
  (prop/for-all [v (gen/vector gen/int)]
    (let [tails (tails v)
          all-prefix-of-reversed? (every?
                                    (partial prefix-of? (reverse v))
                                    (map reverse tails))]
      (= all-prefix-of-reversed? true))))

(defspec str-cat-test 100
  (prop/for-all [svec (gen/vector gen/string)]
    (let [count-without-spaces #(count (remove (partial = \space) %))]
      (= (count-without-spaces (apply str svec)))
        (count-without-spaces (str-cat svec)))))

(defspec my-interpose-test 100
  (prop/for-all [x gen/int
                 coll (gen/vector gen/string)]
    (= (my-interpose x coll) (interpose x coll))))

(defspec my-count-test 100
  (prop/for-all [coll (gen/vector (gen/one-of [gen/string gen/int]))]
    (= (my-count coll) (count coll))))

(defspec my-reverse-test 100
  (prop/for-all [coll (gen/vector (gen/one-of [gen/string gen/int]))]
    (= (my-reverse coll) (reverse coll))))

(defspec my-map-test 100
  (prop/for-all [coll1 (gen/vector gen/int)
                 coll2 (gen/vector gen/int)]
    (= (my-map + coll1 coll2) (map + coll1 coll2))))