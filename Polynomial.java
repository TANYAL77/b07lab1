import java.util.List;
import java.io.*;
import java.util.*;

public class Polynomial {
	double[] co;
	int[] ex;

	public Polynomial(){
		co=new double[] {0};
		ex=new int[] {0};
	}
	
	public Polynomial(double[] co_arr, int[] ex_arr) {
		co=co_arr;
		ex=ex_arr;
		
	}
	
	public Polynomial(File f) {
		//intinialize the polynomial based on the contents of the file
		try {
		BufferedReader ff = new BufferedReader(new FileReader(f));
		String content = ff.readLine();
		
		String[] split_c = content.split("\\+|(?=-)");
		
		co = new double[split_c.length];
		ex = new int[split_c.length];
	
		int fill_index=0;
		
		for (String x:split_c) {
			if(x.contains("x")) {
				String[] ss=x.split("x");
				co[fill_index]=Double.parseDouble(ss[0]);
				ex[fill_index]=Integer.parseInt(ss[1]);
				fill_index++;
			}
			else {
				co[fill_index]=Double.parseDouble(x);
				ex[fill_index]=0;
				fill_index++;
				
			}
		}
			ff.close();
		} catch (IOException e) {
        }
			
	}
	
	
	Polynomial add(Polynomial pol){
		
		List<Integer> exlist = new ArrayList<Integer>();
		for(int x:pol.ex) {
			if (!exlist.contains(x)) {
				exlist.add(x);
			}
		}
		for(int x:ex) {
			if (!exlist.contains(x)) {
				exlist.add(x);
			}
		}
		
		double[] newco=new double[exlist.size()];
		Arrays.fill(newco, 0);
		
		for(int i=0;i<co.length;i++) {
			newco[exlist.indexOf(ex[i])]+=co[i];
		}
		
		for(int i=0;i<pol.co.length;i++) {
			newco[exlist.indexOf(pol.ex[i])]+=pol.co[i];
		}
		
		int[] newex=new int[exlist.size()];
		for(int i=0;i<exlist.size();i++) {
			newex[i]=exlist.get(i);
			
		}
		//Integer[] newex = exlist.toArray(new Integer[exlist.size()]);
		
		Polynomial new_pol= new Polynomial(newco,newex);
		return new_pol;

	}
	
	double evaluate(double x) {
		double result=0;
		
		for (int i=0;i<co.length ;i++) {
			result+=co[i]*Math.pow(x,ex[i]);
		}
		
		return result;
	}

	boolean hasRoot(double root) {
		return (evaluate(root)==0);
	}
	
	int Count_type(int[] arr) {
		List<Integer> element = new ArrayList<Integer>();
		
		for (int x:arr) {
			if (!element.contains(x)) {
				element.add(x);
			}
		}
		
		return element.size();
	}
	
	boolean array_contain(int[] arr,int x) {
		for (int n:arr) {
			if (x==n)    return true;
		}
		return false;
	}
	
	int search(int[] arr,int x) {
		for (int i=0;i<arr.length ;i++) {
			if (arr[i]==x)	return i;
		}
		return -1;
	}
	
	Polynomial multiply(Polynomial pol) {
		
		double[] newco= new double[co.length * pol.co.length];
		int[] newex= new int[newco.length];
		
		int new_index=0;
		
		for(int i=0;i<co.length;i++) {
			for(int j=0;j<pol.co.length;j++) {
				
				newco[new_index]=co[i]*pol.co[j];
				newex[new_index]=ex[i]+pol.ex[j];
				new_index++;	
			}
		}
		//System.out.println("newco:"+Arrays.toString(newco));
		//System.out.println("newex:"+Arrays.toString(newex));
		
		int[] result_ex= new int[Count_type(newex)];
		double[] result_co= new double[result_ex.length];
		//Arrays.fill(result_co, 0);
		
		int fill_index=0;
		for(int i=0;i<newco.length;i++) {
			if (!array_contain(result_ex,newex[i])) {
				result_ex[fill_index]=newex[i];
				result_co[fill_index]=newco[i];
				fill_index++;
			}
			else {
				result_co[search(result_ex, newex[i])]+=newco[i];
			}
		}
		
		Polynomial new_pol= new Polynomial(result_co,result_ex);
		return new_pol;
		
	}
	
	
	public void saveToFile(String f_name) {
		
		//create string
		String content="";
		
		for(int i=0;i<co.length;i++) {
			if(ex[i]!=0) {
				if(co[i]<0) {
					content=content+co[i]+'x'+ex[i];
				}
				else {
					if(content=="")
						content=content+co[i]+'x'+ex[i];
					else
						content=content+'+'+co[i]+'x'+ex[i];
				}
			}
			else {
				if(co[i]<0) {
					content=content+co[i];
				}
				else {
					if(content=="")
						content=content+co[i];
					else
						content=content+'+'+co[i];
				}
			}
		}
		
		
		//write in file
		try {
		File f = new File(f_name);
		if (!f.exists()) {
			f.createNewFile();
		   }
		
		FileWriter fw = new FileWriter(f.getAbsoluteFile());
		BufferedWriter file = new BufferedWriter(fw);
		file.write(content);
		file.close();
		}catch (IOException e) {
        }
	}
	
	
}
 