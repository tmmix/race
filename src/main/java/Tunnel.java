import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private int bandwidth = MainClass.CARS_COUNT / 2;
    private Semaphore smp = new Semaphore(bandwidth);
    private long stopwatch;
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                if (smp.availablePermits() > 0){
                    System.out.println(c.getName() + " готовится к этапу: " + description);
                } else {
                    System.out.println(c.getName() + " ждет этапа: " + description);
                }
                stopwatch =  System.currentTimeMillis();
                System.out.println("Когда приехал " + c.getName() + ", мест в туннеле осталось " + smp.availablePermits());
                smp.acquire();
                System.out.println(c.getName() + " начал этап: " + description + ". Прождал " + (System.currentTimeMillis() - stopwatch) + "мс. Осталось мест " + smp.availablePermits());
                Thread.sleep(length / c.getSpeed() * 1000);
                System.out.println(c.getName() + " закончил этап: " + description);
                smp.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getBandwidth() {
        return bandwidth;
    }
}
