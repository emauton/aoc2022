(ns aoc2022.day4-test
  (:require [clojure.test :refer :all]
            [aoc2022.day4 :refer :all]))

(deftest test-parse-input
  (testing "parse-input works on test input"
    (is (= [[2 4] [6 8]] (parse-input "2-4,6-8")))
    (is (= [[33 90] [78 89]] (parse-input "33-90,78-89")))))

(deftest test-full-overlap?
  (testing "full-overlap? behaves as expected"
    (is (not (full-overlap? [[2 4] [6 8]])))
    (is (not (full-overlap? [[2 3] [4 5]])))
    (is (not (full-overlap? [[5 7] [7 9]])))
    (is      (full-overlap? [[2 8] [3 7]]))
    (is      (full-overlap? [[6 6] [4 6]]))
    (is (not (full-overlap? [[2 6] [4 8]])))))

(deftest test-any-overlap?
  (testing "any-overlap? behaves as expected"
    (is (not (any-overlap? [[2 4] [6 8]])))
    (is (not (any-overlap? [[2 3] [4 5]])))
    (is      (any-overlap? [[5 7] [7 9]]))
    (is      (any-overlap? [[2 8] [3 7]]))
    (is      (any-overlap? [[6 6] [4 6]]))
    (is      (any-overlap? [[2 6] [4 8]]))))
