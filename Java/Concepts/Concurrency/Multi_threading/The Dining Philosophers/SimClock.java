import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rob Wong on 2/21/2017.
 */
public class SimClock {
//  tis is all it really has
    private static int simSec;
    private static long startMili;
    private static long endMili;
    private static long deltaMili;

    private static long check;  //this is our simSec ratio.   check:1 sim second
    private static long endSimSecs;

    public SimClock() {
        startMili = System.currentTimeMillis();
        endMili = 0;
        deltaMili = 0;
        simSec = 0;
        check = 1000;
        endSimSecs = 10;
    }

  
    public static void tick(){
        //Update the current time of the clock and  it
        simSec +=1;
    }

    public static int getTime(){
        return simSec;
    }

    public static long checkTime() {
        endMili = System.currentTimeMillis();
        deltaMili = endMili - startMili;
        return deltaMili;
    }

    //how the clock would work.
    public static void runClock(){
        long limit = check; 
        while (simSec <endSimSecs) {
            //increment time as stated
            if (checkTime() >= limit)
            {
                simSec++;
                limit += SimClock.check;    
                System.out.format("Total elapsed timed (in miliseconds) %d ::: Total Sim time %d\n\n",checkTime(),getTime());
             }
        }       
    }

    public static void setChecknEnd(long check, long end){ check =check ; endSimSecs =end;}
}
