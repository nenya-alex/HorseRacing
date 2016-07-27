package ua.nenya.domain;

import java.util.ArrayList;
import java.util.List;

public class Racing {
	
	private List<Denomination> notes = new ArrayList<>();
	private List<Horse> horses = new ArrayList<>();
	
	public List<Denomination> getNotes() {
		return notes;
	}
	public void setNotes(List<Denomination> notes) {
		this.notes = notes;
	}
	public List<Horse> getHorses() {
		return horses;
	}
	public void setHorses(List<Horse> horses) {
		this.horses = horses;
	}
}
