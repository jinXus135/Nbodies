
public class celestialBody {
	String name; 
	double mass;
	int  pixels;
	int XC, YC;// x and y coordinates
	double XV, YV; // x and y velocity
	
	celestialBody(){ // constructor function
		name = "NA";
		mass = 0;	
		XC = 0;
		YC = 0;
		XV = 0;
		YV = 0;
		pixels = 0;
	}
	
	//getters and setters for name;
	public String GetName() {return name;}
	public void setName(String n) {name = n;}
	// getters and setters for mass
	double getMass() {return mass;}
	void setMass(double m) {mass = m;}
	// getters and setters for x and y coordinates;
	void setXC(int x) {XC =x;}
	void setYC(int y) {YC = y;}
	int getXC() {return XC;}
	int getYC() {return YC;}
	// getters and setters for velocity
	void setXV(double x) {XV = x;}
	void setYV(double y) {YV = y;}
	double getXV () {return XV;}
	double getYV() {return YV;}
	//pixel getter and setter
	void setPixels(int p) {pixels = p;}
	int getPixels() {return pixels;}

	
}
