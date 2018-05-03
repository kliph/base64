(ns base64.runner
  (:require [doo.runner :refer-macros [doo-all-tests]]
            [goog.object :as gobj]
            [base64.core-test]))

(doo-all-tests #"(base64)\..*-test")
