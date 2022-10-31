package processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class JavaSourceFileParser {
    /**
     *  统计源程序文件的行数，包括空行
     * 
     * @return -1表示统计失败, 0和正整数表示行数
     */
    public long getNumberOfLines(File sourceFile) {
        int numberOfLines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            // readLine方法返回null表示读取结束
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
     * 统计源程序文件的空行数
     * 
     * @return -1表示统计失败, 0和正整数表示行数
     */
    public long getNumberOfBlankLines(File sourceFile) {
        int numberOfBlankLines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // 一行去除所有的空白字符后长度==0的是空行
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
