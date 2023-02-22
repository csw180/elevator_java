import java.util.Random;

public class Maestro {

    public void play() {
        Elevator e1 = new Elevator("e1");
        Elevator e2 = new Elevator("                       e2");
        e1.start();
        e2.start();

        Random random = new Random();
        for (int i=0; i<=100;i++) {
            int sleepTime = random.nextInt(10)+1;
            int target = random.nextInt(23)+1;   // 최고층수가 23층임을 감안
            // System.out.println(sleepTime+":"+target);
            if  (target%2==0 && !(e1.isExist(target)))
                e1.addTargets(target);
            if  (target%2==1 && !(e2.isExist(target)))
                e2.addTargets(target);
            try {
                Thread.sleep(sleepTime * 1000);
            } catch (InterruptedException e) {
            }
        }
        e1.setStop(true);
        e2.setStop(true);
    }
}
