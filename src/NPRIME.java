import java.util.*;
import java.io.*;
class NPRIME
{    
    public static void main(String []args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	PrintWriter out = 
		new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
	int MAX_PRIME = 1299709;
	int MAX  = 100000;
	boolean num[] = new boolean[MAX_PRIME+1];
	int arr[] = new int[MAX+1];
	int ptr = 1;
	for(int i=2;i*i<=MAX_PRIME;i++)
	{
	    if(!num[i])	
	    {
		arr[ptr++] = i;
		for(int j=i*i;j<num.length;j+=i)
		    num[j] = true;	
	    }
	}
	String in = null;
	while((in = br.readLine()) != null)
	   System.out.println(arr[Integer.parseInt(in)]);
    }    
}