package com.ibm.chandana.Generics;

import java.util.OptionalDouble;

public class ProductStats {
	private long count;
	private double totalPrice;
	private OptionalDouble maxPrice;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OptionalDouble getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(OptionalDouble maxPrice) {
		this.maxPrice = maxPrice;
	}

	@Override
	public String toString() {
		return "ProductStats [count=" + count + ", totalPrice=" + totalPrice + ", maxPrice=" + maxPrice + "]";
	}

}
