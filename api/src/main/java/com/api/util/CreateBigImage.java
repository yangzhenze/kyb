package com.api.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateBigImage {

    public static void main(String[] args) throws IOException {
        System.out.println("123");

        /*//设置图片宽度相同
        changeImage("D:/imgs/", "1.jpg", "1.jpg", 300,200);
        //changeImage("D:/imgs/", "2.jpg", "2.jpg", 300,200);
        changeImage("D:/imgs/", "3.jpg", "3.jpg", 300,200);
        //获取宽度相同的图片
        String img1 = "D:/imgs/1.jpg";
        //String img2 = "D:/imgs/2.jpg";
        String img3 = "D:/imgs/3.jpg";
        String[] imgs = new String[]{img1,img3};
        //图片拼接
        merge(imgs,"jpg","D:/imgs/big.jpg");

        String folderPath = "D:/imgs";
        changeFolderImages(folderPath,600,400);

        mergeFolderImgs(folderPath,"jpg","D:/imgs/merge.jpg");*/

        //获取宽度相同的图片
        String img1 = "D:/imgs/2.jpg";
        String img2 = "D:/imgs/1.jpg";


        BufferedImage image = mergeImage(img1,img2,true,60,30);

        getNewImage(image,"jpg","D:/imgs/1merge.jpg");
    }
    /**
     * 合并图片
     * @param folderPath 图片所在文件夹的绝对路径
     * @param imgType 合并后的图片类型（jpg、png...）
     * @param outAbsolutePath（输出合并后文件的绝对路径）
     * @return
     */
    public static String mergeFolderImgs(String folderPath,String imgType,String outAbsolutePath){
        File folder = new File(folderPath);
        File[] imgList = folder.listFiles();
        String[] imgPaths = new String[imgList.length];

        for (int i = 0; i < imgList.length; i++) {
            //System.out.println("文件个数："+imgList[i].length());
            imgPaths[i] = imgList[i].getAbsolutePath();
            System.out.println("第"+i+"张图片途径："+imgPaths[i]);
        }
        merge(imgPaths,imgType,outAbsolutePath);

        System.out.println("---------------------");
        File newImg = new File(outAbsolutePath);
        System.out.println(newImg.getName());
        return newImg.getName();
    }


    /**
     * 设置图片大小（单张图片）
     * @param path 路径
     * @param oldimg 旧图片名称
     * @param newimg 新图片名称
     * @param newWidth 新图片宽度
     * @param newHeight 新图片高度
     */
    public static void changeImage(String path, String oldimg, String newimg, int newWidth,int newHeight) {
        try {
            File file = new File(path + oldimg);
            Image img = ImageIO.read(file);
            // 构造Image对象
//               int wideth = img.getWidth(null); // 得到源图宽
//               int height = img.getHeight(null); // 得到源图长
            BufferedImage tag = new BufferedImage(newWidth, newHeight,
                    BufferedImage.TYPE_INT_RGB);
            tag.getGraphics()
                    .drawImage(img, 0, 0, newWidth, newHeight, null); // 绘制后的图
            FileOutputStream out = new FileOutputStream(path + newimg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag); // 近JPEG编码
            out.close();
        } catch (IOException e) {
            System.out.println("处理文件出现异常");
            e.printStackTrace();
        }
    }


    /**
     * 设置图片大小（批量处理整个文件夹中的图片）
     * @param folderPath 文件夹路径
     * @param newWidth 新图片宽度
     * @param newHeight 新图片高度
     */
    public static void changeFolderImages(String folderPath, int newWidth,int newHeight) {
        try {
            File folder = new File(folderPath);//得到文件夹
            File[] imgList = folder.listFiles();//得到文件夹中的所有图片
            Image image = null;//定义一张图片

            BufferedImage bfImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            FileOutputStream outputStream = null;
            JPEGImageEncoder encoder = null;
            for (int i = 0; i < imgList.length; i++) {
                image = ImageIO.read(imgList[i]);//将得到的图片放入新定义的图片中
                bfImg.getGraphics().drawImage(image, 0, 0, newWidth, newHeight, null);//绘制后的图
                outputStream = new FileOutputStream(imgList[i]);
                encoder = JPEGCodec.createJPEGEncoder(outputStream);
                encoder.encode(bfImg);
            }
            outputStream.close();
        } catch (IOException e) {
            System.out.println("处理文件出现异常");
            e.printStackTrace();
        }
    }


    /**
     * Java拼接多张图片
     *
     * @param pics:图片源文件 （必须要宽度一样），如：
     *                         String img1 = "D:/imgs/3.jpg";
     *                        String img2 = "D:/imgs/3.jpg";
     *                        String img3 = "D:/imgs/big.jpg";
     *                        String[] pics = new String[]{img1,img2,img3};
     * @param type ：图片输出类型（jpg，png，jpeg...）
     * @param dst_pic ：图片输出绝对路径，如 String dst_pic="D:/imgs/big2.jpg";
     * @return
     */
    public static boolean merge(String[] pics, String type, String dst_pic) {

        int len = pics.length;  //图片文件个数
        if (len < 1) {
            System.out.println("pics len < 1");
            return false;
        }
        File[] src = new File[len];
        BufferedImage[] images = new BufferedImage[len];
        int[][] ImageArrays = new int[len][];
        for (int i = 0; i < len; i++) {
            try {
                src[i] = new File(pics[i]);
                images[i] = ImageIO.read(src[i]);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            int width = images[i].getWidth();
            int height = images[i].getHeight();
            ImageArrays[i] = new int[width * height];// 从图片中读取RGB
            ImageArrays[i] = images[i].getRGB(0, 0, width, height,
                    ImageArrays[i], 0, width);
        }

        int dst_height = 0;
        int dst_width = images[0].getWidth();
        for (int i = 0; i < images.length; i++) {
            dst_width = dst_width > images[i].getWidth() ? dst_width
                    : images[i].getWidth();

            dst_height += images[i].getHeight();
        }
        System.out.println(dst_width);
        System.out.println(dst_height);
        if (dst_height < 1) {
            System.out.println("dst_height < 1");
            return false;
        }

        // 生成新图片
        try {
            // dst_width = images[0].getWidth();
            BufferedImage ImageNew = new BufferedImage(dst_width, dst_height,
                    BufferedImage.TYPE_INT_RGB);
            int height_i = 0;
            for (int i = 0; i < images.length; i++) {
                ImageNew.setRGB(0, height_i, dst_width, images[i].getHeight(),
                        ImageArrays[i], 0, dst_width);
                height_i += images[i].getHeight();
            }

            File outFile = new File(dst_pic);
            ImageIO.write(ImageNew, type, outFile);// 写图片
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 待合并的两张图必须满足这样的前提，如果水平方向合并，则高度必须相等；如果是垂直方向合并，宽度必须相等。
     * mergeImage方法不做判断，自己判断。
     *
     * @param imgPath
     *            待合并的第一张图
     * @param imgPath2
     *            带合并的第二张图
     * @param isHorizontal
     *            为true时表示水平方向合并，为false时表示垂直方向合并
     * @return 返回合并后的BufferedImage对象
     * @throws IOException
     */
    public static BufferedImage mergeImage(String imgPath, String imgPath2, boolean isHorizontal,int startX, int startY)throws IOException {
        BufferedImage img1 = ImageIO.read(new FileInputStream(imgPath));
        BufferedImage img2 = ImageIO.read(new FileInputStream(imgPath2));
        int w1 = img1.getWidth();
        int h1 = img1.getHeight();
        int w2 = img2.getWidth();
        int h2 = img2.getHeight();

        // 从图片中读取RGB
        int[] ImageArrayOne = new int[w1 * h1];
        ImageArrayOne = img1.getRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 逐行扫描图像中各个像素的RGB到数组中
        int[] ImageArrayTwo = new int[w2 * h2];
        ImageArrayTwo = img2.getRGB(0, 0, w2, h2, ImageArrayTwo, 0, w2);

        // 生成新图片
        BufferedImage DestImage = null;
        if (isHorizontal) { // 水平方向合并
            DestImage = new BufferedImage(w1, h1, BufferedImage.TYPE_INT_RGB);
            DestImage.setRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
            DestImage.setRGB(startX,startY, w2, h2, ImageArrayTwo, 0, w2); // 设置下半部分的RGB
        } else { // 垂直方向合并
            DestImage = new BufferedImage(w1, h1 + h2,BufferedImage.TYPE_INT_RGB);
            DestImage.setRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
            DestImage.setRGB(0, h1, w2, h2, ImageArrayTwo, 0, w2); // 设置下半部分的RGB
        }
        return DestImage;
    }

    public static void getNewImage(BufferedImage bufferedImage,String type,String dst_pic){
        File outFile = new File(dst_pic);
        try {
            ImageIO.write(bufferedImage, type, outFile);// 写图片
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
