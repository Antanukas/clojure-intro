(ns clojure-intro.basics)

;numerics
(defn is-odd? [x]
  (= 1 (mod x 2)))

(defn sum [x y]
  (+ x y))

(defn subtr [x y]
  (- x y))

(defn divide [x y]
  (/ x y))

(defn multiply [& args]
  (apply * args))

(defn increase-by-one [x]
  (inc x))

;strings
(defn concat-two-strings [s1 s2]
  (str s1 s2))

(defn my-reverse [s1]
  (clojure.string/reverse s1))

;booleans
(defn is-true? [x]
  (true? x))

(defn my-not [x]
  (not x))

(defn my-and [x y]
  (and x y))

(defn my-or [x y]
  (or x y))

(defn my-is-nil? [x]
  (nil? x))

;Hint use smth from above
(defn first-not-null [& args]
  (some identity args))