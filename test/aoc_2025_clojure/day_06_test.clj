(ns aoc-2025-clojure.day-06-test 
  (:require
   [aoc-2025-clojure.day-06 :refer [part-1 part-2]]
   [clojure.test :refer [deftest is testing]]))

(def input "123 328  51 64 
 45 64  387 23 
  6 98  215 314
*   +   *   +  ")

(deftest day-06
  (testing "Day 06 - Part 1"
    (is (= 4277556 (part-1 input))))
  (testing "Day 06 - Part 2"
    (is (= 3263827 (part-2 input)))))
