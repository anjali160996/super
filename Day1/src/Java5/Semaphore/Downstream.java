package Java5.Semaphore;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Downstream {
	private static final int MAX_PERMITS = 3;
	private final Semaphore s = new Semaphore(MAX_PERMITS, true);
	private final AtomicInteger in = new AtomicInteger(0);
	private final AtomicInteger peak = new AtomicInteger(0);

	public int getPeak() {
		return peak.get();
	}

	public <T> T invokeProtected(Callable<T> work) throws Exception {
		boolean acquire = s.tryAcquire(5, TimeUnit.SECONDS);
		if (!acquire) {
			throw new IllegalStateException("Dont have permits to acquire");
		}
		try {
			int count = in.incrementAndGet();
			peak.updateAndGet(old -> Math.max(old, count));
			return work.call();
		} finally {
			in.getAndDecrement();
			s.release();
		}
	}

	public int getAvailablePermits() {
		return s.availablePermits();
	}
}
