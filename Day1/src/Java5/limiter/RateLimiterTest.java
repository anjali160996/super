package Java5.limiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class RateLimiterTest {
	public static void main(String[] args) {
		int cores = Runtime.getRuntime().availableProcessors();
		System.out.println("Logical cores : " + cores);
		RateLimiter r = new RateLimiter();
		ExecutorService fixedthreadPool = RateLimiter.createPool();
		//ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		//scheduler.scheduleAtFixedRate(() -> {r.resetWindow();}, 0, 60, TimeUnit.SECONDS);
		for (int user = 1; user <= 10; user++) {
			final String userId = "u" + user;
			for (int i = 0; i < 8; i++) {
				fixedthreadPool.submit(() -> {
					for (int j = 0; j < 10_000; j++) {
						r.recordRequest(userId);
					}
					;
				});
			}
		}
		fixedthreadPool.shutdown();
		try {
			//Q1.How to decide this awaitTermination time in realtime..based on what factor we will decide and how we will know 
			//how much time it will take complete the task?If it's not completed with in that time then what will happen for those worker threads?
			boolean finished=fixedthreadPool.awaitTermination(60, TimeUnit.SECONDS); 
			
			if(!finished) {
				System.out.print(finished);
			}
		} catch (InterruptedException e) {
			 Thread.currentThread().interrupt();
		}
		long count = r.getCount("u1");
		System.out.println("Count:" + count);
		System.out.println("Expected:80000");
		System.out.println("Correct:" + (count == 80000));

		long count1 = r.getCount("u2");
		System.out.println("Count:" + count1);
		System.out.println("Expected:80000");
		System.out.println("Correct:" + (count1 == 80000));
		r.resetWindow();
		long afterReset = r.getCount("u1");
		System.out.println("After reset: " + afterReset);
		System.out.println("Expected   : 0");
		System.out.println("Correct    : " + (afterReset == 0));

	}
}
