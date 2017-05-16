import java.util.*;
import java.awt.geom.Point2D;
import java.io.*;
import static java.lang.Math.*;
public class CouplingPassions
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static double distance_between(Point2D.Double point1,Point2D.Double point2) {
	    double EARTH_RADIUS = 6371;//in km
	    double point1_lat_in_radians  = toRadians( point1.getX() );
	    double point2_lat_in_radians  = toRadians( point2.getX() );
	    double point1_long_in_radians = toRadians( point1.getY() );
	    double point2_long_in_radians = toRadians( point2.getY() );

	    return acos( sin( point1_lat_in_radians ) * sin( point2_lat_in_radians ) +
	                 cos( point1_lat_in_radians ) * cos( point2_lat_in_radians ) *
	                 cos( point2_long_in_radians  - point1_long_in_radians)    ) * EARTH_RADIUS;
	}
	private static void solve(FastScanner s1, PrintWriter out){
		
		int G = s1.nextInt();
		HashMap<String,Point2D.Double> location = new HashMap<>();
		HashMap<String,ArrayList<String>> cityForAct = new HashMap<>();
		HashMap<String,Integer> freqOfAct = new HashMap<>();
		HashMap<String,Integer> freqForCity = new HashMap<>();
		for(int i=0;i<G;i++){
			int m = s1.nextInt();
			for(int j=0;j<m;j++){
				String act = s1.next();
				freqOfAct.put(act, freqOfAct.getOrDefault(act, 0) + 1);
			}
		}

		int destCnt = s1.nextInt();
		for(int i=0;i<destCnt;i++){
			String city = s1.next();
			location.put(city, new Point2D.Double(s1.nextDouble(), s1.nextDouble()));
			int m = s1.nextInt();
			for(int j=0;j<m;j++){
				String act = s1.next();
				ArrayList<String> cities = cityForAct.get(act);
				if(cities == null)
					cities = new ArrayList<>();
				cities.add(city);
				cityForAct.put(act, cities);
			}
		}
	
		for(Map.Entry<String, Integer> e:freqOfAct.entrySet()){
			ArrayList<String> cities = cityForAct.get(e.getKey());
			if(cities != null){
				for(String city:cities){
					freqForCity.put(city, freqForCity.getOrDefault(city, 0) + e.getValue());
				}
			}
		}
		
		int max = 0;
		String maxCity = null;
		for(Map.Entry<String, Integer> e:freqForCity.entrySet()){
			if(e.getValue() > max){
				maxCity = e.getKey();
				max = e.getValue();
			}
		}
		
		int maxCnt = 0;
		for(Map.Entry<String, Integer> e:freqForCity.entrySet())
			maxCnt += e.getValue().intValue() == max ? 1 : 0;
		
		
		if(maxCnt == 1){  //Only one city with max
			int secondMax = 0;
			for(Map.Entry<String, Integer> e:freqForCity.entrySet()){
				if(e.getValue().intValue() != max){
					secondMax = Math.max(secondMax,e.getValue().intValue());
				}
			}
			Point2D.Double p1 = location.get(maxCity);
			Double minDist = Double.MAX_VALUE;
			String secondCity = null;
			for(Map.Entry<String, Integer> e:freqForCity.entrySet()){   //Finding the second largest and nearest city
				double dist = (distance_between(p1, location.get(e.getKey())));
				if(e.getValue() == secondMax && dist < minDist){
					minDist = dist;
					secondCity = e.getKey();
				}
			}
			if(maxCity.compareTo(secondCity) < 0)
				out.println(maxCity + " " + secondCity);
			else
				out.println(secondCity + " " + maxCity);
		}
		else{
			String arr[] = new String[maxCnt];
			int ptr = 0;
			for(Map.Entry<String, Integer> e:freqForCity.entrySet()){ 
				if(e.getValue() == max)
					arr[ptr++] = e.getKey();
			}
			String str1 = null , str2 = null;
			double minDist = Double.MAX_VALUE;
			for(int i=0;i<maxCnt;i++){
				for(int j=i+1;j<maxCnt;j++){
					double dist = distance_between(location.get(arr[i]), location.get(arr[j]));
					if(dist < minDist){
						minDist = dist;
						str1 = arr[i];
						str2 = arr[j];
					}
				}
			}
			if(str1.compareTo(str2) < 0)
				out.println(str1 + " " + str2);
			else
				out.println(str2 + " " + str1);
		}
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(System.in);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		solve(in, out);
		in.close();
		out.close();
	}    
	
	static class FastScanner{
		BufferedReader reader;
		StringTokenizer st;
		FastScanner(InputStream stream){reader=new BufferedReader(new InputStreamReader(stream));st=null;}	
		String next()
		{while(st == null || !st.hasMoreTokens()){try{String line = reader.readLine();if(line == null){return null;}		    
		st = new StringTokenizer(line);}catch (Exception e){throw new RuntimeException();}}return st.nextToken();}
		String nextLine()  {String s=null;try{s=reader.readLine();}catch(IOException e){e.printStackTrace();}return s;}	    	  	
		int    nextInt()   {return Integer.parseInt(next());}
		long   nextLong()  {return Long.parseLong(next());}		
		double nextDouble(){return Double.parseDouble(next());}
		char   nextChar()  {return next().charAt(0);}
		int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
		long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
		int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
		long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}