package ua.nenya.ConsoleIOImpl;

import ua.nenya.consoleIO.ConsoleIO;

public class ConsoleIOImpl implements ConsoleIO {

	public void write(String str) {
		System.out.print(str);
	}

	public void writeln(String str) {
		System.out.println(str);
	}

}
