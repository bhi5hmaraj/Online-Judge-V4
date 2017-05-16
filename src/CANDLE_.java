import java.util.*;
import java.io.*;
class CANDLE_
{
    public static void main(String args[])
    {
        MyScanner2 s1=new MyScanner2();  
        PrintWriter out=new PrintWriter(new BufferedOutputStream(System.out), true);  //Close the output stream after use
        StringBuffer sb=new StringBuffer();
        int t=s1.nextInt();
        for(int z=1;z<=t;z++)
        {
        	int arr[] =s1.nextIntArray(10);
        	int small = Integer.MAX_VALUE,num = -1;
        	for(int i=0;i<10;i++) //Find the least number with least frequency
        	{
        		if(arr[i] < small)
        		{
        			small = arr[i];
        			num = i;
        		}
        	}
        	boolean flag = false;
        	
        	
        	for(int i=1;i<10;i++)       //If a non zero digit has zero freq , then that's the answer
        	{
        		if(arr[i] == 0)
        		{
        			sb.append(i+"\n");
        			flag = true;
        			break;
        		}
        	}
        	if(flag)
        	{
        		continue;
        	}
        	
        	
        	
        	if(num != 0)                      //This gets executed when none of the non zero digit has zero freq
        	{
        		for(int i=1;i<=small+1;i++)          //This prints smallest i (i>0) freq[i] + 1 times 
        			sb.append(num);
        		sb.append("\n");
        		continue;
        	}
        	else
        	{
        		boolean f = false;            //This gets executed when small = freq[0] 
               
        		
        		
        		for(int i=1;i<10;i++)        //This is to find a digit with the same freq as of '0' if possible
                {
                    if(arr[i] == small)
                    {
                        for(int j=1;j<=small+1;j++)
                            sb.append(i);

                        sb.append("\n");
                        f = true;
                        break;
                    }
                }
        		if(f)
        			continue;
        		
        		
        		sb.append((long)Math.pow(10,arr[0]+1)+"\n");   //This is used to print 1 followed by freq[0]+1 zeroes
        	}
        }
        out.println(sb);
        out.close();
    }
    static class MyScanner2 {
        BufferedReader br;
        StringTokenizer st;
 
        public MyScanner2() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
 
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
         
        String nextLine(){
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
        int[] nextIntArray(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }
       
        long[] nextLongArray(int n) {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextLong();
            }
            return arr;
        }

}

}
        