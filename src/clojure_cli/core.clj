(ns clojure-cli.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clojure.string :as string])
  (:gen-class))

(def cli-options
  [
  ;; An option with a required argument
   ["-n" "--name NAME" "The name of the person to greet"
    :id :name
    :parse-fn #(String/valueOf %)
    :default "Joe"] 
   ;; A boolean option defaulting to nil
   ["-v" nil "Verbosity level"
    :id :verbosity
    :default 0
    :update-fn inc] ; Prior to 0.4.1, you would have to use:
                   ;; :assoc-fn (fn [m k _] (update-in m [k] inc))
   ;; A boolean option defaulting to nil
   ["-h" "--help"]])

(defn usage [options-summary]
  (->> ["Simple greeter written in clojure."
        ""
        "Usage: program-name [options] action"
        ""
        "Options:"
        options-summary
        ""
        "Actions:"
        "  greet    Display a greeting"
        ""
        "Please refer to the manual page for more information."]
       (string/join \newline)))

(defn error-msg [errors]
  (str "The following errors occurred while parsing your command:\n\n"
       (string/join \newline errors)))

(defn validate-args
  "Validate command line arguments. Either return a map indicating the program
  should exit (with a error message, and optional ok status), or a map
  indicating the action the program should take and the options provided."
  [args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
    (cond
      (:help options) ; help => exit OK with usage summary
      {:exit-message (usage summary) :ok? true}
      errors ; errors => exit with description of errors
      {:exit-message (error-msg errors)}
      ;; custom validation on arguments
      (and (= 1 (count arguments))
           (#{"greet"} (first arguments)))
      {:action (first arguments) :options options}
      :else ; failed custom validation => exit with usage summary
      {:exit-message (usage summary)})))

(defn exit [status msg]
  (println msg)
  (System/exit status))

(defn -main [& args]
  (let [{:keys [action options exit-message ok?]} (validate-args args)]
    (if exit-message
      (exit (if ok? 0 1) exit-message)
      (case action
        "greet"  (println "Hello" (:name options) "!")))))
