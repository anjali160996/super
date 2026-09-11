package Java5.Semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample {
	private static final int TASK_COUNT = 20;

	private static final int MAX_PERMITS = 3;

	public static void main(String[] args) throws InterruptedException {
		Downstream service = new Downstream();
		ExecutorService executor = Executors.newFixedThreadPool(TASK_COUNT);
		List<Future<Result>> futures = new ArrayList<>();
		for (int i = 1; i <= TASK_COUNT; i++) {
			final int taskId = i;
			futures.add(executor.submit(() -> service.invokeProtected(() -> {
				Thread.sleep(1000);
				if (taskId % 7 == 0) {
					throw new RuntimeException("Failure in task " + taskId);
				}
				return new Result("Task " + taskId + " completed");
			})));
		}
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.MINUTES);
		for (Future<Result> future : futures) {
			try {
				System.out.println(future.get());
			} catch (ExecutionException e) {
				System.out.println("Task failed: " + e.getCause().getMessage());
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		int peak = service.getPeak();
		int permits = service.getAvailablePermits();


	}
}