import java.util.*;
import java.io.*;
public class Stresstester_SNTEMPLE
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
        dump.append(1 + "\n");
        dump.append(N + "\n");
        for(int i = 1; i <= N; i++)
            dump.append((1 + rand.nextInt(H_MAX)) + " ");
        
        byte temp[] = dump.toString().getBytes();
        try {
            file.write(temp);
            judge.write(temp);
            cand.write(temp);
            file.close();
            judge.close();
            cand.close();
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

    private static String getString(InputStream stream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();
        String read = null;
        try {
            while((read = br.readLine()) != null)
                sb.append(read);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    static int runs = 3;   //Number of times to run the stresstest bed
    static int N = 20000;
    static int H_MAX = 1000 * 1000;
    
    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String []args) throws IOException {
        while(runs-->0) {      
            Process judgeProcess = Runtime.getRuntime().exec("java -cp /home/bhishmaraj/workspace/Online_Judge_V4/bin SNTEMPLE_SLOW");
            Process candProcess  = Runtime.getRuntime().exec("java -cp /home/bhishmaraj/workspace/Online_Judge_V4/bin SNTEMPLE");
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
            else {
                System.out.print("PASS");
                System.out.print(" Judge " + getString(judgeProcess.getErrorStream()));
                System.out.println(" Candidate " + getString(candProcess.getErrorStream()));
            }
        }
        
        System.out.println("ALL TESTS PASSED !!");
    }    


    /************************ TEMPLATE ENDS HERE ************************/
}