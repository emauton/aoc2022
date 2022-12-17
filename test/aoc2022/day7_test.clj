(ns aoc2022.day7-test
  (:require [clojure.test :refer :all]
            [aoc2022.day7 :refer :all]
            [aoc2022.util :as util]))

(deftest test-parse-op
  (testing "parsing ops"
    (is (= [:cd "/"] (parse-op "$ cd /")))
    (is (= [:ls nil ] (parse-op "$ ls")))
    (is (= [:dir "a"] (parse-op "dir a")))
    (is (= [:file ["f", 29116]] (parse-op "29116 f")))))

(deftest test-apply-op
  (testing "applying ops"
    (is (= [{} []] (apply-op [{} []]
                             [:ls nil])))  ; ls is a no-op
    (is (= [{} []] (apply-op [{} ["nonexistent"]]
                             [:cd "/"])))
    (is (= [{} []] (apply-op [{} ["a"]]
                             [:cd ".."])))
    (is (= [{} ["a"]] (apply-op [{} ["a" "b"]]
                                [:cd ".."])))
    (is (= [{} ["a" "b"]] (apply-op [{} ["a"]]
                                    [:cd "b"])))
    (is (= [{["a"] #{}} []] (apply-op [{} []]
                                      [:dir "a"])))
    (is (= [{["a"] #{["f" 29116]}} ["a"]] (apply-op [{["a"] #{}} ["a"]]
                                                    [:file ["f" 29116]])))
    (is (= [{[] #{["f" 29116]}} []] (apply-op (new-state)
                                              [:file ["f" 29116]])))))

(deftest test-walk-to-root
  (testing "walk-to-root returns this path and parent paths"
    (is (= [[]]
           (walk-to-root [])))
    (is (= [["a" "b"] ["a"] []]
           (walk-to-root ["a" "b"])))))

(deftest test-path-sizes
  (testing "path-sizes sums path correctly"
    (is (= {[] 10}
           (path-sizes [] #{["file0" 3] ["file1" 7]})))
    (is (= {[] 10 ["a"] 10 ["a" "b"] 10}
           (path-sizes ["a" "b"] #{["file0" 3] ["file1" 7]})))))

(deftest test-total-sizes
  (testing "total-sizes sums entire path set correctly"
    (is (= {[] 10}
           (total-sizes {[] #{["file0" 3] ["file1" 7]}})))
    (is (= {[] 22 ["a"] 12}
           (total-sizes {[] #{["file0" 3] ["file1" 7]}
                         ["a"] #{["file2" 4] ["file3" 8]}})))
    (is (= {[] 30 ["a"] 20 ["a" "b"] 8}
           (total-sizes {[] #{["file0" 3] ["file1" 7]}
                         ["a"] #{["file2" 4] ["file3" 8]}
                         ["a" "b"] #{["file4" 8]}})))))
