import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Map<String, Integer> dictionary = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите путь до файла для анализа текста:");
        String input = sc.next();
        try {
            Scanner scanner = new Scanner(new File(input));
            while (scanner.hasNext()) {
                String scan = scanner.next().toLowerCase();
                scan = scan.replaceAll("[^a-zA-Zа-яёА-ЯЁ]", "").trim();
                String[] words = scan.split("\\s+");
                for (String word : words) {
                    if (word.length() > 2) {
                        if (!dictionary.containsKey(scan)) {
                            dictionary.put(scan, 1);
                        } else {
                            dictionary.put(scan, dictionary.get(scan) + 1);
                        }
                    }
                }
            }
            scanner.close();
        }
        catch (Exception e){
            System.out.println("Файла по данному пути нет");
        }

        //Сортируем по убыванию
        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(dictionary.entrySet());
        sortedWords.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        PrintWriter writer = new PrintWriter(new File("output.txt"));
        for (Map.Entry<String, Integer> entry : sortedWords) {
            writer.println(entry.getKey() + ": " + entry.getValue());
        }
        writer.close();
    }
}