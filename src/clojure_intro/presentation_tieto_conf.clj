(ns clojure-intro.presentation-tieto-conf
  (:import (clojure.lang ExceptionInfo)))


(def title "")

(def agenda
  ["Clojure Basics"

  "Clojure Basics Practice"

  "Explore webapp written in Clojure"

  "Try finishing the implementation of webapp"])


















(def history-facts
  ["Clojure is dialect of Lisp (List processing)."

  "Lisp first apeard in 1958. John McCarthy: 'Recursive Functions of Symbolic Expressions and Their Computation by Machine, Part I'"
  "John McCarthy was born in Boston, Massachusetts on September 4, 1927 to an Irish immigrant father and a Lithuanian Jewish immigrant mother."

  "Second oldest PL. Older is only Fortran (1957)."

  "Other popular dialects: Common Lisp, Scheme."

  "First realease: 2007, last release 2015 June 30 (2 months ago)"

  "Eric S. Raymond:
    Lisp is worth learning for the profound enlightenment experience you will have when you finally get it;
    that experience will make you a better programmer for the rest of your days, even if you never actually use Lisp itself a lot."

  "Motivation: simplicity - building solutions with complext tools on complex infrastructure will make your solution more complex "
  "Success stories: http://cognitect.com/clojure#successstories" ])















(def functionl-features-programming
  [:first-class-functions "Functions can be passed as arguments to other functions"

   :higher-order-functions "Functions accepting other functions as parameters"

   :pure-functions "Functions without side-effects. Alayws return same result when given same input"

   :immutability "When appending to a list new list with appended element is returned"

   :looping-with-recursion "Looping is done using recursion. No for, while, repeat etc."

   :emphasizes-lazy-evalution "Lazyness everywhere"])

"Clojure has all this together with a safe way to represent state using STM in concurrent application"


















(defn function_or_macro [& args] nil)
(def clojure-and-lisp-uniqueness
  ["Code is Data. Homoiconic - you can express programs in a programming language’s primitive data types."

  "Metaprogramming with Macros. Since code is data you write macros in Clojure to process Clojure code."

  "Whole code is written in s-expressions (parenthesized lists)."

  "Lists everywhere (or vectors and maps in Clojure)."

  "Whole syntax can be desribed like that:" (function_or_macro "arg1" "arg2" "..." "argn")

  "Arithmetic in Polish prefix notation:" (+ 1 2 (* 3 4) (/ 5 6) (- 7 8))

  "Clojure is designed to be hosted language."
    {:clr "https://github.com/clojure/clojure-clr"
     :js  "https://github.com/clojure/clojurescript"
     :jvm "https://github.com/clojure/clojure"}

  "Clojure is impure functional programming language. (Clojure is not Haskell)"

  "Clojure is dynamic stronlgy types language."])

















(def clojure-features
  ;Will cover today
  [:repl

  :datatypes

  :namespaces

  :first-class-functions

  :immutable-data-structures

  :lazy-sequences

  :exceptions

  ;Won't be covered
  :concurrent-programming [:stm :refs :atoms :agents :vars]

  :runtime-polymorphism [:proxy :multi-methods :protocols :datatypes]

  :macros

  :java-interop])




