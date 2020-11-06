
// main function
public class CelestialBodies extends LinkedListCelest {


	public static void main(String args[]) {
	
		int e = 1; // in order to call one of our other Main methods
		filehandler f = new filehandler();
		//f.path = args[0];  FOR ACCEPTING A FILE PATH WHEN PASSING DATAFILE FROM COMPILER
		f.openfile(f.path);
		f.GetAtt();
		String dtype0 = "ArrayList"; // to compare
		String dtype1 = "LinkedList"; // to compare 
		System.out.println(f.DataStruct); // the version of bootleg we can expect
		if(f.DataStruct.equals(dtype1)) { // linkedList universe
			LinkedListCelest h = new LinkedListCelest();
			h.main(e);
		}
		if(f.DataStruct.equals(dtype0)) { // ArrayList Universe
			ArrayListCelest h = new ArrayListCelest();
			h.main(e);
		}
		
	}
		

}
