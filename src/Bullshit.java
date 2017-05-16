import java.util.*;
import java.io.*;
public class Bullshit
{

	private static void swap(int arr[],int i,int j)
	{
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void main(String []args) throws IOException {
		Scanner s1 = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		int arr[] = new int[4];
		int start = s1.nextInt();
		arr[start] = 1;
		for(int i=1;i<=3;i++)swap(arr,s1.nextInt(),s1.nextInt());
		for(int i=1;i<=3;i++)if(arr[i] == 1){out.print(i);break;}
		s1.close();
		out.close();
	}    
}