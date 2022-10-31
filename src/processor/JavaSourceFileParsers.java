package processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaSourceFileParsers {
    private JavaSourceFileParsers() {}
    /**
     * 统计源程序文件的行数，包括空行
     * 
     * @return -1表示统计失败, 0和正整数表示行数
     */
    public static int getNumberOfLines(File sourceFile) {
        int numberOfLines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            // readLine方法返回null表示读取结束
            while (reader.readLine() != null) {
                numberOfLines++;
            }
        } catch (IOException e) {
            numberOfLines = -1;
        }
        return numberOfLines;
    }

    public static int getNumberOfBlankLines(File sourceFile) {
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
        }
        return numberOfBlankLines;
    }

    /**
     * 在源程序文件中统计word出现的次数
     * 
     * @param word 统计次数的单词
     * 
     * @return -1表示统计失败, 0和正整数表示单词个数
     */

    public int getNumberOfWord(File sourceFile, String word) {
        int number = 0;
        // 定义字符串模式，
        // 一个单词是指，其前面是空或非单词字符并且后面也是非单词字符
        Pattern pattern = Pattern.compile("(|[\\W])" + word + "[\\W]");
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // 对当前行字符串line应用模式pattern得到匹配器对象
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    number++; // 匹配器中每发现一个匹配项就表示找到一个
                }
            }
        } catch (IOException e) {
            number = -1;
        }
        return number;
    }

    /**
     * 去除源程序所有的空白行后另存为新文件
     * 
     * @param newFilename 新文件名
     * @return true表示另存成功, false表示失败
     */
    
    public boolean saveAsWithoutBlankLine(File sourceFile, String newFilename) {
        boolean result = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
            PrintWriter writer = new PrintWriter(
                new BufferedWriter(new FileWriter(new File(newFilename))))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // 一行去除所有的空白字符后长度>0的是非空行
                if (line.trim().length() > 0) {
                    writer.println(line); // 要写入一个换行符
                }
            }
        } catch (IOException e) {
            result = false;
        }
        return result;
    }
}
