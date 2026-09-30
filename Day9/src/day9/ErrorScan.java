package day9;


	import java.util.ArrayList;
	import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

	public class ErrorScan {
	    static List<String> build(int n) {
	        List<String> lines = new ArrayList<>(n);
	        for (int i = 0; i < n; i++) {
	            lines.add(i % 50 == 0 ? "ERROR disk full id=" + i : "INFO ok id=" + i);
	        }
	        return lines;
	    }

	    static int countErrors(List<String> lines) {
	        int n = 0;
	        Pattern p = Pattern.compile(".*\\bERROR\\b.*");
	        for (String line : lines) {
	             Matcher m = p.matcher(line);
	             if(m.matches()) n++;
	        
	        }
	        return n;
	    }

	    public static void main(String[] args) {
	        List<String> lines = build(800_000);
	        long t = System.nanoTime();
	        int errors = countErrors(lines);
	        System.out.println("errors=" + errors + " ms=" + (System.nanoTime() - t) / 1_000_000);
	    }
	}
	//errors=1600000 ms=34190


