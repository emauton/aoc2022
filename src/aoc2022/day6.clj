(ns aoc2022.day6
  (:require [aoc2022.util :as util]))

(defn detect-marker
  "return the index directly after the first marker in stream: count-distinct different
   characters"
  [stream count-distinct]
  ; construct a lazy sequence of count-distinct prefixes + some accounting
  ; then filter for the first sequence where count-distinct are different
  (let [sets (iterate (fn [[_ remainder n]]
                        (let [buffer (set (take count-distinct remainder))]
                          [buffer (rest remainder) (inc n)]))
                      [(set '()) stream 0])
        [_ _ index] (first (filter (fn [[segment _ _]]
                                     (= count-distinct (count segment)))
                                   sets))]
    (+ index (dec count-distinct))))

(defn main
  "Day 6 of Advent of Code 2022: Tuning Trouble
       lein run day6 <input>
   where <input> is a filename in resources/"
  [[filename]]
  (let [stream (util/slurp-resource filename)]
    (println "part1 result:" (detect-marker stream 4))
    (println "part1 result:" (detect-marker stream 14))))
