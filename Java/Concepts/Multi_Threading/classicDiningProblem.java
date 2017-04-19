import java.util.Random;
class classicDiningProblem{

	public static void main(String[] args) {
		philosopher[] ph;
		int[] forks;

		//Instantiation and stuff
		ph = new philosopher[5];
		forks = new int[] {1,1,1,1,1};
		for (int i =0; i<5 ; i++) {
			ph[i] = new philosopher(i, new Random().nextInt(4)+1, forks);
		}

		//start threads
		for (int i =0;i<5 ;i++ ) {
			ph[i].start();
		}
		
		//Simulated clock class, using System.currentMilis(), increment time by 1 every 1000 miliseconds as def by check
		new SimClock();
		SimClock.setChecknEnd(1000, 20);
		SimClock.runClock();
		System.exit(0);
	}
}