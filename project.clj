(defproject clojure-template "0.1.0-SNAPSHOT"
  :description "A basic template for clojure projects."
  :url "http://github.com/iotemplates/clojure-template"
  :license {:name "Apache License 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0"
            :year 2020
            :key "apache-2.0"}
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :main ^:skip-aot clojure-template.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
