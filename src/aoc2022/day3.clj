(ns aoc2022.day3
  (:require [aoc2022.util :as util]
            clojure.set))

(defn common-item
  "Find one common item between all input sequences"
  [seqs]
  (first (apply clojure.set/intersection (map set seqs))))

(defn common-in-rucksack
  "Find the common letter between the two halves of the input rucksack"
  [input]
  (let [halves (partition (/ (count input) 2) input)]
    (common-item halves)))

(defn priority
  "Return the priority of the input item"
  [item]
  (let [ord (int item)]
    (if (> ord 96)
      (- ord 96)
      (+ 26 (- ord 64)))))

(defn main
  "Day 3 of Advent of Code 2022: Rucksack Reorganization
       lein run day3 <input>
   where <input> is a filename in resources/"
  [[filename]]
  (let [rucksacks (util/read-lines filename)
        items (map common-in-rucksack rucksacks)
        groups (partition 3 rucksacks)
        badges (map common-item groups)]
    (println "sum of item priorities:" (reduce + (map priority items)))
    (println "sum of badge priorities:" (reduce + (map priority badges)))))
