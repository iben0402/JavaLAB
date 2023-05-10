import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Resource {
    ArrayList<Integer> numbers;
    File file;
    String text = "";

    public Resource(ArrayList<Integer> numbers, File file) {
        this.numbers = numbers;
        this.file = file;
    }

    public synchronized void put(int numb) {
        this.numbers.add(numb);
        notifyAll();
    }

    public synchronized int take() throws InterruptedException {
        while(numbers.isEmpty() == true) {
            wait();
        }

        return numbers.remove(0);
    }

    public synchronized void putToFile(int number, ArrayList<Integer> dividers) {
        String str = number + ": " + dividers.stream().map(Object::toString).collect(Collectors.joining(", ")) + "\n";
        try {
            FileWriter myWriter = new FileWriter(file);
            text += str;
            myWriter.write(text);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
