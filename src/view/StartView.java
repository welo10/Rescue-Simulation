package view;


import javax.swing.*;
import controller.CommandCenter;
import model.disasters.Disaster;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.units.Unit;
import model.units.UnitState;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
@SuppressWarnings("serial")
public class StartView extends JFrame implements ActionListener {
	private JPanel p6;
	private JButton b6;
	private JFrame x;
	private TextArea Cyclec;
	private JButton end;
	private TextArea Casualties;
	private JButton restart;
	private JButton exit;
	private JButton [][] buttons ;
	private JButton [] UnitButtons=new JButton[5]; 
	private int X,Y,CasualtiesNo;
	int cycle=1;
	private ArrayList <ResidentialBuilding> buildings;
	private ArrayList<Unit> units;
	private ArrayList<Citizen> citizens;
	private ArrayList <Disaster> planned;
	private ArrayList <Disaster>executed;
	private CommandCenter center=new CommandCenter();
	TextArea CitizenInfo,BuildingInfo,UnitInfo,DisasterInfo;
	
	public StartView()throws Exception {

				x = new JFrame("GAME");
				x.setExtendedState(JFrame.MAXIMIZED_BOTH);
				x.setVisible(true);
				JPanel p1 = new JPanel();
				p1.setLayout(new GridLayout(10,10));
				p1.setVisible(true);
				x.add(p1, BorderLayout.CENTER);
				BorderFactory.createBevelBorder(1);
				buttons=new JButton [10][10];
			// TODO get buildingss	/////////////////	
				buildings=center.getBuilding();
				units = center.getUnit();
				citizens=center.getCitizens();
				planned=center.getPlanned();
				executed=center.getExecuted();
				CasualtiesNo=center.getCasualtiesNumber();
				
///////////////////////////////////////////////////////////Grid Buttons//////////////////////////////////////////
				for(int i =0 ; i<10 ;i++){
					for(int j=0;j<10;j++){
						JButton b = new JButton( " " ) ;
						p1.add(b);
						buttons[i][j]=b;
						
						}
					}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
				updateBuilding();
				updateCitizen();
				updateUnits1();
				
////////////////////////////////////////////////////////PANELS///////////////////////////////////////////////////				
				JPanel p2 = new JPanel() ;  //BIG PANEL
				p2.setVisible(true);
				p2.setPreferredSize(new Dimension (500,200));
				p2.setBackground(Color.WHITE);
				x.add(p2,BorderLayout.WEST);
				
				JPanel p3 = new JPanel() ;
				p3.setVisible(true);
				p3.setLayout(null);
				p3.setPreferredSize(new Dimension(500,100));
				p3.setBackground(Color.black);
				p2.add(p3,BorderLayout.NORTH);
				
				JPanel p4 = new  JPanel();
				p4.setVisible(true);
				p4.setPreferredSize(new Dimension(500,450));
				p2.add(p4,BorderLayout.CENTER);
				
				JPanel p5 = new  JPanel();
				p5.setVisible(true);
				p5.setPreferredSize(new Dimension(500,450));
				p2.add(p5,BorderLayout.SOUTH);
				
				
				JPanel bigright = new JPanel();
				bigright.setVisible(true);
				bigright.setPreferredSize(new Dimension(500,200));
				x.add(bigright,BorderLayout.EAST);
				
				p6 = new  JPanel();
				p6.setVisible(true);
				p6.setPreferredSize(new Dimension(500,450));
				bigright.add(p6,BorderLayout.NORTH);
				
				JPanel mid = new JPanel();
				mid.setVisible(true);
				mid.setPreferredSize(new Dimension(500,450));
				bigright.add(mid,BorderLayout.CENTER);
						
				JPanel res = new JPanel();
				res.setVisible(true);
				res.setPreferredSize(new Dimension(500, 450));
				res.setBackground(Color.BLACK);
				res.setBounds(100, 100, 100, 100);
				bigright.add(res, BorderLayout.CENTER);
				
//////////////////////Unit Buttons view//////////////////////////////////////////
				JButton b1=new JButton("Ambulance");
				b1.setBackground(Color.WHITE);
				b1.setForeground(Color.BLACK);
				b1.setVisible(true);
				b1.setPreferredSize(new Dimension(200,100));
				p6.add(b1);
				UnitButtons[0]=b1;
				b1.addActionListener(this);
				
				
					
				JButton b2=new JButton("Gas Control Unit");
				b2.setBackground(Color.WHITE);
				b2.setForeground(Color.BLACK);
				b2.setVisible(true);
				b2.setPreferredSize(new Dimension(200,100));
				p6.add(b2);
				UnitButtons[4]=b2;
				b2.addActionListener(this);
				
				JButton b3=new JButton("Disease Control Unit");
				b3.setBackground(Color.WHITE);
				b3.setForeground(Color.BLACK);
				b3.setVisible(true);
				b3.setPreferredSize(new Dimension(200,100));
				p6.add(b3);
				UnitButtons[1]=b3;
				b3.addActionListener(this);
				
				JButton b4=new JButton("Evacuator");
				b4.setBackground(Color.WHITE);
				b4.setForeground(Color.BLACK);
				b4.setVisible(true);
				b4.setPreferredSize(new Dimension(200,100));
				p6.add(b4);
				UnitButtons[2]=b4;
				b4.addActionListener(this);
				
				
				JButton b5=new JButton("Fire Truck");
				b5.setBackground(Color.WHITE);
				b5.setForeground(Color.BLACK);
				b5.setVisible(true);
				b5.setPreferredSize(new Dimension(200,100));
				p6.add(b5);
				UnitButtons[3]=b5;
				b5.addActionListener(this);
		
///////////////////////////////// TEXT AREAS ///////////////////////////////////////////				
				Cyclec = new TextArea("CurrentCycle :");
				Cyclec.setText("Current Cycle:");
				Cyclec.setEditable(false);
				Cyclec.setForeground(Color.magenta);
				Cyclec.setBounds(20,20,150,50);
				Cyclec.setVisible(true);
				p3.add(Cyclec);
				
				
				Casualties= new TextArea("0");
				Casualties.setText("Number of Casualties:");
				Casualties.setEditable(false);
				Casualties.setBounds(220 , 20, 200, 50);
				Casualties.setVisible(true);
				p3.add(Casualties);
				
				CitizenInfo= new TextArea("0");
				CitizenInfo.setText("Citizen:");
				CitizenInfo.setEditable(false);
				CitizenInfo.setBounds(180 , 20, 200, 70);
				CitizenInfo.setVisible(true);
				p4.add(CitizenInfo);
				
				BuildingInfo=new TextArea("");
				BuildingInfo.setText("Building:");
				BuildingInfo.setEditable(false);
				BuildingInfo.setBounds(180 , 200, 200, 70);
				BuildingInfo.setVisible(true);
				p4.add(BuildingInfo);
				
				UnitInfo=new TextArea("");
				UnitInfo.setText("Unit:");
				UnitInfo.setEditable(false);
				UnitInfo.setBounds(180 , 150, 200, 70);
				UnitInfo.setVisible(true);
				p4.add(UnitInfo);
				
				DisasterInfo=new TextArea("");
				DisasterInfo.setText("Executed Disasters:"+"\n");
				DisasterInfo.setEditable(false);
				DisasterInfo.setBounds(180 , 200, 200, 70);
				DisasterInfo.setVisible(true);
				//this.setLayout(null);
				p5.add(DisasterInfo);
				
				
////////////////////////////////////////////////////Controllers/////////////////////////////////////////////////////////////			
				
				b6=new JButton("Next Cycle: 1");
				b6.setVisible(true);
				b6.setBackground(Color.yellow);
				b6.setForeground(Color.BLACK);
				b6.setPreferredSize(new Dimension(200,100));
				mid.add(b6);
				b6.addActionListener(this);
				

				end = new JButton("End Current Cycle") ;
				end.setVisible(true);
				end.setBackground(Color.BLACK);
				end.setForeground(Color.WHITE);
				end.setPreferredSize(new Dimension(200,100));
				mid.add(end);
				end.addActionListener(this);
				
				

				restart = new JButton("Restart");
				restart.setVisible(true);
				restart.setPreferredSize(new Dimension(200, 100));
				mid.add(restart);
				restart.addActionListener(this);
				
				exit = new JButton("End Game");
				exit.setVisible(true);
				exit.setPreferredSize(new Dimension(200, 100));
				mid.add(exit);
				exit.addActionListener(this);
		}
/////////////SET THE DISASTER LOG///////////////
			public void  setDisasterInfo() {
				if(executed.size()==0) {
					DisasterInfo.setText("No Executed Disasters yet"+"\n");
				}
				else {
					DisasterInfo.setText("Executed Disasters:"+"\n");
					for(int i=0;i<executed.size();i++) {
					
						if(executed.get(i)!=null) {
							DisasterInfo.setText(DisasterInfo.getText()+executed.get(i).getString()+"\n");
						}
					}
				
				}
			}

///////////////////////////////////////////////////////Citizen"s Buttons//////////////////////////////////////////////////
				public void updateCitizen (){
						Boolean exist=false;
						for(int i=0;i<citizens.size();i++){
							int x1=citizens.get(i).getLocation().getX();
							int y1=citizens.get(i).getLocation().getY();
							for(int j=0;j<buildings.size();j++){
								int x2=buildings.get(j).getLocation().getX();
								int y2=buildings.get(j).getLocation().getY();
								if(x2==x1&& y2==y1){
									exist=true;
								}
								
							}
							if(exist!=true){
								buttons[x1][y1].setText("C"+"\n");
								buttons[x1][y1].setBackground(Color.WHITE);
								if(citizens.get(i).getDisaster()!=null) {
									buttons[x1][y1].setBackground(Color.RED);
								}
							}
							exist=false;
							buttons [x1][y1].addActionListener(this);
						}
					}
				
