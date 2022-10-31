package processor;

import java.io.File;
import java.io.FileFilter;

public class JavaSourceFileFilter {

    private static FileFilter fileFilter = (File f) -> {
        return f.isFile() && f.getName().toLowerCase().endsWith(".java"); 
    };

    public boolean isJavaSourcecFile(File file) {
        return fileFilter.accept(file);
    }
    
    public FileFilter getFileFilter() {
        return fileFilter;
    }
}
