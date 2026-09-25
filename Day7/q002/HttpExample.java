package day7.q002;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpExample {

	public static HttpResult fetch(HttpClient client, String url) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).timeout(Duration.ofSeconds(5)).GET()
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		boolean success = response.statusCode() / 100 == 2;
		return new HttpResult(response.statusCode(), response.body(), success);
	}

	public static String fetchBodyOrThrow(HttpClient client, String url) throws IOException, InterruptedException {
		HttpResult result = fetch(client, url);
		if (result.success()) {
			return result.body();
		}
		throw new IllegalStateException("HTTP request failed with status: " + result.statusCode());
	}

	public static void main(String[] args) throws IOException, InterruptedException {

		try (HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(3))
				.followRedirects(HttpClient.Redirect.NORMAL).build()) {

			HttpResult result = fetch(client, "https://postman-echo.com/get");
			System.out.println("Status: " + result.statusCode());
			String body = result.body();
			System.out.println("Body: " + body.substring(0, Math.min(100, body.length())));

			try {
				fetchBodyOrThrow(client, "https://postman-echo.com/status/500");
			} catch (IllegalStateException e) {
				System.out.println("Caught expected exception: " + e.getMessage());
			}

		}
	}
}
