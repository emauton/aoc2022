(ns aoc2022.day8
  (:require [aoc2022.util :as util]
            [clojure.string :as string]))

(defn parse-map
  "Given a list of lines representing tree map input, return a sorted map of {[y x] height} plus metadata"
  [lines]
  (let [numbers (map (fn [line]
                       (map #(Integer/parseInt %) (string/split line #""))) lines)
        max-y (count numbers)
        max-x (count (first numbers))
        coords (for [y (range max-y) x (range max-x)] [y x])]
    {:max-y max-y
     :max-x max-x
     :map (into (sorted-map) (map (fn [[y x]]
                                    [[y x] (nth (nth numbers y) x)])
                                  coords))}))

(defn cross-coords
  "Given a coordinate and boundaries, return the four sets of 'cross
   coordinates' to the edge, including coords immediately outside the edge,
   sorting by 'closest' coordinate"
  [[y x] max-y max-x]
  (let [west  (reverse (for [j (range -1 x)] [y j]))
        east  (for [j (range (inc x) (inc max-x))] [y j])
        north (reverse (for [i (range -1 y)] [i x]))
        south (for [i (range (inc y) (inc max-y))] [i x])]
    [west east north south]))

(defn tree-visible-direction?
  "Return true if the tree at coord is visible in comparison to others"
  [tree others tree-map]
  (let [height (tree-map tree)]
    (not (some #(>= % height) (map #(get tree-map % -1) others)))))

(defn tree-visible?
  "Given a coordinate and a tree map, return true if tree is visible"
  [coord trees]
  (let [crosses (cross-coords coord (:max-y trees) (:max-x trees))]
    (some #(tree-visible-direction? coord % (:map trees)) crosses)))

(defn viewing-distance
  "Return the viewing distance for a tree in comparison to others"
  [tree others tree-map]
  (let [height (tree-map tree)
        no-edges (drop-last others)
        unblocked (drop-while (fn [c] (< (get tree-map c) height)) no-edges)]
    (if (empty? unblocked)
      (count no-edges)
      (inc (- (count no-edges) (count unblocked))))))

(defn scenic-score
  "Given a coordinateand a tree map, return scenic score for the tree"
  [coord trees]
  (let [crosses (cross-coords coord (:max-y trees) (:max-x trees))
        scores (map #(viewing-distance coord % (:map trees)) crosses)]
    (apply * scores)))

(defn main
  "Day 8 of Advent of Code 2022: Treetop Tree House
       lein run day8 <input>
   where <input> is a filename in resources/"
  [[filename]]
  (let [trees (parse-map (util/read-lines filename))
        visible (filter (fn [[coord _]] (tree-visible? coord trees)) (:map trees))
        scores (map (fn [[coord _]] (scenic-score coord trees)) (:map trees))]
    (println "count of visible trees:" (count visible))
    (println "max scenic score:" (apply max scores))))
