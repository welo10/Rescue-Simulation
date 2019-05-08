package model.units;

import exceptions.UnitException;
import model.events.WorldListener;
import simulation.Address;


public abstract class FireUnit extends Unit {

	public FireUnit(String unitID, Address location, int stepsPerCycle,WorldListener worldListener) throws UnitException {
		super(unitID, location, stepsPerCycle,worldListener);
	}

}
