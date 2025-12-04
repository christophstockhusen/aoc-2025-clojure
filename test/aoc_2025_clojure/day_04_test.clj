(ns aoc-2025-clojure.day-04-test 
  (:require
   [aoc-2025-clojure.day-04 :refer [part-1 part-2]]
   [clojure.test :refer [deftest is testing]]))

(def input "..@@.@@@@.
@@@.@.@.@@
@@@@@.@.@@
@.@@@@..@.
@@.@@@@.@@
.@@@@@@@.@
.@.@.@.@@@
@.@@@.@@@@
.@@@@@@@@.
@.@.@@@.@.")

(deftest day-04
  (testing "Day 04 - Part 1"
    (is (= 13 (part-1 input))))
  (testing "Day 04 - Part 2"
    (is (= 43 (part-2 input)))))
