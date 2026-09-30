package day9;


import java.util.HashMap;
import java.util.Map;

public class LatencyFold {
//    static long checksum(int[] samples) {
//        Map<Integer, Long> hist = new HashMap<>();
//        for (int sample : samples) {
//            hist.merge(Math.floorMod(sample, 64), 1L, Long::sum);
//        }
//        long folded = 0;
//        for (var entry : hist.entrySet()) {
//            folded += (long) entry.getKey() * entry.getValue();
//        }
//        return folded;
//    }
    static long checksum(int[] samples) {
        long[] hist = new long[64];
        
        for (int sample : samples) {
            hist[Math.floorMod(sample, 64)]++;
        }
        
        // 3. Compute the folded sum using a simple index loop
        long folded = 0;
        for (int i = 0; i < 64; i++) {
            folded += (long) i * hist[i];
        }
        
        return folded;
    }
    

    public static void main(String[] args) {
        int[] samples = new int[90_000_000];
        for (int i = 0; i < samples.length; i++) {
            samples[i] = i * 17;
        }
        long t = System.nanoTime();
        long folded = checksum(samples);
        System.out.println("folded=" + folded + " ms=" + (System.nanoTime() - t) / 1_000_000);
    }
}
//folded=2835000000 ms=205

//folded=2835000000 ms=1052
