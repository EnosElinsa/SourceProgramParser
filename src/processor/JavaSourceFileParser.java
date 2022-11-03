package processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * <p>This class contains the main methods for source file processing, 
 * including the {@code getNumberOfLines} method for counting the total number 
 * of lines in the source program and the {@code getNumberOfBlankLines} method 
 * for counting the number of empty lines.
 * @author  Enos
 */
public class JavaSourceFileParser {
    /**
     * Count the number of lines in the source program file, including empty lines.
     * @param sourceFile file to be analyzed.
     * @return -1 indicates that the statistics failed. 
     * 0 and a positive integer indicate the number of rows.
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
     * Count the number of blank lines in the source program file.
     * @param sourceFile file to be analyzed.
     * @return {@code -1} indicates that the statistics failed.
     * {@code 0} and a positive integer indicate the number of rows.
     */
    public long getNumberOfBlankLines(File sourceFile) {
        int numberOfBlankLines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // The length of a string after the whitespace is removed is equal to 0 to determine whether the line is empty
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
