all: hashmap listMapTest treeMapTest

hashmap:
	javac -d ~/Scripts/Java/HashMap_BenjaminBascom/bin/hashmap -cp ~/Scripts/Java/junit-4.10.jar ~/Scripts/Java/HashMap_BenjaminBascom/src/hashmap/*.java

listMapTest:
	java -cp ~/Scripts/Java/junit-4.10.jar:./bin/bnf org.junit.runner.JUnitCore hashmap.ListHashMapTest

treeMapTest:
	java -cp ~/Scripts/Java/junit-4.10.jar:./bin/bnf org.junit.runner.JUnitCore hashmap.TreeHashMapTest

