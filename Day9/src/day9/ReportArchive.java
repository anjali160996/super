package day9;

import java.io.IOException;

public class ReportArchive {
    static class ReportService {
        final StringBuilder archive = new StringBuilder();

        String render(int requestId) {
            String body = "report=" + requestId + " status=ok " + "x".repeat(4096);
            archive.append(requestId);
            archive.append('\n');
            return body;
        }
    }

    public static void main(String[] args) throws IOException {
        ReportService svc = new ReportService();
        int n = 35_000;
        long t = System.nanoTime();
        int chars = 0;
        for (int i = 0; i < n; i++) {
            chars += svc.render(i).length();
            System.out.println(svc.render(i));
        }
        System.gc();
        long usedMb = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024;
        System.out.println("chars=" + chars
                + " archiveLen=" + svc.archive.length()
                + " usedMB=" + usedMb
                + " ms=" + (System.nanoTime() - t) / 1_000_000);
        System.out.println(svc.archive);
        System.in.read(); 
    }
}
