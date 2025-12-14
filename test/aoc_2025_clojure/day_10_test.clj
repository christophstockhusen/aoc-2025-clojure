(ns aoc-2025-clojure.day-10-test 
  (:require
   [aoc-2025-clojure.day-10 :refer [part-1 part-2]]
   [clojure.test :refer [deftest is testing]]))

(def input "[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}")

(deftest day-10
  (testing "Day 01 - Part 1"
    (is (= 7 (part-1 input))))
  (testing "Day 01 - Part 2"
    (is (= 33 (part-2 input)))))
