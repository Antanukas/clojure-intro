(ns clojure-intro.presentation)

;toggle structural editing to disable
;(use '[clojure-intro.presentation] :reload)

"What is functional language?"

(def functional-language-features
  [:first-class-functions "Functions can be passed as arguments to other functions"

   :higher-order-functions "Functions accepting other functions as parameters"

   :pure-functions "Functions without side-effects. Alayws return same result when given same input"

   :looping-with-recursion "Looping is done using recursion. No for, while, repeat etc."

   :emphasizes-lazy-evalution "Lazyness everywhere"

   :immutable-state "When appending to a list new list with appended element is returned"])

"Clojure has all this and additional provides safe way to represent state using STM in conccurent application"







"Some history"

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
(meta +)
(with-meta two-arg-println {:doc "Example of function definition"})
(defn ^{:doc "Example of calling functions"} some-documented-function [] (println "Hi"))
(defn some-doc-fn  "This is a documentation" [] (println "Me is some function"))
(defn ^String returns-string [^Integer i ^String text] (println i text))

; true or false?
(and true "I am true")
(and false "I am not true")
(and nil "I am not true")































