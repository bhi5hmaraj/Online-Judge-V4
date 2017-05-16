import java.util.*;
import java.io.*;
public class BearandPrime
{

    public static void main(String[] args) {
	Scanner s1 = new Scanner(System.in);
	int primes[] = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47};
	HashSet<Integer> set = new HashSet<>();
	for(int  p :primes){	    
	    System.out.println(p);
	    System.out.flush();
	    if(s1.next().equals("yes")){
		set.add(p);
	    }
	}

	if(set.size() == 0){
	    System.out.println("prime");
	    System.out.flush();
	}
	else if(set.size() > 1){
	    System.out.println("composite");
	    System.out.flush();
	}
	else{
	    set.clear();
	    int primesSq[] = {4,9,25,49};
	    for(int p:primesSq){
		System.out.println(p);
		System.out.flush();
		if(s1.next().equals("yes")){
		    System.out.println("composite");
		    System.out.flush();
		    return;
		}
	    }
	    System.out.println("prime");
	    System.out.flush();
	}


    }

}