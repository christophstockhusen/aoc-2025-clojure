(ns aoc-2025-clojure.day-09-test 
  (:require
   [aoc-2025-clojure.day-09 :refer [part-1]]
   [clojure.test :refer [deftest is testing]]))

(def input "7,1
11,1
11,7
9,7
9,5
2,5
2,3
7,3")

(deftest day-09
  (testing "Day 09 - Part 1"
    (is (= 50 (part-1 input)))))
