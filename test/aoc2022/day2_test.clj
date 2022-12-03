(ns aoc2022.day2-test
  (:require [clojure.test :refer :all]
            [aoc2022.day2 :refer :all]))

(deftest test-score
  (testing "score works on test input"
    (is (= 8 (score [:rock :paper])))
    (is (= 1 (score [:paper :rock])))
    (is (= 6 (score [:scissors :scissors])))))

(deftest test-satisfy
  (testing "satisfy works on test input"
    (let [lose (satisfy ["A" "X"])
          draw (satisfy ["A" "Y"])
          win  (satisfy ["A" "Z"])]
      (is (= 0 (get game-scores lose)))
      (is (= 3 (get game-scores draw)))
      (is (= 6 (get game-scores win))))))
