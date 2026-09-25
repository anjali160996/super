package Java5.limiter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class RateLimiter {

	private volatile ConcurrentHashMap<String, AtomicLong> window = new ConcurrentHashMap<>();
	private final ConcurrentHashMap<String, Long> windowStart = new ConcurrentHashMap<>();
	private final BlockingQueue<String> overflowQueue = new ArrayBlockingQueue<>(5000);
	// private final BlockingQueue<String> overflowQueue = new
	// ArrayBlockingQueue<>(5000000);

	public long recordRequest(String userId) {
		long newCount =window.computeIfAbsent(userId, k -> new AtomicLong(0)).incrementAndGet();
//		long now = System.currentTimeMillis();
//		windowStart.putIfAbsent(userId, now);
//		long firstTime = windowStart.get(userId);
//		long elapsed = now - firstTime;
//		if (elapsed > 60) {
//			resetWindowForUser(userId);
//		}
//		if (newCount > 100) {
//			boolean queued = overflowQueue.offer(userId);
//			if (!queued) {
//				System.out.println("Queue is full");
//			}
//		}
		return newCount;
	}

	private void resetWindowForUser(String userId) {
		windowStart.put(userId, System.currentTimeMillis());
		window.put(userId,new AtomicLong(0));

	}

	public long getCount(String userId) {
		AtomicLong counter = window.get(userId);
	    return counter == null ? 0L : counter.get();

	}

	public void resetWindow() {
		window = new ConcurrentHashMap<>();
	}

	public static ExecutorService createPool() {
		int cores = Runtime.getRuntime().availableProcessors();
		return new ThreadPoolExecutor(cores * 2, cores * 4, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100_000),
				new ThreadPoolExecutor.CallerRunsPolicy());
	}
}
