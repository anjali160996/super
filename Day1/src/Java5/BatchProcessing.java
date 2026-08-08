package Java5;

import java.util.ArrayList;
import java.util.List;


public class BatchProcessing {
	public static void main(String args[]) {
		Orderprocess orderProces = new Orderprocess();
		List<Order> orders = new ArrayList<Order>(10);
		for (int i = 0; i < 10; i++) {
			orders.add(new Order(i, "payload-" + i));
		}
		BatchSummary result1 = orderProces.orderprocessing(orders, 4);
		System.out.println(result1.toString());
		BatchSummary result2 = orderProces.orderprocessing(orders, 4);
		System.out.println(result2.toString());
		BatchSummary result3 = orderProces.orderprocessing(orders, 4);
		System.out.println(result3.toString());
		List<Order> regionOrders = List.of(
			new Order(1, "payload-a", "IN", 100),
			new Order(2, "payload-b", "IN",  50),
			new Order(3, "payload-c", "US", 200),
			new Order(4, "payload-d", "US",  30)
		);
		System.out.println("totalByRegion→ "+ orderProces.totalByRegion(regionOrders));
		System.out.println("countByRegion   → " + orderProces.countByRegion(regionOrders));
		System.out.println("partitionHighValue(threshold=100):");
		orderProces.partitionHighValue(regionOrders,100).forEach((highValue,list) ->
			System.out.println("  "+ highValue + " → " + list)
		);
		List<Order> regionOrder = List.of();
		System.out.println("totalByRegion2→ "+ orderProces.totalByRegion(regionOrder));
		System.out.println("countByRegion2   → " + orderProces.countByRegion(regionOrder));
		System.out.println("partitionHighValue(threshold=100)2:");
		orderProces.partitionHighValue(regionOrder,100).forEach((highValue,list) ->
			System.out.println("  "+ highValue + " → " + list)
		);
	}

}
