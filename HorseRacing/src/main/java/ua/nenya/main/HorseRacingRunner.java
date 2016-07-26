package ua.nenya.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import ua.nenya.consoleIO.ConsoleIO;
import ua.nenya.consoleIOImpl.ConsoleIOImpl;
import ua.nenya.domain.Denomination;
import ua.nenya.domain.Horse;

public class HorseRacingRunner {

	private static final String OR = "|";
	private static final String WINNING_NUMBER_COMMAND_REGEX = "^[w,W]\\s?\\d+$";
	private static final String BETTING_COMMAND_REGEX = "^\\d+\\s\\d+(\\.\\d*)?|\\.\\d+$";
	private static final String RESTORE_DENOMINATIONS_COMMAND_REGEX = "^[r,R]$";
	private static final String QUIT_COMMAND_REGEX = "^[q,Q]$";
	private static final String INVALID_COMMAND = "Invalid Command: ";

	public void run(List<Denomination> notes, List<Horse> horses){

		Methods methods = new Methods();
		ConsoleIO io = new ConsoleIOImpl();
		String command;

		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			do {
				methods.showInventoryAndHorses(notes, horses, io);
				command = br.readLine();

				String allCommandsRegex = QUIT_COMMAND_REGEX + OR + RESTORE_DENOMINATIONS_COMMAND_REGEX + OR
						+ BETTING_COMMAND_REGEX + OR + WINNING_NUMBER_COMMAND_REGEX;

				if (methods.isCommandEqualsRegex(command, allCommandsRegex)) {

					if (methods.isCommandEqualsRegex(command, RESTORE_DENOMINATIONS_COMMAND_REGEX)) {
						notes = methods.restoreCashInventory(notes);
					} else {
						if (methods.isCommandEqualsRegex(command, WINNING_NUMBER_COMMAND_REGEX)) {							
							horses = methods.changeHorseWinner(command, horses, io);
						} else {
							if (methods.isCommandEqualsRegex(command, BETTING_COMMAND_REGEX)) {								
								methods.checkPayout(command, horses, notes, io);
							}
						}
					}
				} else {
					io.writeln(INVALID_COMMAND + command);
				}

			} while (!methods.isCommandEqualsRegex(command, QUIT_COMMAND_REGEX));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
