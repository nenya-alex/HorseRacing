package ua.nenya.main;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import ua.nenya.consoleIO.ConsoleIO;
import ua.nenya.domain.Denomination;
import ua.nenya.domain.Horse;

public class MethodsTest {
	
	private Methods methods = new Methods();
	private List<Denomination> notes = new ArrayList<>();
	private List<Horse> horses = new ArrayList<>();
	private Denomination note1;
	private Denomination note5;
	private Horse horse1;
	private Horse horse2;
	private ConsoleIO mockIO;
	
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
		
		mockIO = mock(ConsoleIO.class);
	}
	

	@Test
	public void isCommandEqualsRegexTest1() {
		assertThat(methods.isCommandEqualsRegex("1 55", "^\\d+\\s\\d+$"), is(true));
	}
	
	@Test
	public void isCommandEqualsRegexTest2() {
		assertThat(methods.isCommandEqualsRegex("1 5.5", "^\\d+\\s\\d+$"), is(false));
	}
	
	@Test
	public void showInventoryAndHorsesTest() {		
		methods.showInventoryAndHorses(notes, horses, mockIO);
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mockIO, times(6)).writeln(captor.capture());
        assertEquals(
                "[Inventory:, $1,5, $5,5, Horses:, 1,That Darn Gray Cat,5,won, 2,Fort Utopia,10,lost]"
                , captor.getAllValues().toString());
	}
	
	@Test
	public void restoreCashInventoryTest() {
		assertThat(methods.restoreCashInventory(notes).get(0).getQuantity(), is(note1.getInitialQuantity()));
		assertThat(methods.restoreCashInventory(notes).get(1).getQuantity(), is(note5.getInitialQuantity()));
	}
	
	@Test
	public void changeHorseWinnerFirstHorseTest() {
		methods.changeHorseWinner("w 1", horses, mockIO);
		assertThat(horses.get(0).getDidWin(), is("won"));
		assertThat(horses.get(1).getDidWin(), is("lost"));
	}
	
	@Test
	public void changeHorseWinnerSecondHorseTest() {
		methods.changeHorseWinner("W2", horses, mockIO);
		assertThat(horses.get(0).getDidWin(), is("lost"));
		assertThat(horses.get(1).getDidWin(), is("won"));
	}
	
	@Test
	public void changeHorseWinnerEmptyHorseTest() {
		methods.changeHorseWinner("W 123", horses, mockIO);
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mockIO, times(1)).writeln(captor.capture());
        assertEquals(
                "[Invalid Horse Number: 123]"
                , captor.getAllValues().toString());
	}
	
	@Test
	public void checkPayoutInsufficientFundsTest() {
		methods.checkPayout("1 100000", horses, notes, mockIO);
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mockIO, times(1)).writeln(captor.capture());
        assertEquals(
                "[Insufficient Funds: 500000]"
                , captor.getAllValues().toString());
	}
	
	@Test
	public void checkPayoutNoPayoutTest() {
		methods.checkPayout("2 100", horses, notes, mockIO);
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mockIO, times(1)).writeln(captor.capture());
        assertEquals(
                "[No Payout: Fort Utopia]"
                , captor.getAllValues().toString());
	}
	
	@Test
	public void checkPayoutInvalidBetTest() {
		methods.checkPayout("1 2.3", horses, notes, mockIO);
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mockIO, times(1)).writeln(captor.capture());
        assertEquals(
                "[Invalid Bet: 2.3]"
                , captor.getAllValues().toString());
	}
	
	@Test
	public void checkPayoutTest() {
		methods.checkPayout("1 2", horses, notes, mockIO);
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mockIO, times(4)).writeln(captor.capture());
        assertEquals(
                "[Payout: That Darn Gray Cat,$10, Dispensing:, $1,0, $5,2]"
                , captor.getAllValues().toString());
	}
	
	@Test
	public void checkPayoutNoHorseTest() {
		methods.checkPayout("12 2", horses, notes, mockIO);
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mockIO, times(1)).writeln(captor.capture());
        assertEquals(
                "[No Payout: That Darn Gray Cat]"
                , captor.getAllValues().toString());
	}
	
	@Test
	public void checkPayoutAllNotesTest() {
		methods.checkPayout("1 6", horses, notes, mockIO);
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mockIO, times(4)).writeln(captor.capture());
        assertEquals(
                "[Payout: That Darn Gray Cat,$30, Dispensing:, $1,5, $5,5]"
                , captor.getAllValues().toString());
	}

}
