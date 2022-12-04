(ns aoc2022.day4
  (:require [aoc2022.util :as util]
            [clojure.string :as string]))

(defn parse-input
  "Parse string form 2-4,6-8 into pairs [[2 4] [6 8]]"
  [line]
  (let [fields (string/split line #"[,-]")
        integers (map #(Integer/parseInt %) fields)]
    (partition 2 integers)))

(defn range-contains?
  "Given two int ranges a b, return true if a completely overlaps b"
  [[a1 a2] [b1 b2]]
  (and (<= a1 b1) (>= a2 b2)))

(defn full-overlap?
  "Given two int ranges, return true if either completely overlaps the other"
  [[a b]]
  (or (range-contains? a b) (range-contains? b a)))

(defn range-overlap?
  "Given two int ranges a b, return true if b overlaps a at all"
  [[a1 a2] [b1 b2]]
  (and (>= b1 a1) (<= b1 a2)))

(defn any-overlap?
  "Given two int ranges, return true if either overlaps the other at all"
  [[a b]]
  (or (range-overlap? a b) (range-overlap? b a)))

(defn main
  "Day 4 of Advent of Code 2022: Camp Cleanup
       lein run day4 <input>
   where <input> is a filename in resources/"
  [[filename]]
  (let [ranges (map parse-input (util/read-lines filename))
        full-overlaps (filter full-overlap? ranges)
        any-overlaps (filter any-overlap? ranges)]
    (println "pairs where one range fully contains another:" (count full-overlaps))
    (println "pairs where one range overlaps another at all:" (count any-overlaps))))
