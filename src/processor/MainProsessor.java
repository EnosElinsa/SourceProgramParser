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
        results.add(String.format("\t\t %d Java Files", javaFileCount) + "\n");
        results.add(String.format("\t\t %d Lines in files", lineCount) + "\n");
        results.add(String.format("\t\t %d Blank Lines", blankLineCount) + "\n");
        results.add(String.format("\t\t %d Bytes", byteCount) + "\n");
    }

    private void process(File directory, int layer) {
        for (File file : JavaSourceFileFilters.getJavaSourceFiles(directory)) {
            if (file.isDirectory()) {
                results.add(INDENT.repeat(layer) + DIRECTORY_TAG + file.getName());
                process(file, layer++);
            }
            else {
                long numberOfLines = JavaSourceFileParsers.getNumberOfLines(file);
                long numberOfBlankLines = JavaSourceFileParsers.getNumberOfBlankLines(file);
                long numberOfBytes = file.length();
                byteCount += numberOfBytes;
                results.add(INDENT.repeat(layer) + FILE_TAG + file.getName() + 
                    String.format("\tTotal:\t%d, Blank:\t%d, \t\t%d Bytes\n", numberOfLines, numberOfBlankLines, numberOfBytes));
            }
        }
    }
}
