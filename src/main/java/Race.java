import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Race {
    private ArrayList<Stage> stages;
    public CyclicBarrier prepBarrier = new CyclicBarrier(MainClass.CARS_COUNT);
    public CountDownLatch prepCdl = new CountDownLatch(MainClass.CARS_COUNT);
    public CountDownLatch startCdl = new CountDownLatch(1);
    public CountDownLatch finishCdl = new CountDownLatch(MainClass.CARS_COUNT);
        public ArrayList<Stage> getStages() { return stages; }
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

}
