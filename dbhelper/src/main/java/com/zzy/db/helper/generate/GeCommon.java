package com.zzy.db.helper.generate;

import com.zzy.db.helper.BeanAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author zzy
 * @Date 2019-07-17 21:16
 */
public class GeCommon {
    private static Logger log = LoggerFactory.getLogger(GeCommon.class);

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String toFirstWordLow(String str) {
        String first = str.substring(0, 1);
        return first.toLowerCase() + str.substring(1);
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String toFirstWordUpp(String str) {
        String first = str.substring(0, 1);
        return first.toUpperCase() + str.substring(1);
    }


    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static List<BeanAttribute> getBeanAttributes(String beanPath) {
        List<BeanAttribute> beanAttributes = new ArrayList();
        File file = new File(beanPath);


        try (FileInputStream inputStream = new FileInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));) {

            String line = null;

            while ((line = reader.readLine()) != null) {
                if (line.contains("@Column")) {
                    BeanAttribute beanAttribute = new BeanAttribute();
                    String[] columnContent = line.trim().split("\"");
                    String[] content = reader.readLine().trim().split(" ");
                    if (line.contains("isPK = true")) {
                        beanAttribute.setIsPk(true);
                    }
                    beanAttribute.setColumn(columnContent[1]);
                    beanAttribute.setName(content[2].replaceAll(";", ""));
                    beanAttribute.setType(content[1]);
                    beanAttributes.add(beanAttribute);
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return beanAttributes;
    }


    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));
        System.out.println((100&8)+"");
    }


    public static void deleteDir(String dirPath) {
        dirPath = dirPath.replace("\\", "/");
        File file = new File(dirPath);
        if (file.isFile()) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                file.delete();
            } else {
                for (int i = 0; i < files.length; i++) {
                    deleteDir(files[i].getAbsolutePath());
                }
                file.delete();
            }
        }
    }

    public static void createFile(String filePath, String content) throws IOException {
        filePath = filePath.replace("\\", "/");
        if (filePath.contains(".")) {
            File file = new File(filePath.substring(0, filePath.lastIndexOf("/")));

            if (!file.exists()) {
                file.mkdirs();
            }
        }else{
            log.info("请确认文件后缀");
        }
        File file = new File(filePath);
        // 写入文件
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
            out.write(content);
            out.flush();
        }
    }


}
