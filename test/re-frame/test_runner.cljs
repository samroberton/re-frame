(ns re-frame.test-runner
  (:refer-clojure :exclude (set-print-fn!))
  (:require
    [cljs.test :as cljs-test :include-macros true]
    [jx.reporter.karma :as karma :include-macros true]
    [devtools.core :as devtools]
    ;; Test Namespaces -------------------------------
    [re-frame.middleware-test]
    [re-frame.undo-test]
    [re-frame.subs-test]))

(enable-console-print!)
(devtools/install! [:custom-formatters :sanity-hints]) ;; we love https://github.com/binaryage/cljs-devtools

;; ---- BROWSER based tests ----------------------------------------------------
(defn ^:export set-print-fn! [f]
  (set! cljs.core.*print-fn* f))


(defn ^:export run-html-tests []
  (cljs-test/run-tests
    're-frame.middleware-test
    're-frame.undo-test
    're-frame.subs-test))

;; ---- KARMA  -----------------------------------------------------------------

(defn ^:export run-karma [karma]
  (karma/run-tests
    karma
    're-frame.middleware-test
    're-frame.undo-test
    're-frame.subs-test))
