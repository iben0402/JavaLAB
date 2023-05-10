import java.util.ArrayList;

public class Divider implements Runnable{

    ArrayList<Integer> dividers;
    private int number;

    public Divider(ArrayList<Integer> dividers, int number) {
        this.dividers = dividers;
        this.number = number;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = number; i > 0 ; i--) {
            if(number % i == 0) {
                dividers.add(i);
                //System.out.println(i);
            }
        }


    }
}
