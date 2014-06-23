
// Import the necessary Java synchronization and scheduling classes.

import java.util.concurrent.CountDownLatch;

/**
 * @class PingPongRight
 * @brief This class implements a Java program that creates two
 * instances of the PlayPingPongThread and start these thread
 * instances to correctly alternate printing "Ping" and "Pong",
 * respectively, on the console display.
 */
public class PingPongRight {
    /**
     * Number of iterations to run the test program.
     */
    public final static int mMaxIterations = 10;

    public static CountDownLatch mLatch = null;

    /**
     * @class PlayPingPongThread
     * @brief This class implements the ping/pong processing algorithm
   
     */
    public static class PlayPingPongThread extends Thread {

      
        private final static int FIRST_SEMAPHORE = 0;
        private final static int SECOND_SEMAPHORE = 1;

        /**
         * Maximum number of loop iterations.
         */
        private int mMaxLoopIterations = 0;

       
        // TODO - You fill in here.
        private String message;

        /**
         * alternate pings and pong
         */
        // TODO - You fill in here.
        private SimpleSemaphore[] semaphores = new SimpleSemaphore[2];


        /**
         * Constructor initializes the data member(s).
         */
        public PlayPingPongThread(String stringToPrint,
                                  SimpleSemaphore semaphoreOne,
                                  SimpleSemaphore semaphoreTwo,
                                  int maxIterations) {
            // TODO - You fill in here.
            this.message = stringToPrint;
            this.semaphores[FIRST_SEMAPHORE] = semaphoreOne;
            this.semaphores[SECOND_SEMAPHORE] = semaphoreTwo;
            this.mMaxLoopIterations = maxIterations;
        }

       
        public void run() {
           

            // TODO - You fill in here.
            for (int loops = 1; loops <= this.mMaxLoopIterations; ++loops) {
            	semaphores[FIRST_SEMAPHORE].acquireUninterruptibly();
                System.out.println(this.message+ "(" + loops + ")");
                semaphores[SECOND_SEMAPHORE].release();
            }

            // Ending thread and decreasing latch count
            mLatch.countDown();
        }
    }

  

    /**
     * The main() entry point method into PingPongRight program.
     *
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
       
    
        // TODO initialize this by replacing null with the appropriate
        // constructor call.
        mLatch = new CountDownLatch(2);

        // Create the ping and pong SimpleSemaphores that control
        // alternation between threads.


        SimpleSemaphore pingSemaphore = new SimpleSemaphore(1, false);
        SimpleSemaphore pongSemaphore = new SimpleSemaphore(0, false);

        System.out.println("Ready...Set...Go!");

     
        PlayPingPongThread ping = new PlayPingPongThread( "Ping!  ", pingSemaphore, pongSemaphore,  mMaxIterations);
        PlayPingPongThread pong = new PlayPingPongThread( "Pong!  ", pongSemaphore, pingSemaphore,  mMaxIterations);

        // TODO - Initiate the ping and pong threads,
        ping.start();
        pong.start();

        // TODO - replace the following line with a barrier
        // synchronizer call to mLatch that waits for both threads to
 
        mLatch.await();

        System.out.println("Done!");
    
    }
}
