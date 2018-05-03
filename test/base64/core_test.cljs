(ns base64.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [base64.core :as core]))

(deftest empty-string
  (testing "Empty string returns itself"
    (is (= (core/base64 "")
           ""))))

(deftest encode
  (testing "padding two spaces"
    (is (= (core/base64 "f")
           "Zg==")))
  (testing "padding one space"
    (is (= (core/base64 "fo")
           "Zm8=")))
  (testing "no padding"
    (is (= (core/base64 "foo")
           "Zm9v"))))
