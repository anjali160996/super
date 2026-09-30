package day9;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

interface Pricer1 {
    long price(int sku);
}

class CpuPricer1 implements Pricer1 {
    public long price(int sku) {
        long x = sku;
        for (int i = 0; i < 200_000; i++) {
            x = x * 1103515245L + 12345 + i;
        }
        return x & 0xFFFF;
    }
}


class AuditedPricer1 implements Pricer1 {
    private final Pricer1 inner;
    private long calls;

    AuditedPricer1(Pricer1 inner) {
        this.inner = inner;
    }

    public  long price(int sku) {
    	 synchronized(this) {
    		 calls++;
    	 }
        return inner.price(sku);
        
    }
}

public class PriceServiceBefore {
    public static void main(String[] args) throws Exception {
        Pricer1 pricer = new AuditedPricer1(new CpuPricer1());
        int tasks = 10_600;
        ExecutorService pool = Executors.newFixedThreadPool(8);
        long t = System.nanoTime();
        try {
            List<Future<Long>> futures = new ArrayList<>();
            for (int i = 0; i < tasks; i++) {
                int sku = i;
                futures.add(pool.submit(() -> pricer.price(sku)));
            }
            long sum = 0;
            for (Future<Long> f : futures) {
                sum += f.get();
            }
            System.out.println("sum=" + sum + " ms=" + (System.nanoTime() - t) / 1_000_000);
        } finally {
            pool.shutdown();
        }
    }
}
//sum=347347308 ms=2122
