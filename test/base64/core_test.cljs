(ns base64.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [base64.core :as core]))

(deftest empty-string
  (testing "Empty string returns itself"
    (is (= (core/base64 "")
           ""))))
