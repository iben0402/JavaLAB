import java.util.ArrayList;

public class Divider implements Runnable{

    ArrayList<Integer> dividers = new ArrayList<Integer>();
    private Resource resource;

    public Divider(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            int number = 0;
            try {
                number = resource.take();
            }
            catch (InterruptedException ex) {
                break;
            }
            System.out.println("Calculating dividers for: " + number);
            try {
                Thread.sleep(5000 + (number % 10)*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }

            for (int i = number; i > 0 ; i--) {
                if(number % i == 0) {
                    dividers.add(i);
                }
            }

            resource.putToFile(number, dividers);
            dividers.clear();
        }
    }
}
