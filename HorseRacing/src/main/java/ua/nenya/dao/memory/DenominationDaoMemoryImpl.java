package ua.nenya.dao.memory;

import java.util.ArrayList;
import java.util.List;

import ua.nenya.dao.DenominationDao;
import ua.nenya.domain.Denomination;

public class DenominationDaoMemoryImpl implements DenominationDao{

	@Override
	public List<Denomination> initDenominations() {
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

}
