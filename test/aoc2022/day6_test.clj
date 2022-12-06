(ns aoc2022.day6-test
  (:require [clojure.test :refer :all]
            [aoc2022.day6 :refer :all]
            [aoc2022.util :as util]))

(deftest test-detect-marker-4
  (testing "detect marker finds the right marker for 4 distinct"
    (is (= 7  (detect-marker "mjqjpqmgbljsphdztnvjfqwrcgsmlb"    4)))
    (is (= 5  (detect-marker "bvwbjplbgvbhsrlpgdmjqwftvncz"      4)))
    (is (= 6  (detect-marker "nppdvjthqldpwncqszvftbrmjlhg"      4)))
    (is (= 10 (detect-marker "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" 4)))
    (is (= 11 (detect-marker "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"  4)))))

(deftest test-detect-marker-14
  (testing "detect marker finds the right marker for 14 distinct"
    (is (= 19 (detect-marker "mjqjpqmgbljsphdztnvjfqwrcgsmlb"    14)))
    (is (= 23 (detect-marker "bvwbjplbgvbhsrlpgdmjqwftvncz"      14)))
    (is (= 23 (detect-marker "nppdvjthqldpwncqszvftbrmjlhg"      14)))
    (is (= 29 (detect-marker "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" 14)))
    (is (= 26 (detect-marker "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"  14)))))
