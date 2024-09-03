package document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class EfficientDocumentGrader {
    public static void main(String[] args) {
        try
        {
            System.out.println("Sentences, words, and syllables:");
            BufferedReader br = new BufferedReader(new FileReader("E:\\Java Coursera Course 4\\Main Project\\MOOCTextEditor\\test_cases\\mod2TestCases.txt"));
            String line;
            PrintWriter out = new PrintWriter("E:\\Java Coursera Course 4\\Main Project\\MOOCTextEditor\\grader_output\\module2.part1.out", "utf-8");
            while ((line = br.readLine()) != null)
            {
                EfficientDocument doc = new EfficientDocument(line);
                String result = doc.getNumSentences() + " " + doc.getNumWords() + " " + doc.getNumSyllables() + " ";
                System.out.print(result);
                out.print(result);
            }
            out.print("\n");
            out.close();
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
