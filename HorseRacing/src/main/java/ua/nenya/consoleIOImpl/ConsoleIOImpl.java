package ua.nenya.consoleIOImpl;

import java.io.BufferedReader;
import java.io.IOException;

import ua.nenya.consoleIO.ConsoleIO;

public class ConsoleIOImpl implements ConsoleIO {

	@Override
	public void writeln(String str) {
		System.out.println(str);
	}

	@Override
	public String read(BufferedReader br) {
		String command = "";
		try {
			command = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return command;
	}

	@Override
	public void close(BufferedReader br) {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
