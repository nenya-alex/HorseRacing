package ua.nenya.dao.memory;

import java.util.ArrayList;
import java.util.List;

import ua.nenya.dao.HorseDao;
import ua.nenya.domain.Horse;

public class HorseDaoMemoryImpl implements HorseDao {

	@Override
	public List<Horse> initHorses() {
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
