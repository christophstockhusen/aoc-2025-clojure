(ns aoc-2025-clojure.day-05
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn parse-ranges [input]
  (->> (str/split-lines input)
       (map #(str/split % #"-"))
       (mapv (partial mapv parse-long))))

(defn parse-ids [input]
  (->> (str/split-lines input)
       (mapv parse-long)))

(defn parse-input [input]
  (let [[ranges ids] (str/split input #"\n\n")]
    [(parse-ranges ranges)
     (parse-ids ids)]))

(defn fresh? [ranges id]
  (some (fn [[r1 r2]] (<= r1 id r2)) ranges))

(defn count-fresh [ranges ids]
  (count (filter (partial fresh? ranges) ids)))

(defn part-1
  ([] (part-1 (slurp (io/resource "05.txt"))))
  ([input]
   (let [[ranges ids] (parse-input input)]
     (count-fresh ranges ids))))

(defn overlap? [[r1a r1b] [r2a _]]
  (<= r1a r2a r1b))

(defn merge-ranges [[r1a r1b] [r2a r2b]]
  [(min r1a r2a) (max r1b r2b)])

(defn compress-ranges [ranges]
  (let [sorted (sort ranges)]
    (loop [[r1 r2 & ranges] sorted
           compressed []]
      (cond
        (nil? r1) compressed
        (nil? r2) (conj compressed r1)
        :else (if (overlap? r1 r2)
                (recur (conj ranges (merge-ranges r1 r2)) compressed)
                (recur (conj ranges r2) (conj compressed r1)))))))

(defn part-2
  ([] (part-2 (slurp (io/resource "05.txt"))))
  ([input]
   (let [[ranges _] (parse-input input)]
     (->> (compress-ranges ranges)
          (map (fn [[r1 r2]] (inc (- r2 r1))))
          (reduce +)))))
