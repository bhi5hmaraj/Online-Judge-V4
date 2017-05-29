import java.util.*;
import java.io.*;
public class Stresstester_CONSENK
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
        for(int i = 1; i <= T; i++) {
        int N = N_MAX;
        int L = 1 + rand.nextInt(L_MAX);
        int A = 1 + rand.nextInt(B_MAX - (N * L) - 1);
        int B = A + (N * L) + rand.nextInt(B_MAX - (A + (N * L)));
        dump.append(N + " " + L + " " + A + " " + B + "\n");

        while(N-->0)
            dump.append((1 + rand.nextInt(S_MAX)) + " ");
        
        dump.append("\n");
        if(A > B || N * L > B - A)
            throw new RuntimeException("wrong input " + dump);
        }
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
        String A = "";
        String B = "";
        int line = 0;
        try {
            while ((read = correct.readLine()) != null) {
                line++;
                String read2 = unchecked.readLine();
                if (!read.equals(read2)) {
                    return line;
                }
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

    static int runs = 5;   //Number of times to run the stresstest bed
    static int T = 10;
    static int N_MAX = 5;
    static int L_MAX = 10;
    static int S_MAX = 100;
    static int B_MAX = 200;
    
    /************************ TEMPLATE STARTS HERE *********************/
    
    static final String directory = "/home/bhishmaraj/workspace/Online_Judge_V4/bin";
    static final String judgeClass = "CONSESNK_SLOW";
    static final String candClass = "CONSESNK";
    public static void main(String []args) throws IOException {
        while(runs-->0) {      
            Process judgeProcess = Runtime.getRuntime().exec("java -cp " + directory + " " + judgeClass);
            Process candProcess  = Runtime.getRuntime().exec("java -cp " + directory + " " + candClass);
            
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
                System.out.println("PASS " + runs);
            }
        }
        
        System.out.println("ALL TESTS PASSED !!");
    }    


}