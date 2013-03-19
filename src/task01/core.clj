(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))


(defn get-links []
  (map #(((nth % 2) 1) :href)            ;locating h3/a/href inside coll with all h3 with hrefs
       (filter coll? ((fn smart-flat [x] ;filtering the output of the smart-flat fn applied to the parsed html
                                         ;  smart-flat smartly flattens everything passed except h3 with {:class "r"}
                        (if (and (coll? x) (not= (second x) {:class "r"})) 
                          (mapcat smart-flat x)
                          [x]))
                      (parse "clojure_google.html")))))

(defn -main []
  (println (str "Found " (count (get-links)) " links!")))
