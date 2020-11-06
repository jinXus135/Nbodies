import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
//class for linkedlist and all of it's functionality
public class LinkedListCelest<e> extends JPanel implements ActionListener, list{
	Node head;
	int track;
	Timer tm = new Timer(50, this);//timer
	final double G = 6.67430*Math.pow(10,  -11); //Universal Gravitational constant 
	float pixeldist;
	class Node{ //Node class
		celestialBody data; 
		Node next; 
		
		Node() {
			data = null;
			next = null;
		}
		Node(celestialBody d){
			data = d;
			next = null;
		}
	}
	
	public LinkedListCelest(){
		head = null;
		track = 0;
	}
	public void add(celestialBody body) {//from list interface
		Node newn = new Node(body); // new node to be inserted at tail
		Node phold = head;//place holder node for later
		
	
		if(phold == null) { // if head is empty...
			head = newn;
			track++;// replace with our new node
		}
		else {				// otherwise..
			while(phold.next != null) { // while there is something after our current node...
				phold = phold.next; // set our current to the next node
			}
			phold.next = newn; // once we've reached the last node set it's next to our new node to be added
			//track++;	// increment tracker to keep track of size
		}
		track++;	// increment tracker to keep track of size
	
	}
	
	public celestialBody get(int pos) {		// getter for nodes in list given an index position/ from list interface
		Node nhead = head; // our head value
		for(int i = 0; i < pos-1; i++) { // loop through our list
			head = head.next;
				
		}
		celestialBody tbr = head.data;
		
		// getting the data we were looking for
		head = nhead;
		return tbr; //returning our desired data
	}
	
	
	public void delete (int pos) { // from list interface
		Node og = head; // storing our head
		if(pos == 0) {
			og = head.next; //
		}
		for(int i=0; i<pos-1; i++) {
			
				head = head.next;
			}
		celestialBody tobereturned = head.next.data; // int value being removed
		head.next = head.next.next; // setting the pointer for the current value to the Node that was being pointed to by deleted node
		head = og; // resetting the head
		track--;// decrement tracker


	}
	public void printAll() { // for testing purposes
		Node phold = head;
		for(int i =0; i<track-1; i++ ) {
			System.out.println(phold.data.name);
			System.out.println(phold.data.mass);
			System.out.println(phold.data.XC);
			System.out.println(phold.data.YC);
			System.out.println(phold.data.XV);
			System.out.println(phold.data.YV);
			System.out.println(phold.data.pixels);
			phold = phold.next;
		}
	}
	LinkedListCelest<celestialBody> createList() { // instantiating our DataStruct using filehandler
		LinkedListCelest<celestialBody> b = new LinkedListCelest<celestialBody>();
		filehandler f = new filehandler();
		f.openfile(f.path);
		f.GetAtt();
		b.pixeldist = f.distance;
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
			b.add(j);
		//b.pixeldist = f.distance;
		}
		
		return b;
	}
	
	public void draw (Graphics g) { // renders all celestial bodies on screen
		Node hold = head;
		for(int i= 0; i < this.track-1; i++) {
			g.setColor(Color.red);
			g.fillOval(hold.data.XC, hold.data.YC, hold.data.pixels, hold.data.pixels);
			hold = hold.next;
			tm.start();
		}
		
		//tm.start();
	}
	
	public void actionPerformed(ActionEvent e) { // to be called every iteration of our timer
		Node hold = head;
		
		for(int i = 0; i< this.track; i++) { //for everything in our linkedlist
			celestialBody phold = this.get(i); // getting celestial body for comparing
			for(int j =0; j < this.track; j++) { // loop to compare everything else to phold
				if(hold != null && hold.data != phold) { // as long as we aren't out of bounds and not comaring the same thing...
					float x1 = (float)phold.XV; // initial X-velocity of first celestial body being compared
					float y1 = (float) phold.YV; // initial Y-velocity of first celestial body being compared 
					float x2 = (float)hold.data.getXV(); // initial X-velocity of "everything else"
					float y2 = (float)hold.data.getYV();// initial y-velocity of "everything else"
					double masses = phold.getMass() * hold.data.getMass(); // gotta have a mass
					System.out.println("masses:"+ masses);
					double distx =phold.XC-hold.data.XC ; // our distance in terms of X
					distx *=pixeldist; //scaling
					System.out.println("distx: "+distx+ "pixeldist:"+ pixeldist);
					double disty = (phold.getYC() -hold.data.getYC()); //distance in terms of Y
					disty *=pixeldist; // scaling
					System.out.println("disty: "+disty);
					double r = Math.sqrt(Math.pow(distx, 2) + Math.pow(disty, 2)); // getting our actual distance using X and Y components
					System.out.println("r: "+r);
					double force = G*((masses)/(r*r));//calculating force
					System.out.println("force: "+force + "("+ masses+")"+" "+ r*r);
					if(distx > 0) { // if our distance is positive...
						x1 += force* (distx/r); // calculating force for x
						x2 += force* (distx/r); 
					}
					else if(distx < 0){// if our distance is negative...
						x1 += -1*force*(distx/r); // calculate for for x
						x2+= -1*force*(distx/r);
					}
					x1 = x1*200;//scaling
					x2 = x2*200; //scaling
					if(disty > 0) { // repeat for Y...
						y1 += force*(disty/r);
						y2 += force*(disty/r);
					}
					else if (disty <0){ // repeat for Y...
						y1+=-1*force*(disty/r);
						y2+=-1*force*(disty/r);
					}
					y1 = y1*300; //scaling
					y2 = y2*300; //scaling
					System.out.println("new x n y: "+x1 + ","+y1);
					phold.YC +=(int) y2; // setting our next Y coordinate
					phold.XC += (int)x2; //setting our next X coordinate
					hold.data.XV += force/masses; // applying force to velocity in terms of x
					hold.data.YV+= force/masses;// applying force to velocity in terms of y
					hold = hold.next;
					System.out.println(pixeldist+ " "+ force+ " "+phold.XC +" " +phold.YC);
				}
		}
		}
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) { // "driver-esque" code for drawing bootleg universe
		super.paintComponent(g);
			this.draw(g);
		
	
	}
	public static void main(int h) { //main function to be called if LinkedList is datatype
		LinkedListCelest c = new LinkedListCelest();
		c = c.createList();
		JFrame jf = new JFrame();
		jf.setPreferredSize(new Dimension(768,768));
		jf.setTitle("tutorial");
		jf.setSize(768,768);
		jf.add(c);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		
	}
	
}
