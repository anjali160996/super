package day6;

public class SealedtypesQ02 {

	record Approved(String transactionId, double amount) implements PaymentResult {
	}

	record Declined(String reason) implements PaymentResult {
	}

	record Pending(String reference) implements PaymentResult {
	}

	String describe(PaymentResult result) {
		return switch (result) {
		case Approved a -> "Payment approved: " + a.transactionId() + ", amount = " + a.amount();
		case Declined d -> "Payment declined: " + d.reason();
		case Pending p -> "Payment pending: " + p.reference();
		};

	}

	public static void main(String args[]) {
		SealedtypesQ02 s = new SealedtypesQ02();
		PaymentResult p1 = new Approved("TXN123", 5000);
		PaymentResult p2 = new Declined("Insufficient funds");
		PaymentResult p3 = new Pending("REF-42");
		System.out.println(s.describe(p1));
		System.out.println(s.describe(p2));
		System.out.println(s.describe(p3));

	}

}
