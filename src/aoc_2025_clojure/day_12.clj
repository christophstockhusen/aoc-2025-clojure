(ns aoc-2025-clojure.day-12
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn parse-shape [input]
  (let [lines (str/split-lines input)
        no (parse-long (re-find #"\d+" (first lines)))
        size (count (re-seq #"#" input))]
    {no size}))

(defn parse-shapes [input]
  (into {} (map parse-shape input)))

(defn parse-region [input]
  (let [[format quantities] (str/split input #": ")
        [width length] (map parse-long (str/split format #"x"))
        quantities (into {} (map-indexed #(vector %1 %2)
                                         (map parse-long (str/split quantities #" "))))]
    {:width width
     :length length
     :quantities quantities}))

(defn parse-regions [input]
  (map parse-region (str/split-lines input)))

(defn parse-input [input]
  (let [blocks (str/split input #"\n\n")
        shapes (drop-last blocks)
        regions (last blocks)]
    {:shapes (parse-shapes shapes)
     :regions (parse-regions regions)}))

(defn fits? [shapes {:keys [width length quantities]}]
  (let [size (* width length)]
    (<= (reduce + (map (fn [[no cnt]] (* cnt (get shapes no)))
                       quantities))
        size)))

(defn part-1
  ([] (part-1 (slurp (io/resource "12.txt"))))
  ([input]
   (let [{shapes :shapes regions :regions} (parse-input input)]
     (count (filter (partial fits? shapes) regions)))))
