package day8.q003;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class VirtualThreadsAtScale {

	static ProbeResult probe(ProbeTarget target) throws InterruptedException {
		Thread.sleep(target.delayMs());
		return new ProbeResult(target.id(), true, target.delayMs());
	}

	int runVirtual(int count) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(count);
		AtomicInteger successCount = new AtomicInteger(0);

		for (int i = 0; i < count; i++) {
			ProbeTarget target = new ProbeTarget(i, 10);
			Thread.ofVirtual().name("vt-", i).start(() -> {
				try {
					ProbeResult result = probe(target);
					if (result.ok())
						successCount.incrementAndGet();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await();
		return successCount.get();
	}

	int runPlatformPool(int count) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(count);
		AtomicInteger successCount = new AtomicInteger(0);

		try (ExecutorService pool = Executors.newFixedThreadPool(32)) {
			for (int i = 0; i < count; i++) {
				ProbeTarget target = new ProbeTarget(i, 10);
				pool.submit(() -> {
					try {
						ProbeResult result = probe(target);
						if (result.ok())
							successCount.incrementAndGet();
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					} finally {
						latch.countDown();
					}
				});
			}
			latch.await();
		}

		return successCount.get();
	}

	int runPlatformDirect(int count) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(count);
		AtomicInteger successCount = new AtomicInteger(0);

		for (int i = 0; i < count; i++) {
			ProbeTarget target = new ProbeTarget(i, 10);
			Thread.ofPlatform().name("pt-", i).start(() -> {
				try {
					ProbeResult result = probe(target);
					if (result.ok())
						successCount.incrementAndGet();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await();
		return successCount.get();
	}

	public static void main(String[] args) throws InterruptedException {
		VirtualThreadsAtScale v = new VirtualThreadsAtScale();
		int count = 100_000;

		System.out.println("=== Approach 1: Virtual Threads (" + count + " tasks) ===");
		long s1 = System.currentTimeMillis();
		int r1 = v.runVirtual(count);
		long e1 = System.currentTimeMillis() - s1;
		System.out.println("Success : " + r1);
		System.out.println("Elapsed : " + e1 + " ms");
		System.out.println();

		System.out.println("=== Approach 2: Platform Thread Pool (32) — " + count + " tasks ===");
		long s2 = System.currentTimeMillis();
		int r2 = v.runPlatformPool(count);
		long e2 = System.currentTimeMillis() - s2;
		System.out.println("Success : " + r2);
		System.out.println("Elapsed : " + e2 + " ms");
		System.out.println();

		System.out.println("=== Approach 3: Platform Threads Direct (" + count + " tasks ===");
		long s3 = System.currentTimeMillis();
		int r3 = v.runPlatformDirect(count);
		long e3 = System.currentTimeMillis() - s3;
		System.out.println("Success : " + r3);
		System.out.println("Elapsed : " + e3 + " ms");
		System.out.println();
		System.out.println("=== SUMMARY ===");
		System.out.println("Virtual  threads (" + count + " tasks) : " + e1 + " ms");
		System.out.println("Platform pool-32 (" + count + " tasks) : " + e2 + " ms");
		System.out.println("Platform direct  (" + count + " tasks) : " + e3 + " ms");
	}

}
