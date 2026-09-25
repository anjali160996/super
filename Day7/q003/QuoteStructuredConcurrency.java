package day7.q003;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

public class QuoteStructuredConcurrency {

	static Quote fetchSlow() throws InterruptedException {
		Thread.sleep(800);
		return new Quote("slowww...", 99.0);
	}

	static Quote fetchFast() throws InterruptedException {
		Thread.sleep(200);
		return new Quote("fastttt...", 101.0);
	}

	static Quote fetchFastFailing() throws InterruptedException, IOException {
		Thread.sleep(200);
		throw new IOException("Not avaialble");
	}

	static Quote firstSuccessfulQuote(boolean fastFails) throws InterruptedException, ExecutionException {
		try (var scope = new StructuredTaskScope.ShutdownOnSuccess<Quote>()) {
			scope.fork(() -> fastFails ? fetchFastFailing() : fetchFast());
			scope.fork(() -> fetchSlow());
			scope.join();
			return scope.result();
		}
	}

	static void allMustSucceed() throws InterruptedException, ExecutionException {
		try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
			Subtask<Quote> fastTask = scope.fork(() -> fetchFast());
			Subtask<Quote> slowTask = scope.fork(() -> fetchSlow());
			scope.join();
			scope.throwIfFailed();
			System.out.println("Fasttt result : " + fastTask.get());
			System.out.println("Slowwww result : " + slowTask.get());
		}
	}

	static void oneFailsCancelsAll() throws InterruptedException, ExecutionException {
		try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
			Subtask<Quote> fastTask = scope.fork(() -> fetchFastFailing());
			Subtask<Quote> slowTask = scope.fork(() -> fetchSlow());
			scope.join();
			scope.throwIfFailed();
			System.out.println("Fastttt result : " + fastTask.get());
			System.out.println("Slowwww result : " + slowTask.get());
		}
	}

	public static void main(String[] args) throws Exception {

		System.out.println("ShutdownOnSuccess ===");
		Quote quote1 = firstSuccessfulQuote(false);
		System.out.println("Normal run   : " + quote1);

		Quote quote2 = firstSuccessfulQuote(true);
		System.out.println("Fast fails   : " + quote2);

		System.out.println("ShutdownOnFailure ===");
		System.out.println("Both succeed==>:");
		allMustSucceed();

		System.out.println("One fails====>:");
		try {
			oneFailsCancelsAll();
		} catch (ExecutionException e) {
			System.out.println("Eception : " + e.getCause().getMessage());
		}
	}
}
