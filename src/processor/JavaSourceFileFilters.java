package processor;

import java.io.File;
import java.io.FileFilter;

public class JavaSourceFileFilters {
    public static final FileFilter fileFilter = (File file) -> {
        return file.isFile() && file.getName().toLowerCase().endsWith(".java"); 
    };

    public static File[] getJavaSourceFiles(File file) {
        return file.listFiles(fileFilter);
    }
}
