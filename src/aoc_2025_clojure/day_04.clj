(ns aoc-2025-clojure.day-04
  (:require
   [clojure.java.io :as io]
   [clojure.set :as set]
   [clojure.string :as str]))

(defn parse-line [line row-no]
  (keep-indexed (fn [col-no c] (if (= c "@") [row-no col-no]))
                (str/split line #"")))

(defn parse-input [input]
  (set (mapcat parse-line (str/split-lines input) (range))))

(defn neighbors [[i j]]
  (->>
   (for [di [-1 0 1]
         dj [-1 0 1]
         :when (not (and (zero? di) (zero? dj)))]
     (mapv + [i j] [di dj]))
   (set)))

(defn is-accessible?
  [rolls roll]
  (let [ns (neighbors roll)]
    (< (count (filter identity (map (partial contains? rolls) ns))) 4)))

(defn part-1
  ([] (part-1 (slurp (io/resource "04.txt"))))
  ([input]
   (let [rolls (parse-input input)]
     (count (filter (partial is-accessible? rolls) rolls)))))

(defn part-2
  ([] (part-2 (slurp (io/resource "04.txt"))))
  ([input]
   (loop [rolls (parse-input input)
          removed 0]
     (let [accessible (set (filter (partial is-accessible? rolls) rolls))]
       (if (pos? (count accessible))
         (recur (set/difference rolls accessible)
                (+ removed (count accessible)))
         removed)))))
