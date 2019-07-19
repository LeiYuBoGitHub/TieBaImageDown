package com.lyb.util;

import java.io.*;

/**
 * @Auther: 野性的呼唤
 * @Date: 2019/7/18 16:31
 * @Description:
 */
public class FileUtil {

    /**
     * 读取文本
     * @param file
     * File对象
     * @param encoding 字符编码
     */
    public static String readText(File file,String encoding){

        //判断是否是文件或者文件是否存在
        if(!file.isFile() && !file.exists()) {
            System.out.println("无法读取,可能不是文件或者文件不存在");
            return null;
        }

        StringBuilder txt = new StringBuilder();

        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt;
            while((lineTxt = bufferedReader.readLine()) != null){
                txt.append(lineTxt);
            }
            read.close();
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return txt.toString();
    }

    /**
     * 创建文件
     * @param inputStream
     * 输入流
     * @param outFilePath
     * 输出地址
     * @param outFileName
     * 文件名称(带格式)
     */
    public static void addFile(InputStream inputStream, String outFilePath, String outFileName){
        //创建文件夹

        addFolder(outFilePath);

        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream(outFilePath+"\\"+outFileName);
            outFlow(inputStream,outputStream);
            //close(inputStream,outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件夹
     * @param folderName 文件夹名称
     */
    private static boolean addFolder(String folderName){
        File file=new File(folderName);
        if (!file.exists()) {
            return file.mkdirs();
        }
        return false;
    }

    /**
     * 关闭流
     */
    private static void close(InputStream input){
        //关闭输入流
        if (input!=null){
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //关闭流
    private static void close(OutputStream out){
        //关闭输出流
        if (out!=null){
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //关闭流
    private static void close(InputStream input,OutputStream out){
        close(input);
        close(out);
    }

    private static void outFlow(InputStream input,OutputStream out){
        byte[] data = new byte[1024];
        int len;
        try {
            while ((len = input.read(data)) != -1) {
                out.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
