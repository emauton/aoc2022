(ns aoc2022.day5-test
  (:require [clojure.test :refer :all]
            [aoc2022.day5 :refer :all]
            [aoc2022.util :as util]))

(def stack {1 '(\N \Z)
            2 '(\D \C \M)
            3 '(\P)})

(deftest test-move-1
  (testing "move-n manipulates stacks correctly for 1 item"
    (is (= {1 '(\D \N \Z)
            2 '(\C \M)
            3 '(\P)}
           (move-n stack 1 2 1)))))

(deftest test-move-3
  (testing "move-n manipulates stacks correctly for >1 item"
    (is (= {1 '(\N \Z)
            2 '()
            3 '(\D \C \M \P)}
           (move-n stack 3 2 3)))))

(deftest test-move-9000
  (testing "move-9000 moves multiple crates correctly"
    (is (= {1 '(\N \Z)
            2 '()
            3 '(\M \C \D \P)}
           (move-9000 stack [3 2 3])))))
