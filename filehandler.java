import java.util.*;
import java.io.*;
public class filehandler {
	BufferedReader n;	
	String DataStruct;
	float distance;
	List<String> g = new ArrayList<>();
	String path = "C:\\Users\\antho\\eclipse-workspace\\CelestialBodies\\src\\BodiesData.csv";
	public void openfile(String path)  {
		try { 
		n = new BufferedReader(new FileReader(path)); //opening file takes path file at compile time
		String l;
		while((l = n.readLine())!= null) { //reading file line by line
			g.add(l); // adding all elements of file to Arraylist of strings
				
				
			}
		n.close();
		}
		catch(Exception e) {
		System.out.println("file could not be found");
		}
	
	}
	
	public void GetAtt()  { //getting first and second lines of file for... 
		DataStruct = g.get(0); // our datatype...
		distance = Float.parseFloat(g.get(1)); // and our pixel scale.
		
	}
	
}
