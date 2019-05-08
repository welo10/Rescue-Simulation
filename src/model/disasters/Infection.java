package model.disasters;

import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CitizenAlreadyDeadException;
import exceptions.DisasterException;
import model.people.Citizen;


public class Infection extends Disaster {

	public Infection(int startCycle, Citizen target) throws DisasterException {
		super(startCycle, target);
	}
@Override
public void strike() throws CitizenAlreadyDeadException, BuildingAlreadyCollapsedException 
{
	Citizen target = (Citizen)getTarget();
	target.setToxicity(target.getToxicity()+25);
		super.strike();

}
	@Override
	public void cycleStep() {
		Citizen target = (Citizen)getTarget();
		target.setToxicity(target.getToxicity()+15);
		
	}

}
