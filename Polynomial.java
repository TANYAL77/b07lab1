public class Polynomial {
	double[] co;

	public Polynomial(){
		co=new double[] {0};
	}
	
	public Polynomial(double[] arr) {
		co=arr;
	}
	
	Polynomial add(Polynomial pol){
		if(pol.co.length > co.length ) {
			for (int i=0;i<co.length;i++) {
				pol.co[i]=pol.co[i]+co[i];
			}
			return pol;
					
		}
		else {
			for (int i=0;i<pol.co.length;i++) {
				co[i]=pol.co[i]+co[i];
			}
			return this;
			
		}

	}
	
	double evaluate(double x) {
		double result=0;
		
		for (int i=0;i<co.length ;i++) {
			result+=co[i]*Math.pow(x,i);
		}
		
		return result;
	}

	boolean hasRoot(double root) {
		return (evaluate(root)==0);
	}
	
	
}
 