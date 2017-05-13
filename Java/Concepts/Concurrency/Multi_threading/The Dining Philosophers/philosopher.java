import java.util.Random;

public class philosopher extends Thread{

	private Thread t;
	private int ph;				// our philosopher's ID
	private boolean isThinking;	//condition for when to eat/think
	private boolean hasLeftFork;	//philosopher must have both forks in order to eat
	private boolean hasRightFork;
	private int timeToEat;		//How long in seconds a philosopher takes to eat
	private int[] theforks;		//our shared resource variable
	private int leftFork;		//these next two are index values of each philosopher's fork
	private int rightFork;
	private boolean on;			//junk variable

	public philosopher(int ph, int timeToEat, int[] forks)
	{
		isThinking = true;
		this.ph = ph;
		this.timeToEat = timeToEat;
		theforks = forks;
		printIt();
	}


	public philosopher(int ph,  boolean hasLeftFork , boolean hasRightFork, int timeToEat, int[] forks)
	{
		isThinking = true;
		theforks = forks;
		this.ph = ph;
		this.hasLeftFork = hasLeftFork;
		this.hasRightFork = hasRightFork;
		this.timeToEat = timeToEat;
		printIt();
	}

	//
	public void run ()
	{
		on = true;	//not really necessary
		while(true && SimClock.getTime()<=20)	// wait for 20 seconds, then terminate program
		{
			try
			{
				if (!isThinking)
					{
						this.t.sleep(timeToEat*1000);
						this.returnForks(theforks);
						this.isThinking = true;
						int waiting = new Random().nextInt(10);
						System.out.println("Philosopher "+ ph+ " now waiting " + waiting);
						this.t.sleep(waiting);
					}
			}catch (Exception e) {
				e.getMessage();
			}
			
			try
			{	
				//This function avoids deadlock
				checkForks(theforks);	

				// //If you want a deadlock, uncomment the code below and comment out the code above
				// deadLockExample();				
			}catch (Exception e) {e.getMessage();}
		}
	}



	//method to start each thread successfully.
	public void start(){
		System.out.println("Starting Philosopher " + ph);
		if (t == null)
		{
			t = new Thread (this, Integer.toString(ph));
			t.start();
		}
	}

	//Gets all the forks, avoids the deadlock.
	//Synchronization is fully utilize as its control structures access all critical variables
	synchronized void checkForks( int[] forks)throws InterruptedException
	{
		synchronized (forks)
		{
			hasLeftFork = checkForks(forks, true);
			hasRightFork = checkForks(forks, false);
			if (hasLeftFork && hasRightFork)
			{
				isThinking = false;
				System.out.println("Philosopher is eating "+ ph);	
				return;		
			}
			else
			{
				this.returnForks(forks);
				this.t.sleep(2000);
			}
 		}
	}

	//Gets forks one at a time (deadlock very probable), control structure guarantee 
	//only 1 fork will be picked up at a time and not a pair. Unreliable. Verily.
	synchronized boolean checkForks( int[] forks, boolean left) throws InterruptedException
	{
		synchronized(forks)
		{
			int forkPos;
				if (left)
				{
					forkPos = (this.ph == 0)? 4: this.ph-1;		//left and right bound values are hardcoded, could be fixed later.
					leftFork =forkPos;
					if (forks[forkPos] == 1)
					{
						forks[leftFork] = 0;
						printForks();
						System.out.println("philosopher " + ph + " is grabbing fork on " + leftFork);
						return true;
					}
					return false;
				}
				forkPos = (this.ph == 4)? 0: this.ph+1;
				rightFork = forkPos;
			
				if (forks[forkPos] == 1)
					{
						forks[rightFork] = 0;
						printForks();
						System.out.println("philosopher " + ph + " is grabbing fork on " + rightFork);
						return true;
					}
				return false;
		}
	}

	//return all forks to the fork array, and reset bool has****Fork value if any are true.
	synchronized void returnForks(int[] forks) throws InterruptedException
	{
		synchronized (forks)
		{
			if (hasLeftFork)
				forks[leftFork] = 1;
			if (hasRightFork)
				forks[rightFork] = 1;
			System.out.println("Philosopher " +ph + " is returning forks");
			printForks();
			hasLeftFork = hasRightFork = false;
		}
	}

	//You want a deadlock? here's a deadlock.
	public void deadLockExample() throws InterruptedException
	{
		if ( (hasLeftFork = checkForks(theforks,true) )==true)
		{
			if ((hasRightFork = checkForks(theforks,!true)) == true)
			{
				isThinking = false;
				System.out.println("Philosopher is eating "+ ph);			
			}
		}
	}

	//prints out the array of available forks at the moment.
	void printForks()
	{
		for (int i = 0 ; i < 5; i++)
			System.out.print(theforks[i] + " ");
		System.out.println();

	}

	//instantiation stuff
	void printIt()
	{
		System.out.format("Philosopher #%s takes %d seconds to eat his stuff\n", this.ph, this.timeToEat);
	}
	
}