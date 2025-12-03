(ns aoc-2025-clojure.day-03
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(def input
  "987654321111111
811111111111119
234234234234278
818181911112111")

(defn parse-line [line] (map parse-long (str/split line #"")))

(defn parse-input [input] (map parse-line (str/split-lines input)))

(defn max-joltage [n bank]
  (->> (loop [n n
              bank bank
              batteries []]
         (if (= 0 n)
           batteries
           (let [length (count bank)
                 left (take (- length (dec n)) bank)
                 max-left (apply max left)
                 idx (first (keep-indexed #(if (= %2 max-left) %1) left))
                 right (drop (inc idx) bank)]
             (recur (dec n) right (conj batteries max-left)))))
       (apply str)
       (parse-long)))

(defn solve
  ([part] (solve part (slurp (io/resource "03.txt"))))
  ([part input]
   (let [numerals (if (= part :part-1) 2 12)]
     (reduce + (map (partial max-joltage numerals) (parse-input input))))))

(defn part-1
  ([] (solve :part-1))
  ([input] (solve :part-1 input)))

(defn part-2
  ([] (solve :part-2))
  ([input] (solve :part-2 input)))
