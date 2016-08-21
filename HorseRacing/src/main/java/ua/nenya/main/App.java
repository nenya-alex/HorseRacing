package ua.nenya.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import ua.nenya.consoleIO.ConsoleIO;
import ua.nenya.consoleIOImpl.ConsoleIOImpl;
import ua.nenya.domain.Racing;

public class App {
	public static void main(String[] args) {
		ConsoleIO io = new ConsoleIOImpl();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		HorseRacingInitilizer horseRacingInitilizer = new HorseRacingInitilizer();
		Racing racing = horseRacingInitilizer.initRacing();
		new HorseRacingRunner().run(racing.getNotes(), racing.getHorses(), io, br);
	}

}
