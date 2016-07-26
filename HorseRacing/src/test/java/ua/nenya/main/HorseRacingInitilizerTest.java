package ua.nenya.main;

import static org.junit.Assert.*;

import org.junit.Test;

public class HorseRacingInitilizerTest{
	
	private HorseRacingInitilizer horseRacingInitilizer = new HorseRacingInitilizer();

	@Test
	public void horseRacingInitilizerTest1() {
	assertNotNull(horseRacingInitilizer.initRacing().getHorses());
	}
	
	@Test
	public void horseRacingInitilizerTest2() {
	assertNotNull(horseRacingInitilizer.initRacing().getNotes());
	}
	
}
