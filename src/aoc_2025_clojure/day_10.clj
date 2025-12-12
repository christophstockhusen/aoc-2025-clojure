(ns aoc-2025-clojure.day-10
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]
   [clojure.math.combinatorics :as combo]))

(def input "[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}")

(defn parse-lights [lights]
  (set (keep-indexed #(when (= %2 "#") %1) (str/split lights #""))))

(defn parse-buttons [buttons]
  (->> (re-seq #"\((.*?)\)" buttons)
       (map second)
       (map #(str/split % #","))
       (map #(map parse-long %))
       (map set)))

(defn parse-joltages [joltages]
  (map parse-long (str/split joltages #",")))

(defn parse-machine [line]
  (let [[[_ lights buttons joltages]] (re-seq #"\[([.#]+)\] (.*) \{(.*)\}"  line)]
    {:lights (parse-lights lights)
     :buttons (parse-buttons buttons)
     :joltages (parse-joltages joltages)}))

(defn parse-input [input]
  (map parse-machine (str/split-lines input)))

(defn merge-buttons [buttons]
  (->> (apply concat buttons)
       frequencies
       (filter #(odd? (second %)))
       keys
       set))

(defn fewest-required-presses [{:keys [lights buttons]}]
  (->> (combo/subsets buttons)
       (map #(vector (count %) (merge-buttons %)))
       (filter #(= lights (second %)))
       (map first)
       first))

(defn part-1
  ([] (part-1 (slurp (io/resource "10.txt"))))
  ([input]
   (->> (parse-input input)
        (map fewest-required-presses)
        (reduce +))))

