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

;TODO describe apply
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

;Collections
(defn give-me-some-vector []
  [1 2 3 4 5])

;TODO comma thing
(defn give-me-some-list []
    '(1 2 3 4 5))

(defn give-me-some-maps []
  ;return vector containing hashmaps and sorted maps
  [{1 2 3 4} (sorted-map 4 6 2 5)])

(defn give-me-some-sets []
  ;return vector containing hashsets and sorted sets
  [#{1 2 3 4 5} (sorted-set 2 3 1 5)])

(defn make-list [e1 & rest]
  (apply list (conj rest e1)))

(defn make-vector [e1 & rest]
  (vec (conj rest e1)))

(defn make-hash-map [& entries]
  (apply hash-map entries))

(defn make-set [& values]
  (set values))

