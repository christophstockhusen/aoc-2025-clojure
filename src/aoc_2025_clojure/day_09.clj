(ns aoc-2025-clojure.day-09
  (:require
   [clojure.java.io :as io]))

(def input "7,1
11,1
11,7
9,7
9,5
2,5
2,3
7,3")

(defn parse-input [input]
  (->> (re-seq #"\d+" input)
       (map parse-long)
       (partition 2)))

(defn area [[i1 j1] [i2 j2]]
  (* (inc (abs (- i1 i2)))
     (inc (abs (- j1 j2)))))

(defn largest-area [corners]
  (->> (for [c1 corners
             c2 corners]
         (area c1 c2))
       (apply max)))

(defn part-1
  ([] (part-1 (slurp (io/resource "09.txt"))))
  ([input] (largest-area (parse-input input))))

(println (part-1))
