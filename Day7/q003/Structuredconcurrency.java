package day7.q003;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

public class Structuredconcurrency {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		try(var scope = new StructuredTaskScope.ShutdownOnFailure()){
			var creditScoreFuture =scope.fork(()->{ 
				System.out.print("Credit check" + 10/0 + Thread.currentThread());
				
				return "credit check running...";});
			var eVerificationFuture =scope.fork(()->{ 
				System.out.print("Employment check   ");
				return "Employment verification Running...";});
			scope.join();
			scope.throwIfFailed();
			System.out.print("all tasks completed");
		}
		
	}

}
