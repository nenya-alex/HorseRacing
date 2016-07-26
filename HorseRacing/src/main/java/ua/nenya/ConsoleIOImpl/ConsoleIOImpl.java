package ua.nenya.consoleIOImpl;

import ua.nenya.consoleIO.ConsoleIO;

public class ConsoleIOImpl implements ConsoleIO {

	@Override
	public void writeln(String str) {
		System.out.println(str);
	}

}
