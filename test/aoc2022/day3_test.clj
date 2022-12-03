(ns aoc2022.day3-test
  (:require [clojure.test :refer :all]
            [aoc2022.day3 :refer :all]))

(deftest test-common-in-rucksack
  (testing "common-in-rucksack works on test input"
    (is (= \p (common-in-rucksack "vJrwpWtwJgWrhcsFMMfFFhFp")))
    (is (= \L (common-in-rucksack "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL")))
    (is (= \P (common-in-rucksack "PmmdzqPrVvPwwTWBwg")))
    (is (= \v (common-in-rucksack "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn")))))

(deftest test-priority
  (testing "priority calculates correctly"
    (is (= 16 (priority \p)))
    (is (= 38 (priority \L)))
    (is (= 42 (priority \P)))
    (is (= 22 (priority \v)))))
