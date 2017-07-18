compile: 
	javac -cp "lib/guava-22.0.jar:lib/junit-4.12.jar:lib/hamcrest-all-1.3.jar" src/main/java/brymlee/guava/examples/*.java 
	javac -cp "lib/guava-22.0.jar:lib/junit-4.12.jar:lib/hamcrest-all-1.3.jar" src/test/java/brymlee/guava/examples/*.java

testRun:
	java -cp "lib/guava-22.0.jar:lib/junit-4.12.jar:lib/hamcrest-all-1.3.jar:src/main/java:src/test/java" org.junit.runner.JUnitCore brymlee.guava.examples.PredicateTest
	java -cp "lib/guava-22.0.jar:lib/junit-4.12.jar:lib/hamcrest-all-1.3.jar:src/main/java:src/test/java" org.junit.runner.JUnitCore brymlee.guava.examples.MapTest
	java -cp "lib/guava-22.0.jar:lib/junit-4.12.jar:lib/hamcrest-all-1.3.jar:src/main/java:src/test/java" org.junit.runner.JUnitCore brymlee.guava.examples.CompositeTest
	java -cp "lib/guava-22.0.jar:lib/junit-4.12.jar:lib/hamcrest-all-1.3.jar:src/main/java:src/test/java" org.junit.runner.JUnitCore brymlee.guava.examples.ReduceTest

