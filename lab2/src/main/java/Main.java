import java.io.*;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        int amount_of_threads = Integer.parseInt(args[0]);

        //reding file
        File file = new File("C:\\Users\\iwona\\OneDrive - Politechnika Gdańska\\studia\\SEM4\\pt\\JavaLAB\\lab2\\numbers.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        //ArrayList<Integer> calculatedNumbers = new ArrayList<Integer>();

        while((st = br.readLine()) != null) {
            numbers.add(Integer.parseInt(st));
        }

        ArrayList<Integer> dividers = new ArrayList<Integer>();
        Thread thread = new Thread(new Divider(dividers, 20));
        thread.start();

        Thread.sleep(10000);
        System.out.println(dividers);

    }
}
