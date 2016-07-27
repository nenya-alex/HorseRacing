package ua.nenya.consoleIO;

import java.io.BufferedReader;

public interface ConsoleIO {

	void writeln(String str);

	String read(BufferedReader br);

	void close(BufferedReader br);
}
