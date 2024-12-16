import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        String filePath = "src/text";

        try {
            String content = readFile(filePath);
            Map<String, Integer> wordFrequency = calculateWordFrequency(content);


            List<String> sortedWords = new ArrayList<>(wordFrequency.keySet());
            Collections.sort(sortedWords);

            System.out.println("1. Слова отсортированные по алфавиту:");
            for (String word : sortedWords) {
                System.out.println(word);
            }


            System.out.println("\n2. Посчитать, сколько раз встречается в файле каждое из слов и вывести статистику на экран:");
            for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }


            int maxFrequency = Collections.max(wordFrequency.values());
            System.out.println("\n3.Наиболее часто встречающиеся слова:");
            for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
                if (entry.getValue() == maxFrequency) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    private static String readFile(String filePath) throws FileNotFoundException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content.toString();
    }

    private static Map<String, Integer> calculateWordFrequency(String content) {

        String[] words = content.toLowerCase().split("[\\s\\p{Punct}&&[^']]++");

        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }
        return wordFrequency;
    }
}