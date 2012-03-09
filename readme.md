To compile, you can either use `javac` and do everything manually or you can use the included `buildfile` with [Apache Buildr](http://buildr.apache.org/). Going with Apache Buildr simply run `buildr test` which will automatically run `buildr compile` if needed and then run the unit tests using JUnit 4.

Please note that this implementation targets Java 6 and therefore does *not* use the Fork/Join framework.
