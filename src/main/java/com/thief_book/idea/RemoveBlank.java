package com.thief_book.idea;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @program: com.thief
 * @author: youvy
 * @create: 2019-12-29 10:47
 **/
public class RemoveBlank {
    final static private Pattern PATTERN = Pattern.compile("[\\u4e00-\\u9fa5\\s\\w]+$");

    /**
     * 删除txt文档空白行
     *
     * @author WTCLAB_yd
     */
    public static void main(String[] args) {

        //用命令行参数直接写入待处理文件
        File file = new File("C:\\Users\\Riko\\Downloads\\7501408086835.txt");
        File file1 = new File("C:\\Users\\Riko\\Downloads\\threeKingdom.txt");


        //判断输入的文档是否存在，不存在则提示退出
        if (!file.exists()) {
            System.out.println("文件不存在！");
            System.exit(0);
        }
        //输入的是TXT文档则继续往下执行
        try {
            //读出文档数据流方式
            //读入数据流方式设为‘UTF-8’，避免乱码
            InputStreamReader stream = new InputStreamReader(new FileInputStream(file), "utf-8");
            //构造一个字符流的缓存器，存放在控制台输入的字节转换后成的字符
            BufferedReader reader = new BufferedReader(stream);
            //写入数据流方式
            OutputStreamWriter outStream = new OutputStreamWriter(new FileOutputStream(file1), "gbk");
            BufferedWriter writer = new BufferedWriter(outStream);
            //以行读出文档内容至结束
            StringBuilder stringBuilder = new StringBuilder();
            String oldLine;
            int i = 0;
            while ((oldLine = reader.readLine()) != null) {
                //判断是否是空行
                if (StringUtils.isNotBlank(oldLine)) {
                    stringBuilder.append(oldLine);
                    Matcher matcher = PATTERN.matcher(oldLine);
                    if (!matcher.find()) {
                        //可在文档中标出行号
                        writer.write("[" + ++i + "]");
                        //将非空白行写入新文档
                        writer.write(stringBuilder.toString() + "\r\n");
                        stringBuilder = new StringBuilder();
                    }
                }
            }
            //关闭数据流
            writer.close();
            reader.close();
            System.out.println("修改完成！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
