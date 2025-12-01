(ns aoc-2025-clojure.core
  (:gen-class)
  (:require [aoc-2025-clojure.day-01 :as day-01]))

(defn -main []
  (println "Day 01 - Part 1:" (day-01/part-1))
  (println "Day 01 - Part 2:" (day-01/part-2)))
