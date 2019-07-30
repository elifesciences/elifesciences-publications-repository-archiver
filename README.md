# repo-archiver

Archives **all** repositories in the [elifesciences-publications](https://github.com/elifesciences-publications) 
Github organisation.

Archiving a repository makes it "read-only" which silences security alerts and prevents unintended changes being made.

The elifesciences-publications Github organisation exists to mirror repositories that have been referenced in scholarly
papers published by eLife Sciences.

## Usage

Credentials are required for modifying repositories. A Personal Access Token is looked for in 
`./resources/personal-access-token.edn` and if your token is `123` should be a simple quoted string, like `"123"`.

Java (see releases for downloadable jar files):

```bash
java -jar repo-archiver-x.x.x-standalone.jar
```

Leiningen:

```bash
lein run
```

Clojure REPL usage:

```bash
lein repl
=> (archive-all-repositories)
```

A list of repositories is cached at `./resources/repo-list.edn` to prevent paging through 6+ pages of results.

## Licence

Unless otherwise noted, all `repo-archiver` source files are made available under the terms of the 
GNU General Public License v3 (GPL3).

See [LICENSE](LICENSE) for details and exceptions.
