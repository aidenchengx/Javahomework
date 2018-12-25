package TreeStruct;
import javax.sound.midi.MidiDevice.Info;
import java.io.BufferedReader;    
import java.io.IOException;    
import java.io.InputStreamReader;    
import java.util.Scanner;    
public class main {
	public static void main(String[] args) {
   		System.out.println("NOW TIME");
   		Tree RBT=new Tree(new Node(41,42,100,"Chinese"));
   		Node N=new Node(38,39,101,"Math");
   		RBT.AddNode(N);RBT.printTree();
   		Node Q=new Node(31,32,102,"Physics");
   		Node B=new Node(12,13,103,"Mechanic");
   		RBT.AddNode(Q);
   		RBT.AddNode(B);
   		Node F=new Node(19,20,104,"CS");
   		RBT.AddNode(F);
   		Node F1=new Node(50,51,105,"EE");
   		Node F2=new Node(55,56,105,"IoT");
   		RBT.AddNode(F1);RBT.AddNode(F2);
   		RBT.adjustTreesmax(RBT.getRoot());	
   		RBT.printTree();
   		while(true) {
   		System.out.println("Choose 1 option to operate");
   		System.out.println("1:Add a course");
   		System.out.println("2:Delete a course");
   		System.out.println("3:Search");
   		Scanner sc = new Scanner(System.in);
   		int age = sc.nextInt();
   		if(age==1) {System.out.println("Typein a node in the format 'tt tt id'");
   		int age11=sc.nextInt();
   		int age12=sc.nextInt();
   		int age13=sc.nextInt();
   		System.out.println("Typein the course name");
   		String str=sc.next();
   		Node N4=new Node(age11,age12,age13,str);
   		RBT.AddNode(N4);	RBT.adjustTreesmax(RBT.getRoot());	
   		RBT.printTree();
   		continue;
   		}
   		if (age==2) {System.out.println("Typein an id of a course to delete");
   		int age2 = sc.nextInt();
   		Node K;	K=RBT.findNode(age2);	if(K==null) {System.out.println("this Node DO NOT EXIST!");}	else {RBT.DeleteNode(K);}
   		RBT.adjustTreesmax(RBT.getRoot());	
   		RBT.printTree();continue;}
   		if(age==3) {
   			System.out.println("Typein to search and delete");
   	   		int age21=sc.nextInt();
   	   		int age22=sc.nextInt();
   	   		String str=RBT.courselist(age21, age22);
   	   		System.out.println(str);
   	   	RBT.printTree();
   	   		
   	   		
   			continue;}
   		if(age==4) {return;}
   		}  	
	}
}
