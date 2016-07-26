package ua.nenya.main;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ua.nenya.dao.DenominationDao;
import ua.nenya.dao.HorseDao;
import ua.nenya.dao.memory.DenominationDaoMemoryImpl;
import ua.nenya.dao.memory.HorseDaoMemoryImpl;
import ua.nenya.domain.Denomination;
import ua.nenya.domain.Horse;
import ua.nenya.domain.Racing;

public class HorseRacingInitilizer {

	private HorseDao horseDao;
	private DenominationDao denominationDao;

	public Racing initRacing() {
		Racing racing = new Racing();
		
		horseDao = new HorseDaoMemoryImpl();
		List<Horse> horses = horseDao.initHorses();
		Collections.sort(horses, new Comparator<Horse>() {
			public int compare(Horse h1, Horse h2) {
				return h1.getNumber() - h2.getNumber();
			}
		});

		denominationDao = new DenominationDaoMemoryImpl();
		List<Denomination> notes = denominationDao.initDenominations();
		Collections.sort(notes, new Comparator<Denomination>() {
			public int compare(Denomination d1, Denomination d2) {
				return d1.getAmount() - d2.getAmount();
			}
		});
		
		racing.setHorses(horses);
		racing.setNotes(notes);
		
		return racing;
	}

}
