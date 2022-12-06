(ns aoc2022.day6
  (:require [aoc2022.util :as util]))

(defn next-marker-window
  "Given a triple of [letters stream index], chop off the first `n` letters of
  stream to use as the next set, and shift our 'window' on the stream forward
  by 1 letter"
  [n [_, stream, index]]
  [(set (take n stream)) (rest stream) (inc index)])


(defn detect-marker
  "Return the index directly after the first marker in stream: count-distinct different
   characters"
  [stream count-distinct]
  ; construct a lazy sequence of count-distinct prefixes + some accounting
  ; then filter for the first sequence where count-distinct are different
  (let [sets (iterate (partial next-marker-window count-distinct)
                      [(set '()) stream 0])
        [_ _ index] (first (filter (fn [[letters _ _]]
                                     (= count-distinct (count letters)))
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
