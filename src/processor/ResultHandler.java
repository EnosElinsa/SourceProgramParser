package processor;

import java.io.*;
import java.util.ArrayList;

/**
 * <p><strong>This class contains methods for reading and writing the entire program. </p>
 * <p>The {@code getResultInfo} method returns the file information stored in the result folder, 
 * the {@code getResult} method returns the contents of the specified result file, 
 * and the {@code saveResult} method saves the processed result to the corresponding file
 * @author  Enos
 */
public class ResultHandler {

    /**
     * The name of the result file
     */
    public static final String RESULT_DIR_NAME = "result";

    /**
     * A File instance of the result file
     */
    private File resultDirectory = new File(RESULT_DIR_NAME);

    /**
     * Save the result line by line.
     * 
     * @param linesOfResult 
     * @return {@code true} Succeeded; {@code false} Failed. 
     */
    public boolean saveResult(ArrayList<String> linesOfResult, File newResultFile) {
        // Make a directory named result for storing the results.
        if (resultDirectory.exists() == false) {
            if (resultDirectory.mkdir() == false) {
                System.out.println(">Making result directory failed!");
                return false;
            }
        }
        
        // Create a new text file for storing the results.
        File newResultTextFile = new File(RESULT_DIR_NAME + File.separator + newResultFile.getName() + ".txt");
        try {
            newResultTextFile.createNewFile();
        } catch (IOException e1) {
            System.out.println(">Creating new result file failed!");
            e1.printStackTrace();
            return false;
        }

        // Write the result to the text.
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(newResultTextFile)))) {
            for (String line : linesOfResult) {
                writer.printf(line);
            }
        } catch (IOException e) {
            System.out.println(">Writing result to the file failed!");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Get a string containing all the result file names.
     * 
     * @return {@code null}No result found in the result directory; 
     * {@code not null}The string containing all the result file names. 
     */
    public String getResultInfo() {
        if (resultDirectory.listFiles().length == 0) {
            // No result found
            return null;
        }

        String resultInfo = "";
        int count = 1;
        for (File resultFile : resultDirectory.listFiles()) {
            resultInfo += String.format("%d.%s\n", count++, resultFile.getName());
        }
        return resultInfo;
    }

    /**
     * Get a string containing the content of the specified result file.
     * 
     * @param index the index of result file specified to be viewed.
     * @return a string containing the content of the specified result file;
     * {@code "Invaid index"} will be returned if the index is out of bound.
     */
    public String getResult(int index) {
        if (index >= resultDirectory.listFiles().length || index < 0) {
            return ">Error: Invalid index!";
        }
        File specifiedResultFile = resultDirectory.listFiles()[index];
        String result = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(specifiedResultFile))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                result += (line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    public File getResultDirectory() {
        return resultDirectory;
    }
}
