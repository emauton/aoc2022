(defproject aoc2022 "0.1.0-SNAPSHOT"
  :description "Advent of Code 2022"
  :url "https://github.com/emauton/aoc2022"
  :dependencies [[org.clojure/clojure "1.11.1"]]
  :main ^:skip-aot aoc2022.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
