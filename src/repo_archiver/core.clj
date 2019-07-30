(ns repo-archiver.core
  (:require
   [clojure.tools.namespace.repl :refer [refresh]]
   [clojure.java.io :refer [file]]
   [tentacles
    [repos :as repos]
    [core :refer [with-defaults api-call no-content?]]])
  (:gen-class))

(defn file-exists?
  [path]
  (-> path file .exists))

(defn dump-path
  [id]
  (str "./resources/" id ".edn"))

(defn dump-data
  [id data]
  (spit (dump-path id) data)
  data)

(defn load-data
  [id]
  (let [path (dump-path id)]
    (when (file-exists? path)
      (read-string (slurp path)))))

;;

(defn auth
  []
  {:oauth-token (load-data "personal-access-token")})

(defn archive-repository
  [repository]
  (let [org (-> repository :owner :login)
        repo (:name repository)
        ;; https://developer.github.com/v3/repos/#edit
        ;; "Note: You cannot unarchive repositories through the API."
        options {:archived true}]
    (println repo)
    (with-defaults (auth)
      (repos/edit-repo org repo options))))

(defn list-unarchived-repositories
  []
  (let [org "elifesciences-publications"
        per-page 100
        options {:types "public" :per-page per-page}]
    (loop [page 1
           results []]
      (let [resp (repos/org-repos org (assoc options :page page))
            unarchived-repo-list (vec (remove :archived resp))
            recur? (-> resp count (= per-page))]
        (if recur?
          (recur (inc page)
                 (into results unarchived-repo-list))
          (into results unarchived-repo-list))))))

;;

(defn load-repositories
  []
  (let [id "repo-list"]
    (or (load-data id) (dump-data id (list-unarchived-repositories)))))

(defn archive-all-repositories
  []
  ;;(->> (load-repositories) (take 10) (mapv archive-repository))
  (->> (load-repositories) (mapv archive-repository))
  nil)

(defn -main
  [& args]
  (archive-all-repositories))
  
