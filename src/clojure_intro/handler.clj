(ns clojure-intro.handler
  (:require [ring.util.response :refer [response]]))

(def say-hello {:greeting "Hello"})
(def say-goodbye {:greeting "Goodbye"})

(defn naive-routes [uri]
  (cond
    (.startsWith uri "/hello") say-hello
    (.startsWith uri "/goodbye") say-goodbye))

(defn handler [request]
  (response (naive-routes (:uri request))))

(defn custom-middleware
  ([handler] (custom-middleware handler {}))
  ([handler options]
   (fn [request]
     (let [response (handler request)]
       (assoc-in response [:headers "my-awesome-header"] "Clojure is fun")))))

(use 'ring.middleware.params)
(use 'ring.middleware.json)
(def app
  (-> handler
    custom-middleware
    wrap-json-response
    wrap-params))