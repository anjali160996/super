package day7.q001;

public class Records {
	record IngestJob(int id, int delayMs) {};
	record IngestResult(int id, boolean ok) {};

}
