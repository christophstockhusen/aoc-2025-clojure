(ns aoc-2025-clojure.day-10
  (:require
   [clojure.java.io :as io]
   [clojure.math :as math]
   [clojure.math.combinatorics :as combo]
   [clojure.string :as str])
  (:import
   (org.ojalgo.optimisation ExpressionsBasedModel)))

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

(defn fewest-required-presses-lights [{:keys [lights buttons]}]
  (->> (combo/subsets buttons)
       (map #(vector (count %) (merge-buttons %)))
       (filter #(= lights (second %)))
       (map first)
       first))

(defn part-1
  ([] (part-1 (slurp (io/resource "10.txt"))))
  ([input]
   (->> (parse-input input)
        (map fewest-required-presses-lights)
        (reduce +))))

(defn fewest-required-presses-joltages [{:keys [buttons joltages]}]
  (let [m (ExpressionsBasedModel.)
        buttons-indexed (map-indexed vector buttons)
        joltages-indexed (map-indexed vector joltages)]
    (doseq [[idx _] buttons-indexed]
      (.. m (newVariable (str "Button" idx))
          (integer true)
          (lower 0)
          (weight 1)))
    (doseq [[joltage-idx joltage] joltages-indexed]
      (let [expression (.. m (addExpression (str "Joltage" joltage-idx))
                           (lower joltage)
                           (upper joltage))]
        (doseq [[button-idx button] buttons-indexed
                affected-joltage-idx button
                :when (= joltage-idx affected-joltage-idx)]
          (.set expression button-idx 1))))
    (math/round (.. m minimise getValue))))

(defn part-2
  ([] (part-2 (slurp (io/resource "10.txt"))))
  ([input]
   (->> (parse-input input)
        (map fewest-required-presses-joltages)
        (reduce +))))

