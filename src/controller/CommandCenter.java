package controller;

import java.util.ArrayList;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import model.disasters.Disaster;
import model.events.SOSListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.units.Unit;
import simulation.Rescuable;
import simulation.Simulator;

public class CommandCenter implements SOSListener {

	private Simulator engine;
	private ArrayList<ResidentialBuilding> visibleBuildings;
	private ArrayList<Citizen> visibleCitizens;

	@SuppressWarnings("unused")
	private ArrayList<Unit> emergencyUnits;

	public CommandCenter() throws Exception {
		engine = new Simulator(this);
		visibleBuildings = new ArrayList<ResidentialBuilding>();
		visibleCitizens = new ArrayList<Citizen>();
		emergencyUnits = engine.getEmergencyUnits();
		
	}

	@Override
	
	public void receiveSOSCall(Rescuable r) {
		
		if (r instanceof ResidentialBuilding) {
			
			if (!visibleBuildings.contains(r))
				visibleBuildings.add((ResidentialBuilding) r);
			
		} else {
			
			if (!visibleCitizens.contains(r))
				visibleCitizens.add((Citizen) r);
		}

	}

	public ArrayList<ResidentialBuilding> getVisibleBuildings() {
		return visibleBuildings;
	}

	public ArrayList<Citizen> getVisibleCitizens() {
		return visibleCitizens;
	}
	public ArrayList<ResidentialBuilding> getBuilding(){
		return engine.getBuildings();
	}
	public ArrayList<Citizen> getCitizens(){
		return engine.getCitizens() ;
	}
	
	public ArrayList<Unit> getUnit(){
		return engine.getEmergencyUnits();
	}
	public ArrayList<Disaster>getPlanned(){
		return engine.getPlannedDisasters();
	}
	public ArrayList<Disaster>getExecuted(){
		return engine.getExecutedDisasters();
	}
	public void nexCyc() throws Exception{
		engine.nextCycle();
	}
	public  int getCasualtiesNumber() {
		return engine.getCount();
	}
	public void  respondUnit(int index, int x, int y) throws IncompatibleTargetException, CannotTreatException {
		Rescuable r = null;
		for (int i = 0; i < visibleCitizens.size(); i++) {
			if (visibleCitizens.get(i).getLocation().getX() == x
					&& visibleCitizens.get(i).getLocation().getY() == y) {
				r = visibleCitizens.get(i);
			}
		}
		for (int i = 0; i < visibleBuildings.size(); i++) {
			if (visibleBuildings.get(i).getLocation().getX() == x
					&& visibleBuildings.get(i).getLocation().getY() == y) {
				r = visibleBuildings.get(i);
			}
		}
		if (r != null) {
			emergencyUnits.get(index).respond(r);
		}
		
	}
	
	public boolean checkgameOver() {
		return engine.checkGameOver();
	}
}
