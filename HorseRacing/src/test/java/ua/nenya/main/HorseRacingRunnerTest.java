package ua.nenya.main;

import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import ua.nenya.consoleIO.ConsoleIO;
import ua.nenya.domain.Denomination;
import ua.nenya.domain.Horse;

public class HorseRacingRunnerTest {

	private List<Denomination> notes = new ArrayList<>();
	private List<Horse> horses = new ArrayList<>();
	private Denomination note1;
	private Denomination note5;
	private Horse horse1;
	private Horse horse2;
	private ConsoleIO mockIo;
	private BufferedReader mockBr;

	@Before
	public void init() {
		note1 = new Denomination("$1", 1, 10);
		note1.setQuantity(5);
		note5 = new Denomination("$5", 5, 10);
		note5.setQuantity(5);
		notes.add(note1);
		notes.add(note5);

		horse1 = new Horse(1, "That Darn Gray Cat", 5);
		horse1.setDidWin("won");
		horse2 = new Horse(2, "Fort Utopia", 10);
		horse2.setDidWin("lost");

		horses.add(horse1);
		horses.add(horse2);

		mockIo = mock(ConsoleIO.class);
		mockBr = mock(BufferedReader.class);
	}

	@Test
	public void runTest_Quit() throws IOException {

		when(mockIo.read(mockBr)).thenReturn("q");

		new HorseRacingRunner().run(notes, horses, mockIo, mockBr);

		InOrder order = inOrder(mockIo);
		order.verify(mockIo).writeln("Inventory:");
		order.verify(mockIo).writeln("$1,5");
		order.verify(mockIo).writeln("$5,5");
		order.verify(mockIo).writeln("Horses:");
		order.verify(mockIo).writeln("1,That Darn Gray Cat,5,won");
		order.verify(mockIo).writeln("2,Fort Utopia,10,lost");

	}

	@Test
	public void runTest_RestoreDenomination() throws IOException {

		when(mockIo.read(mockBr)).thenReturn("r").thenReturn("q");

		new HorseRacingRunner().run(notes, horses, mockIo, mockBr);

		InOrder order = inOrder(mockIo);
		order.verify(mockIo).writeln("Inventory:");
		order.verify(mockIo).writeln("$1,5");
		order.verify(mockIo).writeln("$5,5");
		order.verify(mockIo).writeln("Horses:");
		order.verify(mockIo).writeln("1,That Darn Gray Cat,5,won");
		order.verify(mockIo).writeln("2,Fort Utopia,10,lost");
		order.verify(mockIo).writeln("Inventory:");
		order.verify(mockIo).writeln("$1,10");
		order.verify(mockIo).writeln("$5,10");
		order.verify(mockIo).writeln("Horses:");
		order.verify(mockIo).writeln("1,That Darn Gray Cat,5,won");
		order.verify(mockIo).writeln("2,Fort Utopia,10,lost");
	}

	@Test
	public void runTest_DoPayout() throws IOException {

		when(mockIo.read(mockBr)).thenReturn("1 5").thenReturn("q");

		new HorseRacingRunner().run(notes, horses, mockIo, mockBr);

		InOrder order = inOrder(mockIo);
		order.verify(mockIo).writeln("Inventory:");
		order.verify(mockIo).writeln("$1,5");
		order.verify(mockIo).writeln("$5,5");
		order.verify(mockIo).writeln("Horses:");
		order.verify(mockIo).writeln("1,That Darn Gray Cat,5,won");
		order.verify(mockIo).writeln("2,Fort Utopia,10,lost");

		order.verify(mockIo).writeln("Payout: That Darn Gray Cat,$25");
		order.verify(mockIo).writeln("Dispensing:");
		order.verify(mockIo).writeln("$1,0");
		order.verify(mockIo).writeln("$5,5");

		order.verify(mockIo).writeln("Inventory:");
		order.verify(mockIo).writeln("$1,5");
		order.verify(mockIo).writeln("$5,0");
		order.verify(mockIo).writeln("Horses:");
		order.verify(mockIo).writeln("1,That Darn Gray Cat,5,won");
		order.verify(mockIo).writeln("2,Fort Utopia,10,lost");
	}

	@Test
	public void runTest_InvalidBet() throws IOException {

		when(mockIo.read(mockBr)).thenReturn("1 2.5").thenReturn("q");

		new HorseRacingRunner().run(notes, horses, mockIo, mockBr);

		InOrder order = inOrder(mockIo);
		order.verify(mockIo).writeln("Inventory:");
		order.verify(mockIo).writeln("$1,5");
		order.verify(mockIo).writeln("$5,5");
		order.verify(mockIo).writeln("Horses:");
		order.verify(mockIo).writeln("1,That Darn Gray Cat,5,won");
		order.verify(mockIo).writeln("2,Fort Utopia,10,lost");

		order.verify(mockIo).writeln("Invalid Bet: 2.5");
		order.verify(mockIo).writeln("Inventory:");
		order.verify(mockIo).writeln("$1,5");
		order.verify(mockIo).writeln("$5,5");
		order.verify(mockIo).writeln("Horses:");
		order.verify(mockIo).writeln("1,That Darn Gray Cat,5,won");
		order.verify(mockIo).writeln("2,Fort Utopia,10,lost");
	}

	@Test
	public void runTest_InvalidCommand() throws IOException {

		when(mockIo.read(mockBr)).thenReturn("E 25").thenReturn("q");

		new HorseRacingRunner().run(notes, horses, mockIo, mockBr);

		InOrder order = inOrder(mockIo);
		order.verify(mockIo).writeln("Inventory:");
		order.verify(mockIo).writeln("$1,5");
		order.verify(mockIo).writeln("$5,5");
		order.verify(mockIo).writeln("Horses:");
		order.verify(mockIo).writeln("1,That Darn Gray Cat,5,won");
		order.verify(mockIo).writeln("2,Fort Utopia,10,lost");

		order.verify(mockIo).writeln("Invalid Command: E 25");

		order.verify(mockIo).writeln("Inventory:");
		order.verify(mockIo).writeln("$1,5");
		order.verify(mockIo).writeln("$5,5");
		order.verify(mockIo).writeln("Horses:");
		order.verify(mockIo).writeln("1,That Darn Gray Cat,5,won");
		order.verify(mockIo).writeln("2,Fort Utopia,10,lost");
	}

	@Test
	public void runTest_ChangeWinner() throws IOException {

		when(mockIo.read(mockBr)).thenReturn("w 2").thenReturn("q");

		new HorseRacingRunner().run(notes, horses, mockIo, mockBr);

		InOrder order = inOrder(mockIo);
		order.verify(mockIo).writeln("Inventory:");
		order.verify(mockIo).writeln("$1,5");
		order.verify(mockIo).writeln("$5,5");
		order.verify(mockIo).writeln("Horses:");
		order.verify(mockIo).writeln("1,That Darn Gray Cat,5,won");
		order.verify(mockIo).writeln("2,Fort Utopia,10,lost");
		order.verify(mockIo).writeln("Inventory:");
		order.verify(mockIo).writeln("$1,5");
		order.verify(mockIo).writeln("$5,5");
		order.verify(mockIo).writeln("Horses:");
		order.verify(mockIo).writeln("1,That Darn Gray Cat,5,lost");
		order.verify(mockIo).writeln("2,Fort Utopia,10,won");
	}

}
