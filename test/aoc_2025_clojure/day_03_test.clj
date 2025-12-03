(ns aoc-2025-clojure.day-03-test
  (:require
   [aoc-2025-clojure.day-03 :refer [part-1 part-2]]
   [clojure.test :refer [deftest is testing]]))

(def input
  "987654321111111
811111111111119
234234234234278
818181911112111")

(deftest day-03
  (testing "Day 03 - Part 1"
    (is (= 357 (part-1 input))))
  (testing "Day 03 - Part 2"
    (is (= 3121910778619 (part-2 input)))))
