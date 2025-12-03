(ns aoc-2025-clojure.core
  (:gen-class)
  (:require
   [aoc-2025-clojure.day-01 :as day-01]
   [aoc-2025-clojure.day-02 :as day-02]
   [aoc-2025-clojure.day-03 :as day-03]))

(defn -main []
  (println "Day 01 - Part 1:" (day-01/part-1))
  (println "Day 01 - Part 2:" (day-01/part-2))
  (println "Day 02 - Part 1:" (day-02/part-1))
  (println "Day 02 - Part 2:" (day-02/part-2))
  (println "Day 03 - Part 1:" (day-03/part-1))
  (println "Day 03 - Part 2:" (day-03/part-2)))
