(ns aoc2022.day1
  (:require [aoc2022.util :as util]))

(defn calorie-counts
  "Given a seq of string lines per day 1 input, return a reverse-sorted seq
   where each element is an elf's total calorie count."
  [lines]
  (->> (reduce (fn [acc n]
                 (if (empty? n)
                   (conj acc 0) ; on a blank line, start a new elf's count
                   (let [[head & remaining] acc]
                     (conj remaining
                           (+ head (Integer/parseInt n))))))
               [0]
               lines)
       sort
       reverse))

(defn main
  "Day 1 of Advent of Code 2022: Calorie Counting
       lein run day1 <input>
   where <input> is a filename in resources/"
  [[filename]]
  (let [counts (calorie-counts (util/read-lines filename))]
    (println "maximum elf calories:" (first counts))
    (println "top 3 elf calories:" (reduce + (take 3 counts)))))
