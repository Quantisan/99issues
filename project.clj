(defproject ninety-nine-issues "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.189"]
                 [reagent "0.5.1"]
                 [re-frame "0.6.0"]]

  :source-paths ["src/clj"]

  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-figwheel "0.5.0-2"]]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :figwheel {:css-dirs ["resources/public/css"]}

  :cljsbuild {:builds
              {:dev {:source-paths ["src/cljs"]

                     :figwheel {:on-jsload "ninety-nine-issues.core/mount-root"}

                     :compiler {:main ninety-nine-issues.core
                                :output-to "resources/public/js/compiled/app.js"
                                :output-dir "resources/public/js/compiled/out"
                                :asset-path "js/compiled/out"
                                :source-map-timestamp true}}

               :min {:source-paths ["src/cljs"]
                     :compiler {:main ninety-nine-issues.core
                                :output-to "resources/public/js/compiled/app.js"
                                :optimizations :advanced
                                :closure-defines {goog.DEBUG false}
                                :pretty-print false}}}})

