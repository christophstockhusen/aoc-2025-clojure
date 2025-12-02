(ns aoc-2025-clojure.day-02
  (:require
   [clojure.java.io :as io]))

(defn invalid? [part s]
  (cond
    (= part :part-1) (re-matches #"^(\d+?)\1$" s)
    (= part :part-2) (re-matches #"^(\d+?)\1+$" s)))

(defn solve
  ([part] (solve part (slurp (io/resource "02.txt"))))
  ([part input]
   (->> input
        (re-seq #"(\d+)-(\d+)")
        (map rest)
        (map #(map parse-long %))
        (mapcat (fn [[a b]] (range a (inc b))))
        (map str)
        (filter (partial invalid? part))
        (map parse-long)
        (reduce +))))

(defn part-1
  ([] (solve :part-1))
  ([input] (solve :part-1 input)))

(defn part-2
  ([] (solve :part-2))
  ([input] (solve :part-2 input)))
