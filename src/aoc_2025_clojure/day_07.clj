(ns aoc-2025-clojure.day-07
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn get-indices [s line]
  (keep-indexed (fn [i c] (when (= (str c) s) i)) line))

(defn get-start [line] (first (get-indices "S" line)))

(defn get-splitters [line] (get-indices "^" line))

(defn parse-input [input]
  (let [lines (str/split-lines input)
        start (get-start (first lines))
        splitters (remove empty? (map get-splitters (drop-last lines)))]
    {:start start
     :splitters splitters}))

(defn split-beam [cnt pos]
  {(dec pos) cnt
   (inc pos) cnt})

(defn split [beams splitters]
  (let [splitters (set splitters)
        splits (count (filter #(contains? splitters %) (keys beams)))]
    [(apply merge-with + (map (fn [[pos cnt]]
                                (if (contains? splitters pos)
                                  (split-beam cnt pos)
                                  {pos cnt}))
                              beams))
     splits]))

(defn run-simulation [{:keys [start splitters]}]
  (reduce
   (fn [[beams splits] current-splitters]
     (let [[new-beams new-splits] (split beams current-splitters)]
       [new-beams (+ splits new-splits)]))
   [{start 1} 0]
   splitters))

(defn part-1
  ([] (part-1 (slurp (io/resource "07.txt"))))
  ([input]
   (-> input parse-input run-simulation second)))

(defn part-2
  ([] (part-2 (slurp (io/resource "07.txt"))))
  ([input]
   (->> (parse-input input)
        run-simulation
        first
        vals
        (reduce + 0))))
