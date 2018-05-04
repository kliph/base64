(ns base64.core
  (:require [goog.crypt.base64]
            [goog.crypt]))


(def alphabet
  (->> "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
       (mapv char)))

(def filler-byte 64)

(defn string-to-byte-array [s]
  (goog.crypt/stringToByteArray s))

(defn nil-to-0 [[a b c]]
  (mapv #(if (nil? %) 0 %)
        [a b c]))

(defn encode-triplet [bytes]
  (let [[byte1 byte2 byte3] (nil-to-0 bytes)
        out-byte1 (bit-shift-right byte1 2)
        out-byte2 (bit-or (bit-shift-left (bit-and byte1 0x03)
                                           4)
                           (bit-shift-right byte2 4))
        out-byte3 (if (zero? byte2)
                    filler-byte
                    (bit-or (bit-shift-left (bit-and byte2 0x0F)
                                            2)
                            (bit-shift-right byte3
                                             6)))
        out-byte4 (if (zero? byte3)
                    filler-byte
                    (bit-and byte3 0x3F))]
    [out-byte1
     out-byte2
     out-byte3
     out-byte4]))

(defn encode-byte-array [bytes]
  (let [triplets (partition-all 3 bytes)]
    (->> triplets
         (map encode-triplet)
         flatten
         (map #(get alphabet %)))))

(defn base64 [s]
  (->> s
       string-to-byte-array
       encode-byte-array
       clojure.string/join))

#_(defn base64 [s]
  (goog.crypt.base64/encodeString s))
