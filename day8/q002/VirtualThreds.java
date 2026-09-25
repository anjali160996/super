package day8.q002;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VirtualThreds {
	

	static ProbeResult process(ProbeTarget target) throws InterruptedException {
	
			Thread CurrenThread = Thread.currentThread();
			System.out.println(
					"START Job " + target.id() + " | " + CurrenThread.getName() + " | virtual=" + CurrenThread.isVirtual());
			long start = System.currentTimeMillis();

			Thread.sleep(target.delayMs());

			long elapsed = System.currentTimeMillis() - start;
			System.out.println("End Job " + target.id() + " | " + CurrenThread.getName());

			return new ProbeResult(target.id(), true, elapsed);
		
	}

	List<ProbeResult> processAll(List<ProbeTarget> targets) throws InterruptedException, ExecutionException {
		var factory = Thread.ofVirtual().name("vt-", 0).factory();
		try (var executor = Executors.newThreadPerTaskExecutor(factory)) {
			List<Future<ProbeResult>> futures = new ArrayList<>();
			for (ProbeTarget target : targets) {
				Future<ProbeResult> future = executor.submit(() -> process(target));
				futures.add(future);
			}
			List<ProbeResult> results = new ArrayList<>();
			for (Future<ProbeResult> future : futures) {
				results.add(future.get());
			}
			return results;
		}
	}

	public static void main(String args[]) throws InterruptedException, ExecutionException {

		VirtualThreds v = new VirtualThreds();
		List<ProbeTarget> jobs = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			int delay = 50 + (i % 51);
			jobs.add(new ProbeTarget(i, delay));
		}
		long start = System.currentTimeMillis();
		List<ProbeResult> results = v.processAll(jobs);

		long successful = results.stream().filter(ProbeResult::ok).count();
		long elapsed = System.currentTimeMillis() - start;
		System.out.println("Successful results: " + successful);
		System.out.println("Elapsed time: " + elapsed + " ms");

	}

}
