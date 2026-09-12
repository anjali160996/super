package day6.Q004;

public interface ReportSection {
	String title();

	default String render() {
		return title() + ": (empty)";
	}

	static ReportSection titled(String title) {
		return new ReportSection() {

			@Override
			public String title() {
				return title;
			}

		};

	}

}
