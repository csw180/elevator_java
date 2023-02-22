import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Elevator extends Thread {

    final static int _MAXSTORE = 23;
    final static int _MINSTORE = 1;

    private int story;
    private boolean stop = false;
    private List<Integer> targets = new LinkedList<>();
    String display = null;

    public Elevator(String name) {
        super();
        setName(name);
        story = 0;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public boolean isExist(int target) {
        return targets.contains(target);
    }
    
    public void addTargets(int order) {
        targets.add(order);
        boolean isUp = (story<targets.get(0)) ? true:false;
        if (isUp)
            targets = Stream.concat( targets.stream().filter( (a)-> a > story).sorted()
                                   ,targets.stream().filter( (a)-> a < story).sorted(Comparator.reverseOrder())
                                  ).collect(Collectors.toList());
        else 
            targets = Stream.concat( targets.stream().filter( (a)-> a < story).sorted(Comparator.reverseOrder())
                                   ,targets.stream().filter( (a)-> a > story).sorted()
                                  ).collect(Collectors.toList());
    }
    
    @Override
    public void run() {
        while (!stop) {
            if (targets.size() > 0) {
                int target = targets.get(0);
                if (target > story)  {
                    story++;
                    display = getName()+" "+ story + "↑ "+targets;
                } else if (target < story) {
                    story--;
                    display = getName()+" "+ story + "↓ "+targets;
                }
                if (target==story) {
                    display = getName()+" "+ story + "↔ "+targets;
                    System.out.println(display);
                    targets.remove(0);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }
                    display = getName()+" "+ story + "↔ "+targets;
                }
                System.out.println(display);
            } else {
                display = getName()+" "+ story + "_ "+targets;
                System.out.println(display);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
        }
    }
    
}
