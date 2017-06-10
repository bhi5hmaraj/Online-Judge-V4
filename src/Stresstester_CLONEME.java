import java.util.*;
import java.io.*;
public class Stresstester_CLONEME
{


    /************************ SOLUTION STARTS HERE ***********************/
    
    
    private static void generator(OutputStream file , OutputStream judge , OutputStream cand){   //Writes a single instance for a test case

        /*
         * This is the random test generator which generates a single instance 
         * use input object for writing into input.txt
         * 
         * 
         */
        Random rand = new Random();
        StringBuilder dump = new StringBuilder();
        dump.append(T + "\n");
        for(int tc = 1; tc <= T; tc++) {
            dump.append(N + " " + Q + "\n");
            for(int i = 1; i <= N; i++)
                dump.append((1 + rand.nextInt(MAX)) + " ");
            dump.append("\n");
            for(int i = 1; i <= Q; i++) {
                int len = 1 + rand.nextInt(N);
                int a = 1 + rand.nextInt(N - len + 1);
                int c = 1 + rand.nextInt(N - len + 1);
                dump.append(a + " " + (a + len - 1) + " " + c + " " + (c + len - 1) + "\n");
            }
        }
        
        byte temp[] = dump.toString().getBytes();
        try {
            file.write(temp);
            //judge.write(temp);
            //cand.write(temp);
            file.close();
            //judge.close();
            //cand.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int JUDGE(InputStream judge, InputStream cand) {
        BufferedReader correct = new BufferedReader(new InputStreamReader(judge));
        BufferedReader unchecked = new BufferedReader(new InputStreamReader(cand));
        String read = null;
        int line = 0;
        try {
            while ((read = correct.readLine()) != null) {
                line++;
                if (!read.equals(unchecked.readLine()))
                    return line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }



    static int runs = 1;   //Number of times to run the stresstest bed
    static int T = 3;
    static int N = (int) 1e5;
    static int Q = (int) 1e5;
    static int MAX = (int) 1e5;
    
    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String []args) throws IOException {
        /*while(runs-->0) {      
            Process judgeProcess = Runtime.getRuntime().exec("java -cp /home/bhishmaraj/workspace/Online_Judge_V4/bin CLONEME_SLOW");
            Process candProcess  = Runtime.getRuntime().exec("java -cp /home/bhishmaraj/workspace/Online_Judge_V4/bin CLONEME");
            generator(new FileOutputStream("input.txt"), judgeProcess.getOutputStream(), candProcess.getOutputStream());
            try {
                judgeProcess.waitFor();
                candProcess.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int ret = JUDGE(judgeProcess.getInputStream(), candProcess.getInputStream());
            if(ret > 0) {
                System.err.println("WA at line " + ret);
                System.exit(1);
            }
            else
                System.out.println("PASS");
        }
        
        System.out.println("ALL TESTS PASSED !!");
        */
        generator(new FileOutputStream("input.txt"), null , null);
        System.out.println("Generated");
    }    


    /************************ TEMPLATE ENDS HERE ************************/
}