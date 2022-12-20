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
    (is (= (new-state) (apply-op (new-state) [:ls nil])))  ; ls is a no-op
    (is (= (new-state) (apply-op (merge (new-state) {:cwd ["nonexistent"]})
                                 [:cd "/"])))
    (is (= (new-state) (apply-op (merge (new-state) {:cwd ["a"]})
                                 [:cd ".."])))
    (is (= (merge (new-state) {:cwd ["a"]}) (apply-op (merge (new-state) {:cwd ["a" "b"]})
                                                      [:cd ".."])))
    (is (= (merge (new-state) {:cwd ["a" "b"]}) (apply-op (merge (new-state) {:cwd ["a"]})
                                                          [:cd "b"])))
    (is (= (merge (new-state) {:paths {[] #{} ["a"] #{}}}) (apply-op (new-state)
                                                                     [:dir "a"])))
    (is (= (merge (new-state) {:paths {[] #{} ["a"] #{["f" 29116]}} :cwd ["a"]}) (apply-op {:paths {[] #{} ["a"] #{}} :cwd ["a"]}
                                                                                           [:file ["f" 29116]])))
    (is (= (merge (new-state) {:paths {[] #{["f" 29116]}}}) (apply-op (new-state)
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
           (total-sizes {:paths {[] #{["file0" 3] ["file1" 7]}} :cwd []})))
    (is (= {[] 22 ["a"] 12}
           (total-sizes {:paths {[] #{["file0" 3] ["file1" 7]}
                                 ["a"] #{["file2" 4] ["file3" 8]}}
                         :cwd []})))
    (is (= {[] 30 ["a"] 20 ["a" "b"] 8}
           (total-sizes {:paths {[] #{["file0" 3] ["file1" 7]}
                                 ["a"] #{["file2" 4] ["file3" 8]}
                                 ["a" "b"] #{["file4" 8]}}
                         :cwd []})))))
