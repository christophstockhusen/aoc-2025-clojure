(ns aoc-2025-clojure.day-06
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn parse-numbers-to-map [numbers]
  (into {}
        (for [[row-idx row] (map-indexed vector numbers)
              [col-idx n]   (map-indexed vector row)]
          [[row-idx col-idx] (parse-long n)])))

(defn parse-input-by-rows [input]
  (let [lines (str/split-lines input)
        numbers (map #(re-seq #"\d+" %) (drop-last lines))
        operators (re-seq #"[+*]" input)]
    {:operators operators
     :rows (dec (count lines))
     :cols (count operators)
     :numbers (parse-numbers-to-map numbers)}))

(defn solve-column [{:keys [numbers rows operators]} col]
  (let [op ({"+" + "*" *} (nth operators col))]
    (reduce op (map #(get numbers [% col]) (range rows)))))

(defn grand-total [{:keys [cols] :as input}]
  (reduce + (map (partial solve-column input) (range cols))))

(defn part-1
  ([] (part-1 (slurp (io/resource "06.txt"))))
  ([input] (grand-total (parse-input-by-rows input))))

(defn parse-input-2 [input]
  (let [lines (str/split-lines input)
        number-lines (vec (drop-last lines))
        operators (re-seq #"[+*]" (last lines))
        cols (apply map vector number-lines)
        col-strings (map #(str/trim (apply str %)) cols)]
    {:operators operators
     :number-groups (->> col-strings
                         (partition-by str/blank?)
                         (remove #(every? str/blank? %))
                         (map #(map parse-long %)))}))

(defn solve [operator numbers]
  (reduce ({"+" + "*" *} operator) numbers))

(defn part-2
  ([] (part-2 (slurp (io/resource "06.txt"))))
  ([input]
   (let [{operators :operators
          number-groups :number-groups} (parse-input-2 input)]
     (reduce + (map solve operators number-groups)))))

