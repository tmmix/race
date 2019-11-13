public class MainClass {
    public static final int CARS_COUNT = 4;
    public static String winner;
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));

        for (Stage st :
                race.getStages()) {
            if (st instanceof Tunnel) {
                Tunnel t = (Tunnel) st;
                System.out.println("На пути будет туннель емкостью " + t.getBandwidth());
            }
        }

        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 10 + (int) (Math.random() * 20));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            race.prepCdl.await();
            System.out.println("Все готовы. До старта 3000мс");
            Thread.sleep(3000);
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            race.startCdl.countDown();
            race.finishCdl.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!! " + winner + " получает приз!!!");
            for (int i = 0; i < cars.length; i++) {
                System.out.println(cars[i].getName() + ", имея скорость " + cars[i].getSpeed() + ", ехал " + cars[i].getStopwatch() + "мс");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
