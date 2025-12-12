(ns aoc-2025-clojure.day-11-test 
  (:require
   [aoc-2025-clojure.day-11 :refer [part-1 part-2]]
   [clojure.test :refer [deftest is testing]]))

(def input "aaa: you hhh
you: bbb ccc
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

(deftest day-11
  (testing "Day 11 - Part 1"
    (is (= 5 (part-1 input))))
  (testing "Day 11 - Part 2"
    (is (= 2 (part-2 input2)))))
