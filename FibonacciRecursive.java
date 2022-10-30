public class FibonacciRecursive {
	
	public static int Fibonacci(int f) {
		
		if (f <= 1)
			return f;
		
	        return Fibonacci(f - 1)
	        		+ Fibonacci(f - 2);
	        }
	
	    public static void main(String args[]) {
	        int f = 20;
	        long startTime = System.nanoTime();
	        
	        for (int i = 0; i < f; i++) {
	        	System.out.print(Fibonacci(i) + " ");
	        }
	        
	        long endTime = System.nanoTime();
	        long executionTime = endTime - startTime;
	        
	        System.out.println("\n" + "The execution time was: " + executionTime + " nanoseconds");
	        }
	    }