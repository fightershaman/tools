package com.bsr.tools.login;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class LoginUtil {
    private static int imageWidth = 100; //验证码图片宽
    private static int imageHeight = 40; //验证码图片高

    /**
     * 根据验证码字符串生成验证码图片
     *
     * @param code
     * @return
     */
    public static BufferedImage captchImage(String code) {
        Random random = new Random();
        BufferedImage buffImg = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = buffImg.getGraphics();
        //图像填充底色
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0, 0, imageWidth, imageWidth);
        //字体
        Font font = new Font("Times New Roman", Font.BOLD, 28);
        graphics.setFont(font);
        //边框
        //graphics.setColor(Color.BLACK);
        //graphics.drawRect(0, 0, imageWidth - 1, imageHeight - 1);
        //干扰线
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < 25; i++) {
            int x = random.nextInt(imageWidth);
            int y = random.nextInt(imageHeight);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            graphics.drawLine(x, y, x + xl, y + yl);
        }
        //字符串
        int length = code.length();
        //int red = 0, green = 0, blue = 0;
        for (int i = 0; i < length; i++) {
            //red = random.nextInt(256);
            //green = random.nextInt(256);
            //blue = random.nextInt(256);
            //graphics.setColor(new Color(red, green, blue));
            graphics.setColor(Color.BLACK);
            graphics.drawString(String.valueOf(code.charAt(i)), (2 + (i) * 23), 32);
        }
        return buffImg;
    }

}