(def namespaces "This file is located in " 'clojure-intro.presentation-tieto-conf)


(def data-types
  {:numbers   [1 1234N 1.234 1.234M (/ 2 3)]

   :string    "This is a string",

   :character [\c \l \o \j \u \r \e],

   :boolean   [true false nil] ;everything is true except false and nil

   :keyword   :is-a-keyword,                                ;=> resolves to themselves

   :symbol    [*compile-path* + (def my-sym "x")],          ;=> resolves to value

   :list      '(1 2 3 4 5),

   :vector    [1 2 3 4 5],

   :set       [#{1 2 3 4 5} (hash-set 1 2 3 4) (sorted-set 4 2 3 1 5) (set [1 2 3])]

   :map       [{:key 1 :otherKey "value"} (sorted-map :one 1 :three 3 :two 2)]})


;defining-symbols
(def some-string "str")

(def some-vector [1 2 3 4 5])

(def some-map {:a 1 :b 2 "c" 3 4 4})



;Operations
;calling-functions
;C => println("arg1", "arg2")
(println "arg1" "arg2")

;C => ((1 + 2) * (3 + 4)) / 2
;!No operator precedence
(/ (* (+ 1 2) (+ 3 4)) 2)

;defining-functions
[:anonymous (fn [arg1 arg2] (println arg1 arg2))]

[:anonymous-short #(println %1 %2)]

(def two-arg-println
  (fn [arg1 arg2] (println arg1 arg2)))

(defn two-arg-println2 [arg1 arg2]
  (println arg1 arg2))

;applying functions: supply a function and a collection of arguments for that function
(apply two-arg-println ["arg1" "arg2"])

(defn varargs [& args]
  (println args))






(def falsy [false nil])
(def truthy :everything-else)
; true or false?
(and true "I am true")

(and false "I am not true")

(and nil "I am not true")











"Very rich collection Api"

"4 Types of collection:"
{:vectors [[1 2 3 4] (vector 1 2 3 4) (vec '(1 2 3))]
 :lists ['(1 2 3 4) (list 1 2 3 4)]
 :sets [#{1 2 3 4} (hash-set 1 2 3 3 3) (sorted-set 1 2 3 4 4)]
 :maps [{:key "value" 1 "1value"} (hash-map :key1 "val" :key2 "val2") (sorted-map 1 2 3 4)]}

"Taken from http://clojure.org/sequences"

(def sequence-operations
  ["Seq in, Seq out"

   "Shorter seq from a longer seq:" [distinct filter remove 'for keep keep-indexed]
   "Longer seq from a shorter seq:" [cons concat 'lazy-cat mapcat cycle interleave interpose]
   "Seq with head-items missing:" [rest next fnext nnext drop drop-while nthnext 'for]
   "Seq with tail-items missing:" [take take-nth take-while butlast drop-last]
   "Rearrangment of a seq:" [flatten reverse sort sort-by shuffle]
   "Create nested seqs:" [split-at split-with partition partition-all partition-by]
   "Process each item of a seq to create a new seq:" [map pmap mapcat 'for replace reductions map-indexed seque]

   "Using a seq"

   "Extract a specific-numbered item from a seq:" [first ffirst nfirst second nth 'when-first last rand-nth]
   "Construct a collection from a seq:" [zipmap into reduce set vec into-array to-array-2d frequencies group-by]
   "Pass items of a seq as arguments to a function:" [apply]
   "Compute a boolean from a seq:" [not-empty some reduce seq? every? not-every? not-any? empty?]
   "Search a seq using a predicate:" [some filter]
   "Force evaluation of lazy seqs:" ['doseq dorun doall]
   "Check if lazy seqs have been forcibly evaluated:" [realized?]

   "Creating a seq"
   "Lazy seq from collection:" [seq vals keys rseq subseq rsubseq]
   "Lazy seq from producer function:" ['lazy-seq repeatedly iterate]
   "Lazy seq from constant:" [repeat range]
   "Lazy seq from other objects:" [line-seq resultset-seq re-seq tree-seq file-seq xml-seq iterator-seq enumeration-seq]])

"Soooo how much functions do we have here?"
(count (set (flatten (remove string? sequence-operations))))

;Joining
(cons 1 [2 3]); append to begining
(conj [1 2 3] 4)
(conj '(1 2 3) 4)
(concat [1 2] [3 4]) ;concats

;Transforming
(map (fn [item] (repeat 5 item)) [1 2 3]);transforms
(mapcat (fn [item] (repeat 5 item)) [1 2 3]);transforms and concats
(take 12 (cycle [1 2 3]));

(partition 2 [1 2 3 4 5])
(partition-all 2 [1 2 3 4 5])
(partition-by :name [{:name "Antanas"}
                     {:name "Antanas"}
                     {:name "Jonas"}
                     {:name "Ieva"}
                     {:name "Ieva"}
                     {:name "Antanas"}]) ;Danger this goes to different portion

(first [1 2 3])
(nth [1 2 3 4] 3)
(rand-nth [1 2 3 4])
(take-nth 3 [1 2 3 4 5 6]);every nth member
;Similar to Scala for-comprehension

;Positioning
(reverse [1 2 3 4])
(sort > [5 4 2 3])
(sort-by :name [{:name "Antanas"}{:name "Jonas"}{:name "Bernardas"}])

;Producing
(zipmap [1 2 3] ["a" "b" "c"]);Produces map
;Similar to concat.Doesnt change to list. Works with maps.
(set [1 2 3])
(vec #{1 2 3})
(vector 1 2 3)
(list 1 2 3)
(hash-map :a "a" :b "b")
(frequencies [1 2 3 4 4 5]); map of frequencies

(reduce (fn [acc, item] (+ acc item)) [1 2 3 4])
(reduce + [1 2 3 4])

;predicates
(empty? [])
(every? odd? [1 3 5])
(some #{1 3} [2 4]); true or nil
(apply + [1 2 3 4]); looks like reduce

(filter empty? [[] [1 2] [] [3 4]])
(filter (complement empty?) [[] [1 2] [] [3 4]])
(remove empty? [[] [1 2] [] [3 4]])

;find
(first (filter odd? [2 4 1 6 5]))

;lazy sequences
(let [s (lazy-seq (do (Thread/sleep 2000) [1 2 3 4]))]
  s) ;lazy sequences
(repeatedly 5 #(println "test"))
(take 10 (iterate inc 1))
(repeat 5 "test")
(range 1 11)
(range 1 10 2)

;Throwing exceptions
(try
  (throw (ex-info "Message2" {:my-random-data "aaa"}))
  (catch ExceptionInfo e
    (println (.getMessage e))
    (println (ex-data e)))
  (finally (println "Oh finally")))


;Flow structures
(let [name "Antanas"
      age 26]
  (if (= age 26)
    (println name "is 25 years old")
    (println name "is not 25 but is" age)))




"Destructuring"

"In function arguments"
(defn distance [[x1 y1] [x2 y2]]
  (let [square #(* % %)]
    (Math/sqrt (+ (square (- x2 x1)) (square (- y2 y1))))))

"In let"
(let [data ["Antanas"
            "Tieto office"
            "Lead Project Manager"
            "Not interesting info"
            "More not interesting info"]
      [name address job-title & rest] data]
  (println name address job-title (apply str rest)))





















