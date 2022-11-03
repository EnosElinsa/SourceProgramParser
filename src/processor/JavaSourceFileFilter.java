package processor;

import java.io.File;
import java.io.FileFilter;

/**
 * This class mainly includes the {@code isJavaSourceFile() isJavaSourecFile} 
 * method that filters files to determine whether the input file extension is.java.
 * @author  Enos
 */
public class JavaSourceFileFilter {

    /**
     * The file Filter to filter files to determine whether the input file extension is.java.
     */
    private static FileFilter fileFilter = (File f) -> {
        return f.isFile() && f.getName().toLowerCase().endsWith(".java");
    };

    /**
     * To determine whether the input file extension is.java.
     * @param file the file to be tested.
     * @return {@code true} is a java source file.
     * {@code false} is not a java source file.
     */
    public boolean isJavaSourceFile(File file) {
        return fileFilter.accept(file);
    }

    public FileFilter getFileFilter() {
        return fileFilter;
    }
}
