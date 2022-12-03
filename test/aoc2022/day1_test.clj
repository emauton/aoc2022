(ns aoc2022.day1-test
  (:require [clojure.test :refer :all]
            [aoc2022.day1 :refer :all]))

(deftest test-count-calories
  (testing "count-calories works on test input"
    (let [input ["1000"
                 "2000"
                 "3000"
                 ""
                 "4000"
                 ""
                 "5000"
                 "6000"
                 ""
                 "7000"
                 "8000"
                 "9000"
                 ""
                 "10000"]
          counts (calorie-counts input)]
      (is (= counts [24000 11000 10000 6000 4000])))))
