(ns base64.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [base64.core :as core]))

(deftest empty-string
  (testing "Empty string returns itself"
    (is (= (core/base64 "")
           ""))))

#_(deftest encode
  (testing "padding two spaces"
    (is (= (core/base64 "f")
           "Zg==")))
  (testing "padding one space"
    (is (= (core/base64 "fo")
           "Zm8=")))
  (testing "no padding"
    (is (= (core/base64 "foo")
           "Zm9v"))))

(deftest nil-to-0
  (testing "encodes nil as 0"
    (is (= (core/nil-to-0 (->> "A"
                               core/string-to-byte-array
                               (take 3)))
           [65 0 0]))
    (is (= (core/nil-to-0 (->> "AB"
                               core/string-to-byte-array
                               (take 3)))
           [65 66 0]))
    (is (= (core/nil-to-0 (->> "ABC"
                               core/string-to-byte-array
                               (take 3)))
           [65 66 67]))))

(deftest encode-triplet
  (testing "returns the 4 encoded bytes"
    (is (= (core/encode-triplet [65 66 67])
           [16 20 9 3])))
  (testing "returns padding bytes when encoding fewer than 4 bytes"
    (is (= (core/encode-triplet [65 66 nil])
           [16 20 9 64]))
    (is (= (core/encode-triplet [65 nil nil])
           [16 20 64 64]))))
