(ns clojure-intro.presentation
  (:import (clojure.lang Seqable Associative)))

"What is functional language?"

(def functional-language-features
  [:first-class-functions "Functions can be passed as arguments to other functions"

   :higher-order-functions "Functions accepting other functions as parameters"

   :pure-functions "Functions without side-effects. Alayws return same result when given same input"

   :looping-with-recursion "Looping is done using recursion. No for, while, repeat etc."

   :emphasizes-lazy-evalution "Lazyness everywhere"

   :immutable-state "When appending to a list new list with appended element is returned"])

"Clojure has all this and additional provides safe way to represent state using STM in conccurent application"





(def clojure-features
  ;Will cover today
  :repl

  :first-class-functions
  :immutable-data-structures
  :tail-recursion

  ;Won't be covered
  :lazy-sequences
  :concurrent-programming [:stm :refs :atoms :agents :vars]

  :runtime-polymorphism [:proxy :multi-methods :protocols :datatypes]

  :macros

  :jvm-interop)







(def data-types
  {:numbers   [1 1234N 1.234 1.234M (/ 2 3)]

   :string    "This is a string",

   :character [\c \l \o \j \u \r \e],

   :boolean   [true false nil]

   :keyword   :is-a-keyword,                                ;=> resolves to themselves

   :symbol    [*compile-path* + (def my-sym "x")],          ;=> resolves to value

   :list      '(1 2 3 4 5),

   :vector    [1 2 3 4 5],

   :set       [#{1 2 3 4 5} (hash-set 1 2 3 4) (sorted-set 4 2 3 1 5) (set [1 2 3])]

   :map       [{:key 1 :otherKey "value"} (sorted-map :one 1 :three 3 :two 2)]})

;defining-symbols
(def some-string "str")

(def some-vector [1 2 3 4 5])

(def some-map [1 2 3 4 5])



;Operations
;calling-functions
;C => println("arg1", "arg2")
(println "arg1" "arg2")

;C => ((1 + 2) * (3 + 4)) / 2
(/ (* (+ 1 2) (+ 3 4)) 2)

;defining-functions
[:anonymous (fn [arg1 arg2] (println arg1 arg2))]

[:anonymous-short #(println %1 %2)]

(def two-arg-println
  (fn [arg1 arg2] (println arg1 arg2)))

(defn two-arg-println2 [arg1 arg2]
  (println arg1 arg2))

; symbol-metadata
(meta #'+)

(with-meta two-arg-println {:doc "Example of function definition"})

(defn ^{:doc "Example of calling functions"} some-documented-function [] (println "Hi"))

(defn some-doc-fn  "This is a documentation" [] (println "Me is some function"))

(defn ^String returns-string [^Integer i ^String text] (println i text))







; true or false?
(and true "I am true")

(and false "I am not true")

(and nil "I am not true")

(def falsy [false nil])

(def truthy :everything-else)


"Sequences"
(map seq?         [[] '() #{} {}]) ;=> (false true  false false)
(map sequential?  [[] '() #{} {}]) ;=> (true  true  false false)
(map associative? [[] '() #{} {}]) ;=> (true  false false true)
(map coll?        [[] '() #{} {}]) ;=> (true  true  true  true)

(defn always-true [& _] true)
(defn printer [& args]
  (println args)
  true)

(def all-collections [[1 2 3] '(1 2 3) #{1 2 3} {:a 1 :b 2 :c 3}])
(def list-and-vector [[1 2 3] '(1 2 3)])
(def seq-ops
  (map first all-collections)
  (map rest all-collections); always returns empty coll
  (map (partial cons "z") all-collections))

(map distinct list-and-vector)
(map #(filter always-true %) all-collections)
(map #(remove always-true %) all-collections)

;keep returns items for which f(item) != nll
(keep (fn [item] [item]) [1 2 3])
(keep-indexed (fn [index item] [index item]) [1 2 3])

(cons 1 [2 3]); append to begining
(concat [1 2] [3 4]) ;concats
(map (fn [item] (repeat 5 item)) [1 2 3]);transforms
(mapcat (fn [item] (repeat 5 item)) [1 2 3]);transforms and concats
(map-indexed (fn [i, item] (* i item)) [1 2 3 4])
(take 12 (cycle [1 2 3]));
(interleave [1 2 3] ["a" "b" "c"]); almost pairs items
(interpose "zeparator" '(1 2 3));inserts between items
(next []);same as rest just returns nil is no more left.
(butlast [1 2 3]); opsite to next
(drop-last [1 2 3])
(drop 5 [1 2 3 4 5 6]);drops items from begining
(drop-while #(< % 5) [1 3 4 7 5 9]); drops while predicate holds true
(take-while #(< % 5) [1 3 4 7 5 9])
;Similar to Scala for-comprehension
(for [x [1 2 3]
      y ["s" "b"]]
  (do
    (println x " " y)
    [x y]))
(take 3 [1 2 3 4]); takes first items
(take-nth 3 [1 2 3 4 5 6]);every nth member
(flatten [[1 2] '(3 4)])
(reverse [1 2 3 4])
(sort  > [5 4 2 3])
(sort-by :name [{:name "Antanas"}{:name "Jonas"}{:name "Bernardas"}])
(shuffle [1 2 3])
(partition 2 [1 2 3 4 5])
(partition-all 2 [1 2 3 4 5])
(partition-by :name [{:name "Antanas"}
                     {:name "Antanas"}
                     {:name "Jonas"}
                     {:name "Ieva"}
                     {:name "Ieva"}])

;More on sequences
;TODO destructuring




















