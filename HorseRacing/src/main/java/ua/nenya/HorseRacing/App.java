package ua.nenya.HorseRacing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ua.nenya.domain.Denomination;
import ua.nenya.domain.Horse;

public class App 
{
    public static void main( String[] args )
    {
    	App app = new App();
    	
    	List<Denomination> notes = app.initDenominations();
    	
		Collections.sort(notes, new Comparator<Denomination>() {
			public int compare(Denomination d1, Denomination d2) {
				return d1.getAmount() - d2.getAmount();
			}
		});

		List<Horse> horses = app.initHorses();
		
		Collections.sort(horses, new Comparator<Horse>() {
			public int compare(Horse h1, Horse h2) {
				return h1.getNumber() - h2.getNumber();
			}
		});
		
    	new HorseRacing().run(notes, horses);
    }
    
    private List<Denomination> initDenominations() {
		Denomination note1 = new Denomination("$1", 1, 10);
		note1.setQuantity(note1.getInitialQuantity());

		Denomination note5 = new Denomination("$5", 5, 10);
		note5.setQuantity(note5.getInitialQuantity());

		Denomination note10 = new Denomination("$10", 10, 10);
		note10.setQuantity(note10.getInitialQuantity());

		Denomination note20 = new Denomination("$20", 20, 10);
		note20.setQuantity(note20.getInitialQuantity());

		Denomination note100 = new Denomination("$100", 100, 10);
		note100.setQuantity(note100.getInitialQuantity());

		List<Denomination> notes = new ArrayList<>();
		notes.add(note1);
		notes.add(note5);
		notes.add(note10);
		notes.add(note20);
		notes.add(note100);

		return notes;
	}

	private List<Horse> initHorses() {
		Horse horse1 = new Horse(1, "That Darn Gray Cat", 5);
		horse1.setDidWin("won");
		Horse horse2 = new Horse(2, "Fort Utopia", 10);
		horse2.setDidWin("lost");
		Horse horse3 = new Horse(3, "Count Sheep", 9);
		horse3.setDidWin("lost");
		Horse horse4 = new Horse(4, "Ms Traitour", 4);
		horse4.setDidWin("lost");
		Horse horse5 = new Horse(5, "Real Princess", 3);
		horse5.setDidWin("lost");
		Horse horse6 = new Horse(6, "Pa Kettle", 5);
		horse6.setDidWin("lost");
		Horse horse7 = new Horse(7, "Gin Stinger", 6);
		horse7.setDidWin("lost");

		List<Horse> horses = new ArrayList<>();
		horses.add(horse1);
		horses.add(horse2);
		horses.add(horse3);
		horses.add(horse4);
		horses.add(horse5);
		horses.add(horse6);
		horses.add(horse7);

		return horses;
	}
}
