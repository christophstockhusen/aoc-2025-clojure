(ns aoc-2025-clojure.day-05-test 
  (:require
   [aoc-2025-clojure.day-05 :refer [part-1 part-2]]
   [clojure.test :refer [deftest is testing]]))

(def input "3-5
10-14
16-20
12-18

1
5
8
11
17
32")

(deftest day-05
  (testing "Day 05 - Part 1"
    (is (= 3 (part-1 input))))
  (testing "Day 05 - Part 2"
    (is (= 14 (part-2 input)))))
