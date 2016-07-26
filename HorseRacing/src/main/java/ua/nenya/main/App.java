package ua.nenya.main;

import ua.nenya.domain.Racing;

public class App 
{
    public static void main( String[] args )
    {
    	HorseRacingInitilizer horseRacingInitilizer = new HorseRacingInitilizer();
    	Racing racing = horseRacingInitilizer.initRacing();
    	new HorseRacingRunner().run(racing.getNotes(), racing.getHorses());
    }
    
}
