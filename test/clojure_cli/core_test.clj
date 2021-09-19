(ns clojure-cli.core-test
  (:require [clojure.test :refer :all]
            [clojure-template.core :refer :all]))

(deftest validate-args-test
  (testing "Testing failed"
    ;; Expected to return exit message with failure status
    (is (let [result (validate-args nil)]
          (and
           (contains? result :exit-message)
           (not (:ok? result)))))
    ;; Expected to display usage but not fail
    (is (let [result (validate-args ["--help"])]
          (and
           (contains? result :exit-message)
           (:ok? result))))))
 
    
