package day7.q001;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import day7.q001.Records.IngestJob;
import day7.q001.Records.IngestResult;

public class Virtual {

	static IngestResult process(IngestJob job) throws InterruptedException {
		Thread CurrenThread = Thread.currentThread();
		if (job.delayMs() > 0) {
			System.out.println("START Job " + job.id() + " | " + CurrenThread.getName() + " | virtual="
					+ CurrenThread.isVirtual());
			Thread.sleep(job.delayMs());
			System.out.println("End Job " + job.id() + " | " + CurrenThread.getName());
			return new IngestResult(job.id(), true);
		}
		return new IngestResult(job.id(), false);
	}

	List<IngestResult> processAll(List<IngestJob> jobs) throws InterruptedException, ExecutionException {
		try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			List<Future<IngestResult>> futures = new ArrayList<>();
			for (IngestJob job : jobs) {
				Future<IngestResult> future = executor.submit(() -> process(job));
				futures.add(future);
			}
			List<IngestResult> results = new ArrayList<>();
			for (Future<IngestResult> future : futures) {
				results.add(future.get());
			}
			return results;
		}
	}

	public static void main(String args[]) throws InterruptedException, ExecutionException {

		Virtual v = new Virtual();
		List<IngestJob> jobs = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			int delay = 50 + (i % 51);
			jobs.add(new IngestJob(i, delay));
		}
		long start = System.currentTimeMillis();
		List<IngestResult> results = v.processAll(jobs);

		long successful = results.stream().filter(IngestResult::ok).count();
		long elapsed = System.currentTimeMillis() - start;
		System.out.println("Successful results: " + successful);
		System.out.println("Elapsed time: " + elapsed + " ms");

	}
}
