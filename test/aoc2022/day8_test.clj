(ns aoc2022.day8-test
  (:require [clojure.test :refer :all]
            [aoc2022.day8 :refer :all]))

(def test-trees {:max-y 3
                 :max-x 3
                 :map {[0 0] 3 [0 1] 0 [0 2] 3
                       [1 0] 2 [1 1] 5 [1 2] 5
                       [2 0] 6 [2 1] 5 [2 2] 3}})

(deftest test-parse-map
  (testing "tree map parsing"
    (let [test-input ["303" "255" "653"]]
      (is (= test-trees (parse-map test-input))))))

(deftest test-cross-coords
  (testing "cross-coordinate construction"
    (let [expected0 [[[1 0] [1 -1] ]
                     [[1 2] [1 3]]
                     [[0 1] [-1 1]]
                     [[2 1]  [3 1]]]
          expected1 [[[2 1] [2 0] [2 -1]]
                     [[2 3]]
                     [[1 2] [0 2] [-1 2]]
                     [[3 2]]]]
      (is (= expected0 (cross-coords [1 1] 3 3)))
      (is (= expected1 (cross-coords [2 2] 3 3))))))

(deftest test-tree-visible-direction?
  (testing "tree visible in one direction"
    (is (tree-visible-direction? [1 1] [[1 -1] [1 0]] (:map test-trees)))
    (is (not (tree-visible-direction? [1 2] [[1 -1] [1 0] [1 1]] (:map test-trees))))
    (is (not (tree-visible-direction? [1 1] [[1 2] [1 3]] (:map test-trees))))))

(deftest test-tree-visible?
  (testing "tree visible in the map"
    (is (tree-visible? [1 1] test-trees))))

(deftest test-viewing-distance
  (testing "tree viewing distance in one direction"
    (is (= 1 (viewing-distance [1 1] [[1 0] [1 -1]] (:map test-trees))))
    (is (= 0 (viewing-distance [0 0] [[0 -1]] (:map test-trees))))
    (is (= 2 (viewing-distance [2 0] [[2 1] [2 2] [2 3]] (:map test-trees))))))

(deftest test-scenic-score
  (testing "one tree's scenic score"
    (is (= 1 (scenic-score [1 1] test-trees)))))
