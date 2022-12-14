package processor;

import java.io.File;
import java.util.ArrayList;

/**
 * <p><strong>This class is the core class of the program.</p>
 * <p>By calling the {@code JavaSourceFileParser} class object and {@code JavaSourceFileFilter}
 * class object methods to process the file, and record the processing 
 * information through the {@link ResultHandler#saveResult() saveResult} method of the {@code ResultHandler}
 * class object to store.
 * @author  Enos
 * @see     JavaSourceFileParser
 * @see     JavaSourceFileFilter
 * @see     ResultHandler
 */
public class MainProsessor {

    /**
     * A tag to identify a directory.
     */
    public static final String DIRECTORY_TAG = "+"; 
    /**
     * A tag to indentify a file.
     */
    public static final String FILE_TAG = "-";
    /**
     * A tab(4 spaces in this case).
     */
    public static final String INDENT = "    ";

    private File directory;
    /**
     * <p>A list to hold the result generated by the processor.
     * <p>One element in this list denotes a line of the result.
     */
    private ArrayList<String> linesOfResult = new ArrayList<>();
    private JavaSourceFileFilter javaSourceFileFilter = new JavaSourceFileFilter();
    private JavaSourceFileParser javaSourceFileParser = new JavaSourceFileParser();
    private ResultHandler resultHandler = new ResultHandler();

    private long javaFileCount = 0;
    private long lineCount = 0;
    private long blankLineCount = 0;
    private long byteCount = 0;

    public MainProsessor(File directory) {
        this.directory = directory;
    }

    /**
     * Start to analyze the directory.
     */
    public void start() {
        linesOfResult.add(String.format("[%s] Result:\n\n", directory.getAbsolutePath()));
        linesOfResult.add("Files detail: \n");
        linesOfResult.add(DIRECTORY_TAG + directory + "\n");

        process(directory, 1);

        linesOfResult.add("Total: \n");
        linesOfResult.add(String.format("%10d\tJava Files", javaFileCount) + "\n");
        linesOfResult.add(String.format("%10d\tLines in files", lineCount) + "\n");
        linesOfResult.add(String.format("%10d\tBlank Lines", blankLineCount) + "\n");
        linesOfResult.add(String.format("%10d\tBytes", byteCount) + "\n");
        if (resultHandler.saveResult(linesOfResult, directory)) {
            System.out.println(">Result Saved!");
        }
    }

    /**
     * Generate the result recursively.
     * 
     * @param directory current directory
     * @param layer current layer in the directory
     */
    private void process(File directory, int layer) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                linesOfResult.add(INDENT.repeat(layer) + DIRECTORY_TAG + file.getName() + "\n");
                process(file, layer + 1);
            }
            else if (javaSourceFileFilter.isJavaSourceFile(file)) {
                long numberOfLines = javaSourceFileParser.getNumberOfLines(file);
                long numberOfBlankLines = javaSourceFileParser.getNumberOfBlankLines(file);
                long numberOfBytes = file.length();

                javaFileCount++;
                lineCount += numberOfLines;
                blankLineCount += numberOfBlankLines;
                byteCount += numberOfBytes;

                linesOfResult.add(INDENT.repeat(layer) + FILE_TAG + file.getName() + 
                    String.format("\t\tTotal:\t%d, Blank:\t%d, \t%d Bytes\n", numberOfLines, numberOfBlankLines, numberOfBytes));
            }
        }
    }

    /**
     * Show the results before it is saved in the file.
     */
    public void showResults() {
        for (String result : linesOfResult) {
            System.out.print(result);
        }
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }

    public ArrayList<String> getLinesOfResult() {
        return linesOfResult;
    }

    public void setLinesOfResult(ArrayList<String> linesOfResult) {
        this.linesOfResult = linesOfResult;
    }

    public JavaSourceFileFilter getJavaSourceFileFilter() {
        return javaSourceFileFilter;
    }

    public void setJavaSourceFileFilter(JavaSourceFileFilter javaSourceFileFilter) {
        this.javaSourceFileFilter = javaSourceFileFilter;
    }

    public JavaSourceFileParser getJavaSourceFileParser() {
        return javaSourceFileParser;
    }

    public void setJavaSourceFileParser(JavaSourceFileParser javaSourceFileParser) {
        this.javaSourceFileParser = javaSourceFileParser;
    }

    public long getJavaFileCount() {
        return javaFileCount;
    }

    public void setJavaFileCount(long javaFileCount) {
        this.javaFileCount = javaFileCount;
    }

    public long getLineCount() {
        return lineCount;
    }

    public void setLineCount(long lineCount) {
        this.lineCount = lineCount;
    }

    public long getBlankLineCount() {
        return blankLineCount;
    }

    public void setBlankLineCount(long blankLineCount) {
        this.blankLineCount = blankLineCount;
    }

    public long getByteCount() {
        return byteCount;
    }

    public void setByteCount(long byteCount) {
        this.byteCount = byteCount;
    }
}