				public void updateCitizenInfo(int x,int y) {
					Boolean exist=false;
					for(int i=0;i<citizens.size();i++){
						if(citizens.get(i).getLocation().getX()==x&&citizens.get(i).getLocation().getY()==y) {
							int x1=citizens.get(i).getLocation().getX();
							int y1=citizens.get(i).getLocation().getY();
							for(int j=0;j<buildings.size();j++){
								int x2=buildings.get(j).getLocation().getX();
								int y2=buildings.get(j).getLocation().getY();
								if(x2==x1&& y2==y1){
									exist=true;
								}
							}
							if(!exist) {
								clr();
								CitizenInfo.setText(""+citizens.get(i).toString());	
							}
							exist=false;
						}
					}
				}
/////////////////////////////////////////////////////Building's Buttons///////////////////////////////////////////////////////////////
			public void updateBuilding() {
			
					for(int i=0;i<buildings.size();i++){
						int x0=buildings.get(i).getLocation().getX();
						int y0=buildings.get(i).getLocation().getY();
						buttons [x0][y0].setText("B"+"\n");
						if(buildings.get(i).getDisaster()!=null) {
							buttons [x0][y0].setBackground(Color.RED);
						}
						buttons [x0][y0].addActionListener(this);
					}
				}
			public void updateBuildingInfo(int x,int y) {
				for(int i=0;i<buildings.size();i++){
					if(buildings.get(i).getLocation().getX()==x&&buildings.get(i).getLocation().getY()==y) {
						clr();
						BuildingInfo.setText(""+buildings.get(i).toString());	
						CitizenInfo.setText(buildings.get(i).getCitizenString());
					}
				}
			}
			
//////////////////////////////UNITS BUTTONS /////////////////////////////////////////////////////////
			public void updateUnits() {
					for (int i = 0; i < UnitButtons.length; i++) {
						if (units.get(i).getState() == UnitState.IDLE) {
							UnitButtons[i].setBackground(Color.WHITE);
						} else if (units.get(i).getState() == UnitState.RESPONDING) {
							UnitButtons[i].setBackground(Color.GREEN);
						} else {
							UnitButtons[i].setBackground(Color.YELLOW);
					}
					}
			}
			public void updateUnits1() {
				for(int i=0;i<units.size();i++){
					int x1=units.get(i).getLocation().getX();
					int y1=units.get(i).getLocation().getY();
						buttons[x1][y1].setText("U"+"\n");
						buttons[x1][y1].setBackground(Color.black);
						buttons[x1][y1].setForeground(Color.WHITE);
						buttons [x1][y1].addActionListener(this);
					}
			}
			public void updateUnitInfo(int x,int y) {
				String s="Units:";
				for(int i=0;i<units.size();i++){
					if(units.get(i).getLocation().getX()==x&&units.get(i).getLocation().getY()==y) {
						clr();
						s+="\n"+units.get(i).toString();	
					}
				}
				UnitInfo.setText(s);
			}
			
				
/////////SET THE TEXT AREAS AS EMPTY//////////////
			public void clr() {
				UnitInfo.setText("Unit:"+"\n");
				CitizenInfo.setText("Citizen:"+"\n");
				BuildingInfo.setText("Building:"+"\n");

			}

//////////////////////////Main Method///////////////////////////////////////		

