package model.disasters;

import exceptions.*;
import model.infrastructure.ResidentialBuilding;

public class GasLeak extends Disaster {

	public GasLeak(int startCycle, ResidentialBuilding target) throws DisasterException {
		super(startCycle, target);
	}

	@Override
	public void strike() throws BuildingAlreadyCollapsedException, CitizenAlreadyDeadException {

		ResidentialBuilding target = (ResidentialBuilding) getTarget();
		target.setGasLevel(target.getGasLevel() + 10);

		super.strike();

	}

	@Override
	public void cycleStep() {
		ResidentialBuilding target = (ResidentialBuilding) getTarget();
		target.setGasLevel(target.getGasLevel() + 15);

	}

}
