package day8.q001;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PlatformThreads {
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
		try (var executor = Executors.newFixedThreadPool(32)) {
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

		PlatformThreads v = new PlatformThreads();
		List<ProbeTarget> jobs = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
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
