import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



// class which represents the Array List and all of it's functionality
public class ArrayListCelest <e> extends JPanel implements ActionListener, list{
	ArrayList<celestialBody> a;
	int size;
	float pixeldist;
	final double G = 6.67430*Math.pow(10,  -11);
	Timer tm = new Timer(50, this);
	ArrayListCelest(){
		a = new ArrayList<celestialBody>();
		size= 0;
	}
	
	public void add(celestialBody c) { // add method from list interface
	a.add(c);
	size++;
	}
	public void delete(int x) { // delete method from list interface
		
		a.remove(x);
		size--;
	}
	public celestialBody get(int x) { // get method from list interface
		celestialBody b = a.get(x);
		return b;
	}
	
	public void printAll( ) { // for testing purposes
		for(int i = 0; i <size; i++) {
			celestialBody c = this.get(i);
			System.out.println(c.name);
			System.out.println(c.mass);
			System.out.println(c.XC);
			System.out.println(c.YC);
			System.out.println(c.XV);
			System.out.println(c.YV);
			System.out.println(c.pixels);
		}
		
	}
	public ArrayListCelest<CelestialBodies> createList() { // uses the filehandler to get the files attributes
		ArrayListCelest n = new ArrayListCelest();		   // and creates our ArrayList of CelestialBodies
		filehandler f = new filehandler();
		f.openfile(f.path);
		f.GetAtt();
		for(int i = 2; i< f.g.size(); i++) {
			celestialBody j = new celestialBody();
			String [] arr = f.g.get(i).split(",");
			for(int g=0; g< arr.length; g++) {
				switch(g) {
				case 1:j.setName(arr[0]);
				case 2: j.setMass(Double.parseDouble(arr[1]));
				case 3: j.setXC(Integer.parseInt(arr[2]));
				case 4: j.setYC(Integer.parseInt(arr[3]));
				case 5: j.setXV(Double.parseDouble(arr[4]));
				case 6: j.setYV( Double.parseDouble(arr[5]));
				case 7: j.setPixels( Integer.parseInt(arr[6]));
				}
					
			}
			n.add(j);
		}
		n.pixeldist = f.distance;
		return n;
	}
	
	public void draw (Graphics g) { // fills our screen with celestial bodies
		Random rand = new Random();
		int r,gr,b;
		r = rand.nextInt(256); // random red value
		gr = rand.nextInt(256);// random green value
		b = rand.nextInt(256); // random blue value
		Color randcolor = new Color(r , gr, b);
		for(int i= 0; i < this.size; i++) { // filling screen with objects
			g.setColor(randcolor);
			g.fillOval(Math.round(a.get(i).XC), Math.round(a.get(i).YC), a.get(i).pixels, a.get(i).pixels);
			tm.start();
	}
	//	tm.start();
		
	}
	
	
	public void actionPerformed(ActionEvent e) { // occurs @ every iteration of the timer
		
		for(celestialBody i: a) {
			for(celestialBody j : a) { // compares every object to every other object in our data structure
				if(i != j) { // while we aren't comparing the same body
					/*if(i.XC <= i.pixels/2 || i.XC >= 768 -(i.pixels/2)) {
						i.YV = -i.YV;
						i.XV = -i.XV;
					}
					if(i.YC <= i.pixels/2|| i.YC >= 768 -(i.pixels/2)) {
						i.YV = -i.YV;
						i.XV = -i.XV;
						i.YV = 0;
					}*/
					/*if(j.XC <= j.pixels/2 || j.XC >= 768 -(j.pixels/2)) {
						j.YV = -j.YV;
						j.XV = -j.XV;
					}
					if(j.YC <= j.pixels/2|| j.YC >= 768 -(j.pixels/2)) {
						j.YV = -j.YV;
						j.XV = -j.XV;
					}*/
					float x1 = (float)i.XV; // initial x-velocity of body being compared
					float y1 = (float)i.YV; // initial y-velocity of body being compared
					float x2 = (float)j.XV; // initial X-velocity of "all other bodies"
					float y2 = (float)j.YV; // initial y-velocity of "all other bodies"
					System.out.println(i.GetName()+" "+x1+" " + y1);// testing
					double masses = i.getMass() * j.getMass(); // whats our combined mass?
					System.out.println("masses:"+ masses); //testing
					double distx = i.getXC() -j.getXC(); // our distance in terms of x
					distx *=pixeldist; // scaling to size
					System.out.println("distx: "+distx);//testing
					double disty = i.getYC()- j.getYC(); // distance in terms of y components
					disty *=pixeldist; // scaling to size
					System.out.println("disty: "+disty); //testing
					double r = Math.sqrt(Math.pow(distx, 2) + Math.pow(disty, 2));  // actual distance between body1 and body2
					System.out.println("r: "+r);
					double force = G*((masses)/(r*r)); // calculating force
					System.out.println("force: "+force + "("+ masses+")"+" "+ r*r);
						if(distx > 0) { // if our "distance" is a positive value...
							x1 += force* (distx/r); // 
							x2 += force* (distx/r);
						}
						else if(distx < 0) { // if our distance returns negative...
							x1+=-1*force*(distx/r);
							x2+=-1*force*(distx/r);
						}
						//x1 = x1*200;
						x2 = x2*300; //scaling for effect
						if(disty > 0) { //repeat for y
							y1 += force*(disty/r);
							y2 += force*(disty/r);
						}
						else if (disty < 0){ //repeat for y
							y1+= -1*force*(disty/r);
							y2+=-1*force*(disty/r);
						}
						//y1 = y1*200;
						y2 = y2*300; //scaling for effect
						System.out.println("new x n y: "+x1 + ","+y1);
						i.YC +=(int) y2; //setting new Y-coordinate
						i.XC += (int)x2; // setting new X-coordinate
						j.XV += force/masses; // setting new velocity
						j.YV+= force/masses; //setting new velocity
						//j.YC +=(int) y2;
						//j.XC += (int)x2;
						System.out.println(pixeldist+ " "+ force+ " "+i.XC +" " +i.YC);
			
				
			}
			}
			
			}
		
		repaint();
	}
	@Override
	public void paintComponent(Graphics g) {// "driver-esque" code for drawing bootleg universe
		super.paintComponent(g);
			this.draw(g);
		
	
	}
	public static void main(int e) { //  main function to be called if ArrayList is passed as datatype
		ArrayListCelest c = new ArrayListCelest();
		c = c.createList();
		JFrame jf = new JFrame();	
		jf.setPreferredSize(new Dimension(768,768));
		jf.setTitle("tutorial");
		jf.setSize(780,780);
		jf.add(c);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}
		
	}
	

