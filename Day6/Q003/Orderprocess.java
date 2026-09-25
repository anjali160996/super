package Java5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Orderprocess {
	public BatchSummary orderprocessing(List<Order> orders) {
		ExecutorService fixedthreadPool = Executors.newFixedThreadPool(4);
		int processed = 0;
		int failed = 0;
		List<Integer> failedOrderIds = new ArrayList<>();
		if (orders == null || orders.isEmpty()) {
			return (new BatchSummary(0, 0, Collections.emptyList()));
		}
		List<Callable<String>> task = new ArrayList<>(orders.size());
		for (Order o : orders) {
			task.add(() -> {
				Thread.sleep(200);
				if (o.getId() == 3 || o.getId() == 7) {
					throw new Exception("Order" + o.getId() + "failed on purpose");
				}
				return ("Order" + o.getId() + "order success");
			});
		}
		List<Future<String>> result = Collections.emptyList();
		try {
			result = fixedthreadPool.invokeAll(task);
			for (int i = 0; i < orders.size(); i++) {
				try {
					if (result.get(i).get() != null) {
						processed++;
					}
				} catch (ExecutionException e) {
					failed++;
					failedOrderIds.add(orders.get(i).getId());
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return new BatchSummary(0, 0, Collections.emptyList());
		} finally {
			fixedthreadPool.shutdown();
		}
		return new BatchSummary(processed, failed, failedOrderIds);
	}

	CompletableFuture<String> notifyCustomer(Order order) {
		CompletableFuture<Order> future = CompletableFuture.supplyAsync(() -> {
			if (order.getId() <= 0) {
				throw new IllegalArgumentException("Order id must be positive");
			}
			return order;
		});
		CompletableFuture<String> message = future.thenApply(o -> {
			String regionmessage = o.getRegion() == null ? "UNKNOWN" : o.getRegion();

			return "Order " + o.getId() + " confirmed for region " + regionmessage;
		});
		return message.thenApply(msg -> {
			String total;
			if (order.getAmount() > 0) {
				total = String.valueOf(order.getAmount());
			} else {
				total = "Not available";
			}
			return msg + " | total=" + total;
		});
	}

	CompletableFuture<List<String>> notifyAll(List<Order> orders) {
		List<CompletableFuture<String>> futures = orders.stream()
				.map(o -> notifyCustomer(o))
				.collect(Collectors.toList());

		CompletableFuture<Void> allDone =
				CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

		return allDone.thenApply(v ->
				futures.stream()
					   .map(CompletableFuture::join)
					   .collect(Collectors.toList())
		);
	}

	public Map<String, Double> totalByRegion(List<Order> orders) {
		if (orders == null || orders.isEmpty())
			return Collections.emptyMap();
		return orders.stream()
				.collect(Collectors.groupingBy(Order::getRegion, Collectors.summingDouble(Order::getAmount)));
	}

	public Map<String, Long> countByRegion(List<Order> orders) {
		if (orders == null || orders.isEmpty())
			return Collections.emptyMap();
		return orders.stream().collect(Collectors.groupingBy(Order::getRegion, Collectors.counting()));
	}

	public Map<Boolean, List<Order>> partitionHighValue(List<Order> orders, double threshold) {
		if (orders == null || orders.isEmpty())
			return Collections.emptyMap();
		return orders.stream().collect(Collectors.partitioningBy(order -> order.getAmount() >= threshold));
	}
}
