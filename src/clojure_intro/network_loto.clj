(ns clojure-intro.network-loto
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.util.response :refer [response]]
            [clj-http.client :as c])
  (:import (java.net NetworkInterface)))

;If doesn't work write by hand like this
(def my-ip "localhost")
(comment (def my-ip
  (->>
    (enumeration-seq (NetworkInterface/getNetworkInterfaces))
    (filter #(= (.getName %) "eth0"))
    (first)
    (.getInterfaceAddresses)
    (map #(.getAddress %))
    (map #(.getHostAddress %))
    (filter (partial re-find #"\b\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}\b"))
    (first))))
(def my-address (str my-ip ":3000"))

(def configuration
  {:players     [{:host my-address :name "Antanas"}
                 {:host "localhost:3001" :name "3001"}
                 {:host "localhost:3002" :name "3002"}
                 {:host "localhost:3003" :name "3003"}
                 {:host "localhost:3004" :name "3004"}]
   :probability (/ 1 200)})

;Some state
(def last-loto-result (atom []))
(def game-monitor (Object.))

;Helper functions
(defn- probable-true [probability]
  (< (rand) probability))

(defn- get-json [url]
  (:body (c/get url {:as :json})))

(defn- do-roll [master host]
  (get-json (str "http://" host "/roll?master=" master)))

(defn log! [& msgs]
  (println (java.util.Date.) msgs))

;Endpoints
(defn get-master-configuration [master]
  (get-json (str "http://" master "/configuration")))

(def roll-cnt (atom 0))
(defn roll [master]
  (swap! roll-cnt inc)
  (let [master-cfg (get-master-configuration master)

        pick-random (fn [seq] (nth seq (rand-int (count seq))))
        chosen-player (pick-random (:players master-cfg))

        _ (log! "Choosing player " chosen-player ". Recursion depth:" @roll-cnt)
        roll-result (if (probable-true (:probability master-cfg))
                      {:hops []}
                      (do-roll master (:host chosen-player)))

        find-my-self (fn [master-cfg]
                       (first (filter (fn [p] (.startsWith (:host p) my-address)) (:players master-cfg))))
        my-name (:name (find-my-self master-cfg))

        new-hops (cons [my-name my-address] (:hops roll-result))]
    (swap! roll-cnt dec)
    {:hops new-hops :hop-count (count new-hops)}))

(defn loto-results []
  {:players-left (- (count (:players configuration)) (count @last-loto-result))
   :loto-results @last-loto-result})

(defn loto-results-top []
  {:loto-top (->> (loto-results)
               :loto-results
               (sort-by :hop-count)
               (map #(hash-map :player (first (:hops %)) :hop-count (:hop-count %))))})

(defn start-loto []
  (locking game-monitor
    (reset! last-loto-result [])
    (let [player-hosts (map :host (:players configuration))

          do-roll-mem (fn [& args]
                        (let [roll-result (apply do-roll args)]
                          (swap! last-loto-result conj roll-result)
                          roll-result))

          player-hops (pmap (partial do-roll-mem my-address) player-hosts)]
      {:loto-results player-hops})))

(defroutes app-routes
  (GET "/configuration" [] (response configuration))
  (GET "/roll" [master] (response (roll master)))
  (GET "/start-loto" [] (response (start-loto)))
  (GET "/loto-results" [] (response (loto-results)))
  (GET "/loto-results/top" [] (response (loto-results-top)))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
    (wrap-json-response {:pretty true})
    (wrap-defaults api-defaults)))
