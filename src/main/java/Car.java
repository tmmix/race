public class Car implements Runnable {
    private static int CARS_COUNT;
    private long stopwatch;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    public long getStopwatch() {
        return stopwatch;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов. Поедет со скоростью " + this.speed);
            race.prepCdl.countDown();
            race.prepBarrier.await();
            race.startCdl.await();
            stopwatch = System.currentTimeMillis();
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
            stopwatch = System.currentTimeMillis() - stopwatch;
            if (MainClass.winner == null) {
                MainClass.winner = this.name;
                System.out.println("Ура!!! Я (" + this.name + ") приехал первый!!!");
            } else {
                System.out.println("Черт!!! Я (" + this.name + ") опоздал!!!");
            }
            race.finishCdl.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
