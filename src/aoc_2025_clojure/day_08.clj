(ns aoc-2025-clojure.day-08
  (:require
   [clojure.java.io :as io]
   [clojure.math :as math]
   [clojure.pprint :as pprint]
   [clojure.set :as set]
   [clojure.string :as str]))

(def input "162,817,812
57,618,57
906,360,560
592,479,940
352,342,300
466,668,158
542,29,236
431,825,988
739,650,466
52,470,668
216,146,977
819,987,18
117,168,530
805,96,715
346,949,466
970,615,88
941,993,340
862,61,35
984,92,344
425,690,689")

(defn parse-line [line]
  (mapv parse-long (str/split line #",")))

(defn parse-input [input]
  (->> (str/split-lines input)
       (mapv parse-line)))

(defn distance [v w]
  (math/sqrt (reduce + (map #(math/pow (- %1 %2) 2) v w))))

(defn ordered-pairs [vs]
  (for [v vs
        w vs
        :when (= -1 (compare v w))]
    [v w]))

(defn sorted-pairs [vs]
  (->> (ordered-pairs vs)
       (map (fn [[v w]] {:v v :w w :distance (distance v w)}))
       (sort-by :distance)))

(defn add-edge [{:keys [circuits representatives] :as graph} {:keys [v w]}]
  (let [t1 (get representatives v)
        t2 (get representatives w)]
    (if (= t1 t2)
      graph
      (let [representative (first (sort [t1 t2]))
            circuit (set/union (get circuits t1)
                               (get circuits t2))
            circuits (assoc circuits
                            v nil
                            w nil
                            t1 nil
                            t2 nil
                            representative circuit)
            representatives (reduce #(assoc %1 %2 representative) representatives circuit)]
        {:circuits circuits
         :representatives representatives}))))

(defn part-1
  ([] (part-1 (slurp (io/resource "08.txt"))))
  ([input]
   (let [nodes (parse-input input)
         limit (if (= 1000 (count nodes)) 1000 10)
         edges (take limit (sorted-pairs nodes))
         representatives (into {} (for [n nodes] [n n]))
         circuits (into {} (for [n nodes] [n #{n}]))
         graph {:representatives representatives
                :circuits circuits}]
     (->> (reduce add-edge graph edges)
          :circuits
          vals
          (map count)
          sort
          reverse
          (take 3)
          (reduce *)))))

(defn part-2
  ([] (part-2 (slurp (io/resource "08.txt"))))
  ([input]
   (let [nodes (parse-input input)
         edges (sorted-pairs nodes)
         representatives (into {} (for [n nodes] [n n]))
         circuits (into {} (for [n nodes] [n #{n}]))
         graph {:representatives representatives
                :circuits circuits}]
     (loop [graph graph
            [e & edges] edges]
       (let [graph (add-edge graph e)
             largest-circuit (apply max (map count (vals (:circuits graph))))]
         (if (= (count nodes) largest-circuit)
           (* (first (:v e)) (first (:w e)))
           (recur graph edges)))))))

