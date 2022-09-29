import java.util.List;
import java.io.*;
import java.util.*;

public class Driver { 
 public static void main(String [] args) { 
  Polynomial p = new Polynomial(); 
  System.out.println(p.evaluate(3)); 
  double [] co1 = {3,4,5}; 
  int [] ex1= {2,3,0};
  double [] co2= {1,-2,10,3};
  int [] ex2= {1,2,3,5};
  
  Polynomial p1 = new Polynomial(co1,ex1); 
  //double [] c2 = {0,-2,0,0,-9}; 
  Polynomial p2 = new Polynomial(co2,ex2); 
  Polynomial s = p1.add(p2); 
  System.out.println(Arrays.toString(s.co));
  System.out.println(Arrays.toString(s.ex));
  
  System.out.println("s(1.3) = " + s.evaluate(1.3)); 
  if(s.hasRoot(1)) 
   System.out.println("1 is a root of s"); 
  else 
   System.out.println("1 is not a root of s"); 
 
  
  String f_name="C:\\Users\\itsec\\Desktop\\大二上\\CSCB07\\lab2\\b07lab1\\read_content.txt";
  
  File f1 = new File(f_name);
  Polynomial p3 = new Polynomial(f1);
  System.out.println(Arrays.toString(p3.co));
  System.out.println(Arrays.toString(p3.ex));
  
  String w_name="C:\\Users\\itsec\\Desktop\\大二上\\CSCB07\\lab2\\b07lab1\\write_content.txt";
  s.saveToFile(w_name);
  
  Polynomial m = p1.multiply(p2);
  System.out.println(Arrays.toString(m.co));
  System.out.println(Arrays.toString(m.ex));
  
  
  
  
 }
} 