(ns aoc-2025-clojure.day-01-test
  (:require [clojure.test :refer :all]
            [aoc-2025-clojure.day-01 :refer :all]))

(def input
"L68
L30
R48
L5
R60
L55
L1
L99
R14
L82")

(deftest day-01
  (testing "Day 01 - Part 1"
    (is (= 3 (part-1 input))))
  (testing "Day 01 - Part 2"
    (is (= 6 (part-2 input)))))
