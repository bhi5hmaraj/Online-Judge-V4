import java.util.*;

public class vignere_break {
    
    public static String CrackVig(int no, String s){
        int count[][] = new int[no][26];
        StringBuilder temp = new StringBuilder();
        for(int i=0;i<s.length();i++){
            count[i%no][s.charAt(i)-'A']++;
        }
        for(int i=0;i<no;i++){
            int max = 0;
            int loc = -1;
            for(int j=0;j<26;j++){
                if(count[i][j]>max){
                    max = count[i][j];
                    loc = j;
                }
            }
            int pos = loc - 4;
            System.out.println(pos);
            if(pos>=0){
                temp.append((char)(pos + 'A'));
            }
            else{
                temp.append((char)(26 + pos + 'A'));
            }
        }
        return temp.toString();
        
    }
    public static String Decrypt(String key, String cipher){
        StringBuilder temp2 = new StringBuilder();
        for(int i=0;i<cipher.length();i++){
            int pos= (cipher.charAt(i) - key.charAt(i%key.length()));
            //System.out.print(pos+" ");
            char one;
            if(pos>0){
                one=(char)((pos)%26 + 'A');
            }
            else{
                one = (char) ((26+pos)%26 + 'A');
            }
            temp2.append(one);
        }
        String decrypt = temp2.toString();
        return decrypt;
    }

    public static void main(String[] args) {
        
        String k  = "ANIRUDH";
        
        Scanner s1 = new Scanner(System.in);
        String s= s1.nextLine();
        StringBuilder temp = new StringBuilder();
        int n = s.length();
        for(int i=0;i<n;i++){
            if(s.charAt(i)>=65 && s.charAt(i)<=90){
                temp.append(s.charAt(i));
            }
        }
        String s2 = temp.toString();
        
        StringBuilder temp1 = new StringBuilder();
        for(int i=0;i<s2.length();i++){
            char one = (char) ((s2.charAt(i)-'A' + k.charAt(i%k.length())-'A')%26 + 'A');
            temp1.append(one);
        }
        String cipher = temp1.toString();
        
        int limit = 7;  // Size of the key. As the key gets bigger, it gets closer to the OTP.
        
        for(int i=7;i<=limit;i++){
            String crack = CrackVig(i,cipher);
            String original = Decrypt(crack,cipher);
            System.out.println("Size of Key = " + i);
            System.out.println("Found key is " + crack);
            System.out.println("Output:     "+ original);
        }
    }

}
