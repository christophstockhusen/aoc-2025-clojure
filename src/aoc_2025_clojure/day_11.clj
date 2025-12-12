(ns aoc-2025-clojure.day-11
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(def input "aaa: you hhh
you: bbb cccy
bbb: ddd eee
ccc: ddd eee fff
ddd: ggg
eee: out
fff: out
ggg: out
hhh: ccc fff iii
iii: out")

(def input2 "svr: aaa bbb
aaa: fft
fft: ccc
bbb: tty
tty: ccc
ccc: ddd eee
ddd: hub
hub: fff
eee: dac
dac: fff
fff: ggg hhh
ggg: out
hhh: out")

(defn parse-input [input]
  (let [xf (comp (map #(re-seq #"\w{3}" %))
                 (map #(vector (first %) (rest %))))]
    (into {} xf (str/split-lines input))))

(def count-paths
  (memoize
   (fn [graph start target]
     (if (= start target)
       1
       (let [children (get graph start)]
         (reduce + (map #(count-paths graph % target) children)))))))

(defn part-1
  ([] (part-1 (slurp (io/resource "11.txt"))))
  ([input]
   (let [graph (parse-input input)]
     (count-paths graph "you" "out"))))

(defn part-2
  ([] (part-2 (slurp (io/resource "11.txt"))))
  ([input]
   (let [graph (parse-input input)]
     (+ (* (count-paths graph "svr" "fft")
           (count-paths graph "fft" "dac")
           (count-paths graph "dac" "out"))
        (* (count-paths graph "svr" "dac")
           (count-paths graph "dac" "fft")
           (count-paths graph "fft" "out"))))))

