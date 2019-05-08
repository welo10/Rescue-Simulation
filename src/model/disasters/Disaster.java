package model.disasters;

import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CitizenAlreadyDeadException;
import exceptions.DisasterException;
import exceptions.SimulationException;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Rescuable;
import simulation.Simulatable;

public abstract class Disaster implements Simulatable{
	private int startCycle;
	private Rescuable target;
	private boolean active;
	public Disaster(int startCycle, Rescuable target) throws DisasterException {
		this.startCycle = startCycle;
		this.target = target;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getStartCycle() {
		return startCycle;
	}
	public Rescuable getTarget() {
		return target;
	}
	public void strike() throws CitizenAlreadyDeadException, BuildingAlreadyCollapsedException 
	{
		if(target instanceof Citizen&&((Citizen)target).getState()==CitizenState.DECEASED)
			throw new CitizenAlreadyDeadException(this,"Citizen is Dead");
		if(target instanceof ResidentialBuilding && ((ResidentialBuilding)target).getStructuralIntegrity()==0)
			throw new BuildingAlreadyCollapsedException(this,"building Collapsed");
		target.struckBy(this);
		active=true;
	}
	public String getString() {
		String s="Disaster:"+this.getClass().toString()+"\n"
				+"Active:"+this.active+"\n"
				+"Start Cycle:"+this.startCycle+"\n";
		return s;
	}
}
