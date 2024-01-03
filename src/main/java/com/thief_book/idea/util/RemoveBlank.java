package com.thief_book.idea.util;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @program: com.thief
 * @author: youvy
 * @create: 2019-12-29 10:47
 **/
public class RemoveBlank {

    /**
     * 删除txt文档空白行
     *
     * @author WTCLAB_yd
     */
    public static void main(String[] args) {

        //用命令行参数直接写入待处理文件
        File file = new File("C:\\Users\\FO\\Downloads\\药娘的天空(妮可全文手打).TXT");
        File file1 = new File("C:\\Users\\FO\\Downloads\\yndtk.txt");


        //判断输入的文档是否存在，不存在则提示退出
        if (!file.exists()) {
            System.out.println("文件不存在！");
            System.exit(0);
        }
        //输入的是TXT文档则继续往下执行
        try (InputStreamReader stream = new InputStreamReader(Files.newInputStream(file.toPath()), Charset.defaultCharset());
             //构造一个字符流的缓存器，存放在控制台输入的字节转换后成的字符
             BufferedReader reader = new BufferedReader(stream);
             //写入数据流方式
             OutputStreamWriter outStream = new OutputStreamWriter(Files.newOutputStream(file1.toPath()), StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(outStream)) {
            //以行读出文档内容至结束
            String oldLine;
            int i = 0;
            while ((oldLine = reader.readLine()) != null) {
                //判断是否是空行
                if (StringUtils.isBlank(oldLine)) {
                    continue;
                }
                for (String line : split(oldLine)) {
                    StringUtils.removeStart(line, "　　");
                    //可在文档中标出行号
                    writer.write("[" + ++i + "] ");
                    //将非空白行写入新文档
                    writer.write(StringUtils.removeStart(line.trim(), "　　") + "\r\n");
                }
            }
            System.out.println("修改完成！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<String> split(String line) {
        List<String> lines = new LinkedList<>();
        split(line, lines);
        return lines;
    }


    private static int findLast(String line, int maxLength) {
        int minLength = 40;
        if (line.length() < minLength) {
            return -1;
        }
        int i = StringUtils.lastIndexOfAny(StringUtils.substring(line, 0, maxLength), ".",
                " ",
                "　",
                "!",
                "?",
                "\"",
                "~",
                "]",
                "…",
                "}",
                "。",
                "』",
                "！",
                "】",
                "？",
                ";",
                "；",
                "”"
        );
        if (i < minLength) {
            if (maxLength + 50 < line.length()) {
                return findLast(line, maxLength + 50);
            }
            return -1;
        }
        while (i != -1 && i < line.length() - 1 && characterList.contains(line.charAt(i + 1))) {
            i += 1;
        }
        if (line.length() - i < minLength) {
            return -1;
        }
        return i;
    }

    private static void split(String line, List<String> lines) {
        int i = findLast(line, 120);
        if (i == -1) {
            if (StringUtils.isNotBlank(line)) {
                lines.add(line.replace(" ", ""));
            }
            return;
        }
        lines.add(StringUtils.substring(line, 0, i + 1));
        split(StringUtils.substring(line, i + 1), lines);
    }

    final static List<Character> characterList = Arrays.asList('.',
            ' ',
            '　',
            '!',
            '?',
            '\'',
            '~',
            ']',
            '…',
            '}',
            '。',
            '』',
            '！',
            '】',
            '？',
            ';',
            '；',
            '”');

}

