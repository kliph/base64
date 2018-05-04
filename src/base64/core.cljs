(ns base64.core
  (:require [goog.crypt.base64]
            [goog.crypt]))


(def alphabet
  (->> "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
       (mapv char)))

(defn string-to-byte-array [s]
  (goog.crypt/stringToByteArray s))

(defn nil-to-0 [[a b c]]
  (mapv #(if (nil? %) 0 %)
        [a b c]))

(defn encode-triplet [[byte1 byte2 byte3]]
  (let [bytes (nil-to-0 [byte1 byte2 byte3])
        out-byte1 (bit-shift-right byte1 2)
        out-byte2 (bit-or (bit-shift-left (bit-and byte1 0x03)
                                           4)
                           (bit-shift-right byte2 4))
        out-byte3 (bit-or (bit-shift-left (bit-and byte2 0x0F)
                                           2)
                           (bit-shift-right byte3 6))
        out-byte4 (bit-and byte3 0x3F)
        ]
    [out-byte1
     out-byte2
     out-byte3
     out-byte4
     ]))

(defn encode-byte-array [bytes]
  (let [output []]
    output))

(defn base64 [s]
  s)

#_(defn base64 [s]
  (goog.crypt.base64/encodeString s))
