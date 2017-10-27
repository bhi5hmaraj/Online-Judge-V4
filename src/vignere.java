import java.util.*;

//Test
//THEWORLDISATERRIBLEPLACEFORHUMANS
//CAPS
public class vignere {
    public static void main(String args[]){
        Scanner s1 = new Scanner(System.in);
        
        String n = s1.nextLine();
        String k = s1.nextLine();
        
        StringBuilder temp = new StringBuilder();
        for(int i=0;i<n.length();i++){
            char one = (char) ((n.charAt(i)-'A' + k.charAt(i%k.length())-'A')%26 + 'A');
            temp.append(one);
        }
        String cipher = temp.toString();
        System.out.println(n);
        System.out.println(cipher);
        StringBuilder temp2 = new StringBuilder();
        for(int i=0;i<n.length();i++){
            int pos= (cipher.charAt(i) - k.charAt(i%k.length()));
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
        System.out.println(decrypt);
    }
    
}
