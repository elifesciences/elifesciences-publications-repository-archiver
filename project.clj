(defproject repo-archiver "0.1.0"
  :description "Archives all repositories in the elifesciences-publications Github organisation"
  :url "https://github.com/elifesciences/elifesciences-publications-repository-archiver"
  :license {:name "GPL3"
            :url "https://www.gnu.org/licenses/gpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [irresponsible/tentacles "0.6.4"] ;; github api interaction
                 [org.clojure/tools.namespace "0.2.11"]]

  :main repo-archiver.core
  :profiles {:uberjar {:aot :all}}
  :uberjar-exclusions [#".*\.edn"]
  :repl-options {:init-ns repo-archiver.core})
