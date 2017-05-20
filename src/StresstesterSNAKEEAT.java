import java.util.*;
import java.io.*;
public class StresstesterSNAKEEAT
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
        dump.append(1);
        dump.append(N + " " + Q);
        for(int i = 1; i <= N; i++)
            dump.append((1 + rand.nextInt(L_MAX)) + " ");
        dump.append("\n");
        for(int i = 1; i <= Q; i++)
            dump.append((1 + rand.nextInt(K_MAX)) + "\n");
        
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



    static int runs = 5;   //Number of times to run the stresstest bed
    static int N = 5;
    static int Q = 100;
    static int L_MAX = 100;
    static int K_MAX = 100;

    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String []args) throws IOException {


        String inputFile = "input.txt";

        while(runs-->0) {      
            
            
            
        }
    }    


    /************************ TEMPLATE ENDS HERE ************************/
}