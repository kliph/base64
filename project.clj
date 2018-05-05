(defproject base64 "0.1.0-SNAPSHOT"
  :description "base64 encoding based on RFC"
  :url "https://tools.ietf.org/html/rfc4648"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :clean-targets ^{:protect false} [:target-path "out"]
  :min-lein-version "2.5.3"
  :dependencies [[org.clojure/clojure "1.10.0-alpha4"]
                 [org.clojure/clojurescript "1.10.238"]
                 [org.clojure/tools.cli "0.3.7"]]
  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-doo "0.1.7"]]
  :profiles {:dev {:source-paths ["src"]
                   :dependencies [[com.cemerick/piggieback "0.2.2"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :cljsbuild {:builds [{:id "dev"
                                         :source-paths ["src"]
                                         :compiler {:main "base64.core"
                                                    :output-to "js/base64.js"
                                                    :output-dir "js/out"
                                                    :optimizations :none
                                                    :target :nodejs
                                                    :recompile-dependents true
                                                    :source-map true}}
                                        {:id "test"
                                         :source-paths ["src" "test"]
                                         :compiler {:output-to "js/test.js"
                                                    :output-dir "js/test/out"
                                                    :main "base64.runner"
                                                    :target :nodejs
                                                    :optimizations :none}}]}}})