	public static void main(String[]args) throws Exception {
		new StartView();

	}
///////////////////Action Buttons////////////////////////////////////////////////////////////
	@Override
	public void actionPerformed(ActionEvent e1) {
		// TODO Auto-generated method stub
		if (e1.getSource()==b6) {
			try {
				center.nexCyc();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clr();
			updateBuilding();
			updateCitizen();
			updateUnits();
			//updateUnits1();
			setDisasterInfo();
			
			Cyclec.setText("Current Cycle:"+cycle);
			b6.setText(" Next Cycle: "+ ++cycle);
			Casualties.setText("Number of Casualties:"+center.getCasualtiesNumber());
			setDisasterInfo();
			if(center.checkgameOver()) {					
				JOptionPane.showConfirmDialog(null,
						"Game Over. Casualties Number:"+""+center.getCasualtiesNumber(), " ", JOptionPane.PLAIN_MESSAGE);
				JOptionPane.showConfirmDialog(null,
						"This Game Has Ended."+"\n", " ", JOptionPane.PLAIN_MESSAGE);
				JOptionPane.showConfirmDialog(null,
						"Better Luck Next Time", " ", JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			}
		}
	
		for (int i = 0; i < UnitButtons.length; i++) {
			if (e1.getSource() == UnitButtons[i]) {
				UnitInfo.setText(""+units.get(i).toString());
				try {
					center.respondUnit(i, X, Y);
					//updateUnits();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
					
				}
				updateUnits();
			}
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (e1.getSource() == buttons[i][j]) {
					X= i;
					Y =j;
					updateBuildingInfo(i, j);
					updateCitizenInfo(i, j);
					updateUnitInfo(i,j);
					//updateUnits1();
				}
			}
		}
		
		if(e1.getSource()==end) {
			Cyclec.setText("Current Cycle:"+cycle);
			b6.setText(" Next Cycle: "+ ++cycle);
			Casualties.setText("Number of Casualties:"+""+CasualtiesNo);
			setDisasterInfo();
		}
		if(e1.getSource()==restart) {
			try {
				new StartView();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			x.setVisible(false);
		}
		if(e1.getSource()==exit) {
			JOptionPane.showConfirmDialog(null,
					"This Game Has Ended."+"\n", " ", JOptionPane.PLAIN_MESSAGE);
			JOptionPane.showConfirmDialog(null,
					"Better Luck Next Time", " ", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
		}
	}
	


