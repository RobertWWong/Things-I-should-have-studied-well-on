//Daemon threads are those of lowest priority. They are considered a background thread that terminates only when the JVM terminates, useful when you want to do a task but do not want that (thread)task to prevent the termination of your MAIN thread.
 
import java.util.concurrent.TimeUnit;
import java.util.Random;
 
public class ThreadDaemon {
 
    private static class SampleThread extends Thread {
        private final int id;
        static long start;
        static long end;
 
        public SampleThread(int id) {
            this.id = id;
        }
 
        public void run() {
            end = System.currentTimeMillis();
            System.out.println("Running thread " + id + " at this time : "+ (end-start));
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(5)+8);
            } catch (InterruptedException e) {
                System.out.println("Interrupted!");
            } finally {
                System.out.println("Finally in thread " + id);
            }
            end = System.currentTimeMillis();
            System.out.println("Stopping thread " + id+ " at this time : "+(end-start));
        }
    }
 
    public static void main(String[] args) {
        System.out.println("Starting threads:");
        for (int i = 1; i <= 5; ++i) {
            Thread t = new SampleThread(i);
            SampleThread.start = System.currentTimeMillis();
            // Daemon trigger by setting it to true: If set to false, 
            //it'll be a regular thread and won't wait as specified
            t.setDaemon(true);
            t.start();
        }
        try{
            Thread.sleep(10*1000);
        }catch (Exception e) {
        }
        System.out.println("\n\n ALL IS DONE\n\n");

            
        System.out.println("Stopping program");
    }
}