public class FibonacciIterative {

	public static void Fibonacci(int f) {
		int f1 = 0, f2 = 1;
        int maxF = 0;
        
        while (maxF < f) {
            System.out.print(f1 + " ");
            
            int f3 = f2 + f1;
            f1 = f2;
            f2 = f3;
            maxF = maxF + 1;
            }
        }
	
	public static void main(String args[]) {
		int f = 20;
        long startTime = System.nanoTime();
        
        Fibonacci(f);

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        
        System.out.println("\n" + "The execution time was: " + executionTime + " nanoseconds");
        }
	}