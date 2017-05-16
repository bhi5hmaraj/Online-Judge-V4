import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Experiment {

	public static void main(String[] args) throws Exception{
		
		Scanner s1 = new Scanner(System.in);
		PrintWriter out = new PrintWriter("out.txt");
		double r = s1.nextDouble();
		double xo = s1.nextDouble();
		HashMap<Double , Integer> map = new HashMap<>();
		map.put(xo, 1);
		for(int i=2;;i++) {
			double xn = (r * xo) * (1.0 - xo);
			out.println("i " + i + " value " + xn);
			if(map.containsKey(xn)) {
				out.println("Cycle found , period = " + (i - map.get(xn)));
				s1.close();
				out.close();
				return;
			}
			map.put(xn, i);
			xo = xn;
		}
	}
	
}
