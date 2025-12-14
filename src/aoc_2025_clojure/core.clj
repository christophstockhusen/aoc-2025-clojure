(ns aoc-2025-clojure.core
  (:gen-class)
  (:require
   [aoc-2025-clojure.day-01 :as day-01]
   [aoc-2025-clojure.day-02 :as day-02]
   [aoc-2025-clojure.day-03 :as day-03]
   [aoc-2025-clojure.day-04 :as day-04]
   [aoc-2025-clojure.day-05 :as day-05]
   [aoc-2025-clojure.day-06 :as day-06]
   [aoc-2025-clojure.day-07 :as day-07]
   [aoc-2025-clojure.day-10 :as day-10]
   [aoc-2025-clojure.day-11 :as day-11]))

(defn -main []
  (println "Day 01 - Part 1:" (day-01/part-1))
  (println "Day 01 - Part 2:" (day-01/part-2))
  (println "Day 02 - Part 1:" (day-02/part-1))
  (println "Day 02 - Part 2:" (day-02/part-2))
  (println "Day 03 - Part 1:" (day-03/part-1))
  (println "Day 03 - Part 2:" (day-03/part-2))
  (println "Day 04 - Part 1:" (day-04/part-1))
  (println "Day 04 - Part 2:" (day-04/part-2))
  (println "Day 05 - Part 1:" (day-05/part-1))
  (println "Day 05 - Part 2:" (day-05/part-2))
  (println "Day 06 - Part 1:" (day-06/part-1))
  (println "Day 06 - Part 2:" (day-06/part-2))
  (println "Day 07 - Part 1:" (day-07/part-1))
  (println "Day 07 - Part 2:" (day-07/part-2))
  (println "Day 10 - Part 1:" (day-10/part-1))
  (println "Day 10 - Part 2:" (day-10/part-2))
  (println "Day 11 - Part 1:" (day-11/part-1))
  (println "Day 11 - Part 2:" (day-11/part-2)))
