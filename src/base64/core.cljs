(ns base64.core
  (:require [goog.crypt.base64]))


(def alphabet
  (->> "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
       (mapv char)))

(defn base64 [s]
  s)

#_(defn base64 [s]
  (goog.crypt.base64/encodeString s))
