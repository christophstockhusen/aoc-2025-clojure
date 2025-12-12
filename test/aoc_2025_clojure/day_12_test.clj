(ns aoc-2025-clojure.day-12-test 
  (:require
   [aoc-2025-clojure.day-12 :refer [part-1]]
   [clojure.test :refer [deftest is testing]]))

(def input "0:
###
##.
##.

1:
###
##.
.##

2:
.##
###
##.

3:
##.
###
##.

4:
###
#..
###

5:
###
.#.
###

4x4: 0 0 0 0 2 0
12x5: 1 0 1 0 2 2
12x5: 1 0 1 0 3 2")

(deftest day-12
  (testing "Day 12 - Part 1 (adjusted to heuristic)"
    (is (= 3 (part-1 input)))))
