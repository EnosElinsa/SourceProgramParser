package processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class JavaSourceFileParser {
    /**
     * Count the number of lines in the source program file, including empty lines
     * @param sourceFile file to be analyzed
     * @return -1 indicates that the statistics failed. 0 and a positive integer indicate the number of rows
     */
    public long getNumberOfLines(File sourceFile) {
        int numberOfLines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            // The readLine method returns null to indicate the end of the read
            while (reader.readLine() != null) {
                numberOfLines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            numberOfLines = -1;
        }
        return numberOfLines;
    }

    /**
     * Count the number of blank lines in the source program file
     * @param sourceFile file to be analyzed
     * @return -1 indicates that the statistics failed. 0 and a positive integer indicate the number of rows
     */
    public long getNumberOfBlankLines(File sourceFile) {
        int numberOfBlankLines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // A line of length equal to zero after all whitespace has been removed is a blank line
                if (line.trim().length() == 0) {
                    numberOfBlankLines++;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
            numberOfBlankLines = -1;
        }
        return numberOfBlankLines;
    }
}
