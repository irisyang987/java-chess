cd IrisYangJavaChess
mkdir -p out
javac -cp "lib/*" -d out src/IrisYangChess/Chess.java
java -cp "out:lib/*" IrisYangChess.Chess
