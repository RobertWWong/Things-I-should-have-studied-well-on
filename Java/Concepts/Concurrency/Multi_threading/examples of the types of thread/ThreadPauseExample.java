import java.util.concurrent.TimeUnit;
 
public class ThreadPauseExample {
 
    public static void main(String[] args) {
        pauseUsingThreadSleep();
 
        pauseUsingThreadSleepWithNanos();
 
        pauseUsingTimeUnit();
    }
 
    private static void pauseUsingThreadSleep() {
        System.out.print("Pausing Thread.sleep(500)... ");
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // someone interrupted me!
        }
        long end = System.currentTimeMillis();
        System.out.printf("done after: %d ms%n", end - start);
    }
 
    private static void pauseUsingThreadSleepWithNanos() {
        System.out.print("Pausing Thread.sleep(1000, 500000)... ");
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(1000, 500000);
        } catch (InterruptedException e) {
            // someone interrupted me!
        }
        long end = System.currentTimeMillis();
        System.out.printf("done after: %d ms%n", end - start);
    }
 
    private static void pauseUsingTimeUnit() {
        System.out.print("Pausing TimeUnit.MILLISECONDS.sleep(479)... ");
        long start = System.currentTimeMillis();
        try {
            TimeUnit.MILLISECONDS.sleep(479);
        } catch (InterruptedException e) {
            // someone interrupted me!
        }
        long end = System.currentTimeMillis();
        System.out.printf("done after: %d ms%n", end - start);
    }
}