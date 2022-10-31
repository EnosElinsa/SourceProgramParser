package processor;

import java.io.File;
import java.util.ArrayList;

public class MainProsessor {

    public static final String DIRECTORY_TAG = "+"; 
    public static final String FILE_TAG = "-";
    public static final String INDENT = "    "; // 4 tabs 

    private File directory;

    private ArrayList<String> results = new ArrayList<>();

    private long javaFileCount = 0;
    private long lineCount = 0;
    private long blankLineCount = 0;
    private long byteCount = 0;

    public MainProsessor(File directory) {
        this.directory = directory;
    }

    public void start() {
        results.add("Files detail: \n");
        results.add(DIRECTORY_TAG + directory + "\n");
        process(directory, 1);
        results.add("Total: \n");
        results.add(String.format("\t%4d\tJava Files", javaFileCount) + "\n");
        results.add(String.format("\t%4d\tLines in files", lineCount) + "\n");
        results.add(String.format("\t%4d\tBlank Lines", blankLineCount) + "\n");
        results.add(String.format("\t%4d\tBytes", byteCount) + "\n");
    }

    private void process(File directory, int layer) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                results.add(INDENT.repeat(layer) + DIRECTORY_TAG + file.getName() + "\n");
                process(file, layer + 1);
            }
            else if (JavaSourceFileFilters.isJavaSourcecFile(file)){
                long numberOfLines = JavaSourceFileParsers.getNumberOfLines(file);
                long numberOfBlankLines = JavaSourceFileParsers.getNumberOfBlankLines(file);
                long numberOfBytes = file.length();
                javaFileCount++;
                lineCount += numberOfLines;
                blankLineCount += numberOfBlankLines;
                byteCount += numberOfBytes;
                results.add(INDENT.repeat(layer) + FILE_TAG + file.getName() + 
                String.format("\tTotal:%8d, Blank:%8d, %12d Bytes\n", numberOfLines, numberOfBlankLines, numberOfBytes));
            }
        }
    }

    public void showResults() {
        for (String result : results) {
            System.out.print(result);
        }
    }
}
