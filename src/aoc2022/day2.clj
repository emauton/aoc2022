(ns aoc2022.day2
  (:require [aoc2022.util :as util]
            [clojure.string :as string]
            clojure.pprint))

(def shape-map {"A" :rock "B" :paper "C" :scissors})

(def shape-scores {:rock 1 :paper 2 :scissors 3})

(def game-scores {[:rock :rock]         3
                  [:rock :paper]        6
                  [:rock :scissors]     0
                  [:paper :rock]        0
                  [:paper :paper]       3
                  [:paper :scissors]    6
                  [:scissors :rock]     6
                  [:scissors :paper]    0
                  [:scissors :scissors] 3})

(def part1-map {"X" :rock "Y" :paper "Z" :scissors})

(def part2-map {"X" 0 "Y" 3 "Z" 6})

(defn score
  "Given a pair of shapes, return the score from the perspective of the second
   player."
  [[p1 p2]]
  (+ (get game-scores [p1 p2]) (get shape-scores p2)))

(defn satisfy
  "Given a part 2 input representing a shape and a target result (e.g. A X),
   return a pair of shapes that produce that result."
  [[p1 target]]
  (let [target-value (get part2-map target)
        satisfier (first (filter (fn [[[p _] value]]
                                   (and (= p (get shape-map p1))
                                        (= value target-value)))
                                 game-scores))]
    (first satisfier)))

(defn main
  "Day 2 of Advent of Code 2022: Rock Paper Scissors
       lein run day1 <input>
   where <input> is a filename in resources/"
  [[filename]]
  (let [pairs (map #(string/split % #" ") (util/read-lines filename))
        part1-pairs (map (fn [[p q]]
                           [(get shape-map p) (get part1-map q)])
                         pairs)
        part2-pairs (map satisfy pairs)
        part1-scores (map score part1-pairs)
        part2-scores (map score part2-pairs)]
    (println "part 1 score:" (reduce + part1-scores))
    (println "part 2 score:" (reduce + part2-scores))))
