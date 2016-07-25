package ua.nenya.HorseRacing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.nenya.consoleIO.ConsoleIO;
import ua.nenya.domain.Denomination;
import ua.nenya.domain.Horse;

public class Methods {

	private static final String BLANK = " ";
	private static final String COMA = ",";
	private static final String COMA_DOLLAR = ",$";
	private static final String DISPENSING = "Dispensing:";
	private static final String PAYOUT = "Payout: ";
	private static final String INSUFFICIENT_FUNDS = "Insufficient Funds: ";
	private static final String INVALID_BET = "Invalid Bet: ";
	private static final String VALID_BET_REGEX = "^\\d+$";
	private static final String NO_PAYOUT = "No Payout: ";
	private static final String LOST = "lost";
	private static final String WON = "won";
	private static final String HORSES = "Horses:";
	private static final String INVENTORY = "Inventory:";
	private static final String INVALID_HORSE_NUMBER = "Invalid Horse Number: ";

	public boolean isCommandEqualsRegex(String command, String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(command);
		return m.matches();
	}

	public void showInventoryAndHorses(List<Denomination> notes, List<Horse> horses, ConsoleIO io) {		
		io.writeln(INVENTORY);
		for (Denomination it : notes) {
			io.writeln(it.getName() + COMA + it.getQuantity());
		}

		io.writeln(HORSES);
		for (Horse it : horses) {
			io.writeln(it.getNumber() + COMA + it.getName() + COMA + it.getOdds() + COMA + it.getDidWin());
		}
	}

	public List<Denomination> restoreCashInventory(List<Denomination> notes) {
		List<Denomination> restoringNotes = new ArrayList<>();
		for (int i = 0; i < notes.size(); i++) {
			Denomination denomination = notes.get(i);
			denomination.setQuantity(denomination.getInitialQuantity());
			restoringNotes.add(denomination);
		}
		return restoringNotes;
	}

	public List<Horse> changeHorseWinner(String command, List<Horse> horses, ConsoleIO io) {
		int number = Integer.valueOf(command.substring(1).trim());

		if (isHorseExists(number, horses)) {
			horses = setWinningHorseNumber(horses, number);
		} else {
			io.writeln(INVALID_HORSE_NUMBER + number);
		}
		return horses;
	}

	public void checkPayout(String command, List<Horse> horses, List<Denomination> notes, ConsoleIO io) {

		List<String> commands = new ArrayList<>(Arrays.asList(command.split(BLANK)));
		int number = Integer.valueOf(commands.get(0));

		if (!isHorseWinner(number, horses)) {
			io.writeln(NO_PAYOUT + getHorse(number, horses).getName());
		} else {
			String strBet = commands.get(1);
			if (isCommandEqualsRegex(strBet, VALID_BET_REGEX)) {
				int bet = Integer.valueOf(strBet);
				doPayout(number, bet, horses, notes, io);
			} else {
				io.writeln(INVALID_BET + strBet);
			}
		}
	}
	
	private void showDispensing(List<Denomination> notes, ConsoleIO io) {
		io.writeln(DISPENSING);
		
		for (Denomination it : notes) {
			io.writeln(it.getName() + COMA + it.getQuantity());
		}
	}

	private Horse getHorse(int number, List<Horse> horses) {
		int index = 0;
		for (int i = 0; i < horses.size(); i++) {
			Horse horse = horses.get(i);
			if (horse.getNumber() == number) {
				index = i;
			}
		}
		return horses.get(index);
	}

	private List<Horse> setWinningHorseNumber(List<Horse> horses, int number) {
		List<Horse> newHorses = new ArrayList<>();

		for (int i = 0; i < horses.size(); i++) {
			Horse horse = horses.get(i);
			if (horse.getNumber() == number) {
				horse.setDidWin(WON);
			} else {
				horse.setDidWin(LOST);
			}
			newHorses.add(horse);
		}
		return newHorses;
	}

	private boolean isHorseWinner(int number, List<Horse> horses) {
		for (Horse it : horses) {
			if (it.getNumber() == number) {
				return WON.equals(it.getDidWin());
			}
		}
		return false;
	}

	private boolean isHorseExists(int number, List<Horse> horses) {
		for (Horse it : horses) {
			if (it.getNumber() == number) {
				return true;
			}
		}
		return false;
	}

	private void doPayout(int number, int bet, List<Horse> horses, List<Denomination> notes, ConsoleIO io) {

		long totalSum = getTotalSum(notes);
		Horse horse = getHorse(number, horses);

		if (bet * horse.getOdds() <= totalSum) {
			long payout = horse.getOdds() * bet;
			io.writeln(PAYOUT + horse.getName() + COMA_DOLLAR + payout);
			List<Denomination> bills = countBills(payout, notes);
			showDispensing(bills, io);
		} else {
			io.writeln(INSUFFICIENT_FUNDS + getHorse(number, horses).getOdds() * bet);
		}
	}

	private List<Denomination> countBills(long payout, List<Denomination> notes) {
		List<Denomination> bills = new ArrayList<>();

		for (int i = notes.size() - 1; i >= 0; i--) {
			Denomination bill = notes.get(i);
			int amountOfBill = bill.getAmount();
			int quantityOfBillNotes = bill.getQuantity();
			int numberOfNeededNotes = (int) (payout / amountOfBill);
			
			if (numberOfNeededNotes == 0) {
				Denomination denomination = new Denomination(bill.getName(), bill.getAmount());
				denomination.setQuantity(numberOfNeededNotes);
				bills.add(denomination);
			} else {
				if (numberOfNeededNotes <= quantityOfBillNotes) {
					Denomination denomination = new Denomination(bill.getName(), bill.getAmount());
					denomination.setQuantity(numberOfNeededNotes);
					bill.setQuantity(quantityOfBillNotes - numberOfNeededNotes);
					bills.add(denomination);
					payout = payout - numberOfNeededNotes * amountOfBill;
				} else {
					Denomination denomination = new Denomination(bill.getName(), bill.getAmount());
					denomination.setQuantity(quantityOfBillNotes);
					bill.setQuantity(0);
					bills.add(denomination);
					payout = payout - quantityOfBillNotes * amountOfBill;
				}
			}
		}
		Collections.reverse(bills);
		return bills;
	}

	private long getTotalSum(List<Denomination> notes) {
		long sum = 0;
		for (Denomination it : notes) {
			sum = sum + it.getQuantity() * it.getAmount();
		}
		return sum;
	}
}
