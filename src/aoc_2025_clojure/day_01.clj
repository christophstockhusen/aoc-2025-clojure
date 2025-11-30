(ns aoc-2025-clojure.day-01 
  (:require
   [clojure.java.io :as io]))

(defn part-1
  ([] (part-1 (slurp (io/resource "01.txt"))))
  ([input] (println input)))
