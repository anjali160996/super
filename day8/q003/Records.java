package day8.q003;

record ProbeTarget(int id, int delayMs){};
record ProbeResult(int id, boolean ok, long elapsedMs) {};
