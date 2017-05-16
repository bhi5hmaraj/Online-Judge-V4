import java.util.*;
public class bounds {
/*
	private static int lower_bound(int arr[],int key)
	{
		
		int lo = 0;
		int hi = arr.length-1;
		int pos = -1;
		boolean flag = false;
		int mid=0;
		while(lo <= hi)
		{
			 mid = (lo + hi)/2;
			if(key <= arr[mid])
			{
				if(key == arr[mid])
				{
					flag = true;
					pos = mid;
				}
				else
					pos = (mid - 1 >= pos)?(mid-1):pos;
				
				hi = mid -1;				
			}
			else
			{
				pos = mid + 1;
				lo = mid + 1;
			}
		}	
		if(flag)
			return pos;
		else
			return -(pos + 1);
	
	}

*/
	public static void main(String[] args) {
		Scanner s1= new Scanner(System.in);
		int len = s1.nextInt();
		float arr[] = new float[len];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = s1.nextInt();
		}
		Arrays.sort(arr);
		int t = s1.nextInt();
		System.out.println(Arrays.toString(arr));
		while(t-->0)
		{
		int key = s1.nextInt();

		System.out.println("upper bound "+(-(Arrays.binarySearch(arr, key + 0.5f) + 1)));
		}
		s1.close();
	}
}
