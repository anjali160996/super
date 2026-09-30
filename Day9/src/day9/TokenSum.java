package day9;

import java.io.IOException;

public class TokenSum {
	static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    static long total(String[] tokens) {
        long sum = 0;
        for (String token : tokens) {
        	 if (isNumeric(token)) {
                 sum += Integer.parseInt(token);
             }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        int n = 4000_0000;
        String[] tokens = new String[n];
        for (int i = 0; i < n; i++) {
            tokens[i] = i % 20 == 0 ? Integer.toString(i) : "item-" + (i % 100);
        }
        long t = System.nanoTime();
        long sum = total(tokens);
        System.out.println("sum=" + sum + " ms=" + (System.nanoTime() - t) / 1_000_000);
      
    }
}

//sum=39999980000000 ms=26422
//sum=39999980000000 ms=162
