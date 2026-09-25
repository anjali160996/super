package Java5.Semaphore;

public class Result {
    private final String message;

    public Result(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Result{message='" + message + "'}";
    }
}
