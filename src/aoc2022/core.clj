(ns aoc2022.core
  (:require aoc2022.day1 
            clojure.string)
  (:gen-class))

(defn -main
  "Dispatch to the different day routines for Advent of Code 2022"
  [day & args]
  (let [day-ns (symbol (clojure.string/join "." ["aoc2022" day]))
        day-main (ns-resolve day-ns 'main)]
    (day-main args)))
