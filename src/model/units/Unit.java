package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import exceptions.UnitException;
import model.disasters.Disaster;
import model.disasters.Infection;
import model.disasters.Injury;
import model.events.SOSResponder;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
import simulation.Rescuable;
import simulation.Simulatable;

public abstract class Unit implements Simulatable, SOSResponder {
	private String unitID;
	private UnitState state;
	private Address location;
	private Rescuable target;
	private int distanceToTarget;
	private int stepsPerCycle;
	private WorldListener worldListener;

	public Unit(String unitID, Address location, int stepsPerCycle, WorldListener worldListener) throws UnitException {
		this.unitID = unitID;
		this.location = location;
		this.stepsPerCycle = stepsPerCycle;
		this.state = UnitState.IDLE;
		this.worldListener = worldListener;
	}

	public void setWorldListener(WorldListener listener) {
		this.worldListener = listener;
	}

	public WorldListener getWorldListener() {
		return worldListener;
	}

	public UnitState getState() {
		return state;
	}

	public void setState(UnitState state) {
		this.state = state;
	}

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}

	public String getUnitID() {
		return unitID;
	}

	public Rescuable getTarget() {
		return target;
	}

	public int getStepsPerCycle() {
		return stepsPerCycle;
	}

	public void setDistanceToTarget(int distanceToTarget) {
		this.distanceToTarget = distanceToTarget;
	}

	public void respond(Rescuable r) throws IncompatibleTargetException, CannotTreatException {

		if (r instanceof Citizen && (this instanceof FireUnit || this instanceof PoliceUnit))
			throw new IncompatibleTargetException(this, r, "Available for Buildings only");
		if (r instanceof ResidentialBuilding && (this instanceof Ambulance || this instanceof DiseaseControlUnit))
			throw new IncompatibleTargetException(this, r, "Available for Citizens only");
		if (!canTreat(r))
			throw new CannotTreatException(this, r, "cannot treat");

		if (target != null && state == UnitState.TREATING)
			reactivateDisaster();
		finishRespond(r);

	}

	public void reactivateDisaster() {
		Disaster curr = target.getDisaster();
		curr.setActive(true);
	}

	public void finishRespond(Rescuable r) {
		target = r;
		state = UnitState.RESPONDING;
		Address t = r.getLocation();
		distanceToTarget = Math.abs(t.getX() - location.getX()) + Math.abs(t.getY() - location.getY());

	}

	public abstract void treat();

	public void cycleStep() {
		if (state == UnitState.IDLE)
			return;
		if (distanceToTarget > 0) {
			distanceToTarget = distanceToTarget - stepsPerCycle;
			if (distanceToTarget <= 0) {
				distanceToTarget = 0;
				Address t = target.getLocation();
				worldListener.assignAddress(this, t.getX(), t.getY());
			}
		} else {
			state = UnitState.TREATING;
			treat();
		}
	}

	public void jobsDone() {
		target = null;
		state = UnitState.IDLE;

	}

	public boolean canTreat(Rescuable r) {
		if (r instanceof Citizen) {
			Citizen c = (Citizen) r;
			if (c.getState() == CitizenState.SAFE)
				return false;
			if (c.getDisaster() instanceof Injury && !(this instanceof Ambulance))
				return false;
			if (c.getDisaster() instanceof Infection && !(this instanceof DiseaseControlUnit))
				return false;
		} else {
			ResidentialBuilding b = (ResidentialBuilding) r;
			if (b.getFireDamage() == 0 && this instanceof FireTruck)
				return false;
			if (b.getGasLevel() == 0 && this instanceof GasControlUnit)
				return false;
			if (b.getFoundationDamage() == 0 && this instanceof Evacuator)
				return false;
		}
		return true;
	}
	public String toString() {
		String s = "Unit ID:" + this.unitID + "\n"
			+ "Unit Type :" + this.getClass().toString() +"\n" 
			+ "Unit location : " + this.getLocation().toString() + "\n"
			+ "Steps Per Cycle : " + this.stepsPerCycle + "\n"
			+"Target:"+this.target+"\n"
			+"Unit State:"+this.state+"\n";
		if(this.target!=null){
			s+="Target's Location:"+ this.target.getLocation()+'\n';
		}
		return s;
	}
}
