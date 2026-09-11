package day6;

import java.util.Arrays;
import java.util.Optional;

public class EnumsQ01 {
	enum DiscountTier {
		NONE(1.0, 0), SILVER(0.95, 10000), GOLD(0.90, 30000), PLATINUM(0.85, 50000);

		private final double rate;
		private final int minSpend;

		DiscountTier(double rate, int minSpend) {
			this.rate = rate;
			this.minSpend = minSpend;
		}

		public boolean qualifies(double cartTotal) {
			return cartTotal >= minSpend;
		}

		public double apply(double cartTotal) {
			return qualifies(cartTotal) ? cartTotal * rate : cartTotal;
		}

		static Optional<DiscountTier> fromCode(String code) {
			return Arrays.stream(DiscountTier.values()).filter(t -> t.name().equalsIgnoreCase(code)).findFirst();
		}

	}

	public static void main(String[] args) {
		System.out.println(DiscountTier.GOLD.apply(30000));
		System.out.println(DiscountTier.GOLD.apply(500));
		System.out.println(DiscountTier.fromCode("gold"));
		System.out.println(DiscountTier.fromCode("vip"));
	}

}
