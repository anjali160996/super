package day6.Q004;

import java.util.List;
import java.util.stream.Collectors;

public class SalesSection implements ReportSection {
	private final String title;
	private final double total;

	public SalesSection(String title, double total) {
		this.title = title;
		this.total = total;
	}

	@Override
	public String render() {
		return title() + ": total=" + total;
	}

	public static String buildReport(List<ReportSection> sections) {
		return sections.stream().map(ReportSection::render).collect(Collectors.joining("\n"));
	}

	@Override
	public String title() {
		return title;
	}

	public static void main(String[] args) {
		ReportSection sales = new SalesSection("Sales", 1250.5);
		ReportSection notes = ReportSection.titled("Notes");
		System.out.println(buildReport(List.of(sales, notes)));

	}

}
