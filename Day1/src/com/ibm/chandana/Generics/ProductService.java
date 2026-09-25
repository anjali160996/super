package com.ibm.chandana.Generics;

import java.util.List;

public class ProductService {

	public ProductStats summarizeProducts(Repository<Product> catalog, String namePrefix) {
		String prefixLower = namePrefix.toLowerCase();
		List<Product> matched = catalog.findAll().stream()
				.filter(p -> p.getName().toLowerCase().startsWith(prefixLower))
				.toList();
		ProductStats stats = new ProductStats();
		stats.setCount(matched.size());
		stats.setTotalPrice(matched.stream().mapToDouble(Product::getPrice).sum());
		stats.setMaxPrice(matched.stream().mapToDouble(Product::getPrice).max());
		return stats;
	}
}
