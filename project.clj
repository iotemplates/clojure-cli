(defproject clojure-cli "0.1.0-SNAPSHOT"
  :description "A basic template for clojure projects."
  :url "http://github.com/iotemplates/clojure-cli"
  :license {:name "Apache License 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0"
            :year 2020
            :key "apache-2.0"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/tools.cli "1.0.206"]]
  :plugins [[io.taylorwood/lein-native-image "0.3.1"]]
  :main ^:skip-aot clojure-cli.core
  :target-path "target/%s"
  :native-image {:jvm-opts ["-Dclojure.compiler.direct-linking=true"]
                 :opts ["--report-unsupported-elements-at-runtime" ;; ignore native-image build errors
                        "--initialize-at-build-time"
                        "--no-server" ;; TODO issue with subsequent builds failing on same server
                        "--verbose"]
                 :name "clojure-cli"}
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
