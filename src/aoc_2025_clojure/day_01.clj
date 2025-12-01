(ns aoc-2025-clojure.day-01
  (:require
   [clojure.java.io :as io]))

(defn parse-input [input]
  (->> (re-seq #"(L|R)(\d+)" input)
       (map (fn [[_ d v]]
              (let [v (parse-long v)]
                (if (= "L" d) (- v) v))))))

(defn next-pos [pos delta]
  (mod (+ pos delta) 100))

(defn apply-rotation [{pos :pos} delta]
  (let [np (next-pos pos delta)]
    {:zeros (+ (quot (abs delta) 100)
               (if (and (not (zero? pos))
                        (or (and (pos? delta) (< np pos) (not (zero? np)))
                            (and (neg? delta) (< pos np) (not (zero? np)))
                            (and (pos? delta) (< np pos) (zero? np))
                            (and (neg? delta) (< np pos) (zero? np))))
                 1
                 0))
     :pos np}))

(defn part-1
  ([] (part-1 (slurp (io/resource "01.txt"))))
  ([input]
   (->> (parse-input input)
        (reductions apply-rotation {:pos 50})
        (map :pos)
        (filter zero?)
        (count))))

(defn part-2
  ([] (part-2 (slurp (io/resource "01.txt"))))
  ([input]
   (->> (parse-input input)
        (reductions apply-rotation {:pos 50 :zeros 0})
        (map :zeros)
        (reduce +))))
