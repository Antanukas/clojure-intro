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








; true or false?
(and true "I am true")

(and false "I am not true")

(and nil "I am not true")

(def falsy [false nil])

(def truthy :everything-else)











"Sequences"
(map coll?        [[] '() #{} {}]) ;=> (true  true  true  true)
(map sequential?  [[] '() #{} {}]) ;=> (true  true  false false)
(map seq?         [[] '() #{} {}]) ;=> (false true  false false)
(map associative? [[] '() #{} {}]) ;=> (true  false false true)

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

;Joining
(cons 1 [2 3]); append to begining
(conj [1 2 3] 4)
(conj '(1 2 3) 4)
(concat [1 2] [3 4]) ;concats

;Transforming
(map (fn [item] (repeat 5 item)) [1 2 3]);transforms
(mapcat (fn [item] (repeat 5 item)) [1 2 3]);transforms and concats
(map-indexed (fn [i, item] (* i item)) [1 2 3 4])
(take 12 (cycle [1 2 3]));
(interleave [1 2 3] ["a" "b" "c"]); almost pairs items
(interpose "zeparator" '(1 2 3));inserts between items
(for [x [1 2 3]
      y ["s" "b"]]
  (do
    (println x " " y)
    [x y]))
(flatten [[1 2] '(3 4)])
(partition 2 [1 2 3 4 5])
(partition-all 2 [1 2 3 4 5])
(partition-by :name [{:name "Antanas"}
                     {:name "Antanas"}
                     {:name "Jonas"}
                     {:name "Ieva"}
                     {:name "Ieva"}])


;Taking
(next []);same as rest just returns nil is no more left.
(butlast [1 2 3]); opsite to next
(drop-last [1 2 3])
(drop 5 [1 2 3 4 5 6]);drops items from begining
(drop-while #(< % 5) [1 3 4 7 5 9]); drops while predicate holds true
(take-while #(< % 5) [1 3 4 7 5 9])
(first [1 2 3])
(nth 3 [1 2 3 4])
(rand-nth [1 2 3 4])
(take 3 [1 2 3 4]); takes first items
(take-nth 3 [1 2 3 4 5 6]);every nth member
;Similar to Scala for-comprehension

;Positioning
(reverse [1 2 3 4])
(sort  > [5 4 2 3])
(sort-by :name [{:name "Antanas"}{:name "Jonas"}{:name "Bernardas"}])
(shuffle [1 2 3])

;Producing
(zipmap [1 2 3] ["a" "b" "c"]);Produces map
;Similar to concat.Doesnt change to list. Works with maps.
(into {:b  "c"} {:a "b"})
(set [1 2 3])
(vec #{1 2 3})
(list [1 2 3])
(hash-map :a "a" :b "b")
(seq(frequencies [1 2 3 4 4 5])); map of frequencies

(reduce (fn [acc, item] (+ acc item)) [1 2 3 4])
(reduce + [1 2 3 4])

;predicates
(empty? [])
(every? odd? [1 3 5])
(empty? [])
(some #{1 3} [2 4]); true or nil
(apply + [1 2 3 4]); looks like reduce

;Creating sequences
(let [s (lazy-seq (do (Thread/sleep 2000) [1 2 3 4]))]
  s)
(repeatedly 5 #(println "test"))
(take 10 (iterate inc 1))
(repeat 5 "test")
(range 1 10)
(range 1 10 2)













"Destructuring"

"In function arguments"
(defn distance [[x1 y1] [x2 y2]]
  (let [^2 #(* % %)]
    (Math/sqrt (+ (^2 (- x2 x1)) (^2 (- y2 y1))))))

"In let"
(let [data ["Antanas"
            "Tieto office"
            "Lead Project Manager"
            "Not interesting info"
            "More not interesting info"]
      [name address job-title & rest] data]
  (println name " " address " " job-title " " rest))





















