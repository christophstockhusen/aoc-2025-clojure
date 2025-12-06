(ns aoc-2025-clojure.day-06
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn parse-number-row [ns row]
  (map (fn [n col] [[row col] (parse-long n)]) ns (range)))

(defn parse-numbers-to-map [numbers]
  (into {} (apply concat (map (fn [ns row] (parse-number-row ns row))
                              numbers
                              (range)))))

(defn parse-input-by-rows [input]
  (let [lines (str/split-lines input)
        numbers (map #(re-seq #"\d+" %) (drop-last lines))
        operators (re-seq #"[+*]" input)]
    {:operators operators
     :rows (dec (count lines))
     :cols (count operators)
     :numbers (parse-numbers-to-map numbers)}))

(defn solve-column [{numbers :numbers rows :rows operators :operators} col]
  (let [op (nth operators col)
        op (if (= op "+") + *)]
    (reduce op (map #(get numbers [% col]) (range rows)))))

(defn grand-total [{cols :cols :as input}]
  (reduce + (map (partial solve-column input) (range cols))))

(defn part-1
  ([] (part-1 (slurp (io/resource "06.txt"))))
  ([input] (grand-total (parse-input-by-rows input))))

(defn parse-input-2 [input]
  (let [lines (str/split-lines input)
        number-lines (map #(str/split % #"") (drop-last lines))
        operators (re-seq #"[+*]" input)
        cols (apply (partial map str) number-lines)
        cols (map str/trim cols)]
    {:operators operators
     :number-groups (loop [groups []
                           current []
                           [col & cols] cols]
                      (if (nil? col)
                        (conj groups current)
                        (if (empty? col)
                          (recur (conj groups current) [] cols)
                          (recur groups (conj current (parse-long col)) cols))))}))

(defn solve [operator numbers]
  (if (= operator "+")
    (reduce + numbers)
    (reduce * numbers)))

(defn part-2
  ([] (part-2 (slurp (io/resource "06.txt"))))
  ([input]
   (let [{operators :operators
          number-groups :number-groups} (parse-input-2 input)]
     (reduce + (map solve operators number-groups)))))

