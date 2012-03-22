To compile, you can either use `javac` and do everything manually or you can use the included `buildfile` with [Apache Buildr](http://buildr.apache.org/). Going with Apache Buildr simply run `buildr test` which will automatically run `buildr compile` if needed and then run the unit tests using JUnit 4.

Please note that this implementation targets Java 6 and therefore does *not* use the Fork/Join framework.

This implementation is designed to spawn threads up to a certain threshold and then fall back to recursion in order to avoid the overhead of constant context switching. Here are some sample outputs from the `largeTestSort` method (output will vary since sorted values are different every time):

    :::text
    Generation time: 887 (10000000 Integer Objects)
    Copy time: 7
    Java built in implementation: 4115
    Multi-threaded quick sort: 1599

    Generation time: 866 (10000000 Integer Objects)
    Copy time: 7
    Java built in implementation: 4158
    Multi-threaded quick sort: 1491

    Generation time: 875 (10000000 Integer Objects)
    Copy time: 7
    Java built in implementation: 4062
    Multi-threaded quick sort: 1451
