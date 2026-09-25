package day7.q002;

public record HttpResult(int statusCode, String body, boolean success) {

	public HttpResult {
		body = (body == null) ? "" : body;
	}
}
