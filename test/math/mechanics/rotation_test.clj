;; Copyright (C) 2015 Colin Smith.
;; This work is based on the Scmutils system of MIT/GNU Scheme.
;;
;; This is free software;  you can redistribute it and/or modify
;; it under the terms of the GNU General Public License as published by
;; the Free Software Foundation; either version 3 of the License, or (at
;; your option) any later version.

;; This software is distributed in the hope that it will be useful, but
;; WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
;; General Public License for more details.

;; You should have received a copy of the GNU General Public License
;; along with this code; if not, see <http://www.gnu.org/licenses/>.

(ns math.mechanics.rotation-test
  (:refer-clojure :exclude [+ - * / zero?])
  (:require [clojure.test :refer :all]
            [math.generic :refer :all]
            [math.function :refer :all]
            [math.numbers]
            [math.simplify :refer [pe]]
            [math.mechanics.rotation :refer :all]
            [math.structure :refer :all]))

(deftest hello
  (let [P (up 'x 'y 'z)]
    (is (= '(up x
                (+ (* (cos a) y) (* -1 (sin a) z))
                (+ (* (sin a) y) (* (cos a) z)))
           (simplify ((Rx 'a) P))))
    (is (= '(up x
                (+ (* (cos a) y) (* -1 (sin a) z))
                (+ (* (sin a) y) (* (cos a) z)))
           (simplify (* (rotate-x-matrix 'a) P))))
    (is (= '(up 0 0 0) (simplify (- ((Rx 'a) P) (* (rotate-x-matrix 'a) P)))))
    (is (= '(up 0 0 0) (simplify (- ((Ry 'a) P) (* (rotate-y-matrix 'a) P)))))
    (is (= '(up 0 0 0) (simplify (- ((Rz 'a) P) (* (rotate-z-matrix 'a) P)))))
    ))
