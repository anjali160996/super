package Java5;

import java.util.List;

public class BatchSummary {

	private final int processed;
	private final int failed;
	private final List<Integer> failedOrderIds;

	public BatchSummary(int processed, int failed, List<Integer> failedOrderIds) {
		this.processed = processed;
		this.failed = failed;
		this.failedOrderIds = failedOrderIds;
	}

	public int getProcessed() {
		return processed;
	}

	public int getFailed() {
		return failed;
	}

	public List<Integer> getFailedOrderIds() {
		return failedOrderIds;
	}

	@Override
	public String toString() {
		return "processed=" + processed + ", failed=" + failed + ", failedOrderIds=" + failedOrderIds;
	}
	 
}