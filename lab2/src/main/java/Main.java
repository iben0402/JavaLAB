import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        int amount_of_threads = Integer.parseInt(args[0]);

        //reding file
        File file = new File("C:\\Users\\iwona\\OneDrive - Politechnika Gdańska\\studia\\SEM4\\pt\\JavaLAB\\lab2\\numbers.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        ArrayList<Integer> numbers = new ArrayList<Integer>();

        while((st = br.readLine()) != null) {
            numbers.add(Integer.parseInt(st));
        }

        File file1 = new File("C:\\Users\\iwona\\OneDrive - Politechnika Gdańska\\studia\\SEM4\\pt\\JavaLAB\\lab2\\result.txt");
        try {
            if (file1.createNewFile()) {
                System.out.println("File created: " + file1.getName());
            } else {
                System.out.println("File already exists.");
                file1 = new File("C:\\Users\\iwona\\OneDrive - Politechnika Gdańska\\studia\\SEM4\\pt\\JavaLAB\\lab2\\result.txt");
                PrintWriter writer = new PrintWriter(file1);
                writer.print("");
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Resource resource = new Resource(numbers, file1);

        Thread threads[] = new Thread[amount_of_threads];
        System.out.println("Starting threads");
        for (int i = 0; i < amount_of_threads; i++) {
            threads[i] = new Thread(new Divider(resource));
            threads[i].start();
        }

        Scanner scan = new Scanner(System.in);
        String input;
        System.out.println("You can add numbers to list by typing them or exit program by typing 'exit'.");
        while(true) {
            input = scan.nextLine();
            if(input.equals("exit")) {
                for (int i = 0; i < amount_of_threads; i++) {
                    threads[i].interrupt();
                    System.out.println("killed thread");
                }

                System.exit(0);

            }
            else {
                resource.put(Integer.parseInt(input));
                System.out.println("Added " + input + " to list");
            }
        }

    }
}
