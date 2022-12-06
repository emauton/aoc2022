(ns aoc2022.day5
  (:require [aoc2022.util :as util]
            [clojure.pprint :as pprint]))

(defn parse-instr
  "Parse the instruction input into simple triples"
  [input]
  (map (fn [line]
         (map #(Integer/parseInt %)
              (rest (re-matches #"move (\d+) from (\d+) to (\d+)" line))))
       input))

(defn move-n
  "Perform one move of n crates"
  [stack n from to]
  (let [items (take n (get stack from))]
    (merge stack {from (nthrest (get stack from) n)
                  to   (concat items (get stack to))})))

(defn move-9000
  "Perform a CrateMover 9000 move instruction on a stack"
  [stack [n from to]]
  (last (take (inc n) (iterate #(move-n % 1 from to) stack))))

(defn move-9001
  "Perform a CrateMover 9001 move instruction on a stack"
  [stack [n from to]]
  (move-n stack n from to))

(defn main
  "Day 5 of Advent of Code 2022: Supply Stacks
       lein run day5 <stack> <input>
   where <stack> and <input> are in resources/
  <stack> is the initial datastructure encoded as a Clojure map because life is
  too short to parse that"
  [[stack instructions]]
  (let [state (read-string (util/slurp-resource stack))
        instr (parse-instr (util/read-lines instructions))
        part1-state (reduce (fn [acc i]
                              (move-9000 acc i))
                            state
                            instr)
        part2-state (reduce (fn [acc i]
                              (move-9001 acc i))
                            state
                            instr)]
    (println "part1-state")
    (pprint/pprint (into (sorted-map) part1-state))
    (println "part2-state")
    (pprint/pprint (into (sorted-map) part2-state))))
