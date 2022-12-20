(ns aoc2022.day7
  (:require [aoc2022.util :as util]
            [clojure.string :as string]
            [clojure.pprint :as pp]))

(defn new-state
  "Get initialized empty filesystem state"
  []
  ; Each path (including cwd) is a list of string path components
  ; For each path, files are a set of [name size] pairs
  {:paths {[] #{}}
   :cwd []})

(defn parse-op
  "Given a single-line operation or output, return a pair [operation argument]
   where operation is a symbol in :cd :ls :dir :file"
  [line]
  (let [fields (string/split line #" ")]
    (case (first fields)
      "$" (if (= "ls" (second fields))
            [:ls nil]
            [:cd (last fields)])
      "dir" [:dir (second fields)]
      [:file [(second fields) (Integer/parseInt (first fields))]])))

(defn apply-op
  "Given an op and a filesystem state, return a new state with the op applied"
  [{paths :paths cwd :cwd :as state} [op arg]]
  (case op
    :ls state ; ls is a no-op
    :cd (case arg
          "/" {:paths paths :cwd []}
          ".." {:paths paths :cwd (pop cwd)}
          {:paths paths :cwd (conj cwd arg)})
    :dir (let [path (conj cwd arg)]
           (if (paths path)
             state ; no-op if we see a dir more than once
             {:paths (assoc paths path #{}) :cwd cwd}))
    :file (let [[filename size] arg
                contents (paths cwd)]
            {:paths (assoc paths cwd (conj contents arg)) :cwd cwd})))

(defn walk-to-root
  "Given a path represented as a list, return this plus all parent paths as we walk up to the root"
  [paths]
  (take (inc (count paths)) (iterate pop paths)))

(defn path-sizes
  "Given a path and a set of files, map all paths walking up to root to the sum of file sizes"
  [path files]
  (let [sum (reduce (fn [acc [_ size]] (+ acc size)) 0 files)]
    (reduce (fn [acc path]
              (assoc acc path sum))
            {}
            (walk-to-root path))))

(defn total-sizes
  "Given a filesystem state, return a map of paths to total sizes"
  [{paths :paths}]
  (reduce-kv (fn [acc path files]
               (merge-with + acc (path-sizes path files)))
             {}
             paths))

(defn main
  "Day 7 of Advent of Code 2022: No Space Left On Device
       lein run day7 <input>
   where <input> is a filename in resources/"
  [[filename]]
  (let [ops (map parse-op (util/read-lines filename))
        state (reduce apply-op (new-state) ops)
        sizes (total-sizes state)
        part1-targets (filter (fn [[path size]] (<= size 100000)) sizes)
        part1-sum (reduce-kv (fn [acc path size] (+ acc size)) 0 part1-targets)
        unused-space (- 70000000 (sizes []))
        part2-target (- 30000000 unused-space)
        part2-result (first (filter (fn [[path size]] (>= size part2-target)) (sort-by val < sizes)))]
    (println "sum of sizes at most 100000:" part1-sum)
    (println "size of directory to delete:" (second part2-result))))
