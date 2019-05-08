package model.units;

import java.util.ArrayList;

import exceptions.UnitException;
import simulation.Address;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;

public abstract class PoliceUnit extends Unit {
	private ArrayList<Citizen> passengers;
	private int maxCapacity;
	private int distanceToBase;

	public PoliceUnit(String unitID, Address location, int stepsPerCycle,
			WorldListener worldListener, int maxCapacity) throws UnitException {
		super(unitID, location, stepsPerCycle, worldListener);
		passengers = new ArrayList<Citizen>();
		this.maxCapacity = maxCapacity;
	}

	public int getDistanceToBase() {
		return distanceToBase;
	}

	public void setDistanceToBase(int distanceToBase) {
		this.distanceToBase = distanceToBase;
		if (this.distanceToBase <= 0)
			this.distanceToBase = 0;
	}

	@Override
	public void cycleStep() {
		if (distanceToBase != 0) {
			setDistanceToBase(getDistanceToBase() - getStepsPerCycle());
			if (distanceToBase == 0)
				getWorldListener().assignAddress(this, 0, 0);
		} else {
			if (passengers.size() != 0) {

				for (int i = 0; i < passengers.size(); i++) {
					Citizen c = passengers.get(i);
					if (c.getState() != CitizenState.DECEASED)
						c.setState(CitizenState.RESCUED);
					c.getWorldListener().assignAddress(c, 0, 0);
				}
				passengers.clear();
				Address location = ((ResidentialBuilding) getTarget())
						.getLocation();
				setDistanceToTarget(location.getX() + location.getY());
			} else
				super.cycleStep();
		}
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public ArrayList<Citizen> getPassengers() {
		return passengers;
	}
	public String toString() {
		String s = "Unit ID:" + this.getUnitID() + "\n"
				+ "Unit Type :" + this.getClass().toString() +"\n" 
				+ "Unit location : " + this.getLocation().toString() + "\n"
				+ "Steps Per Cycle : " + this.getStepsPerCycle() + "\n"
				+"Target:"+this.getTarget()+"\n"
				+"Unit State:"+this.getState()+"\n";
			if(this.getTarget()!=null){
				s+="Target's Location:"+ this.getTarget().getLocation()+'\n';
			}
		s+="No of Passengers: "+this.passengers.size()+"\n";
		for(int i=0;i<passengers.size();i++) {
			int j=i;
			s+="Passenger"+(j+1)+":"+passengers.get(i).toString()+"\n";
		}
		return s;
	}

}
