package util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;


public class AddFontToImg {
	public final static void pressImage(String pressImg, String targetImg,
			int x, int y, float alpha) {
		try {
			File img = new File(targetImg);// 得到目标图片
			Image src = ImageIO.read(img);// 图片io
			int wideth = src.getWidth(null);// 图片宽度
			int height = src.getHeight(null);// 图片高度

			BufferedImage image = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_RGB);// 表示这个图像，它具有合成整数像素的 8 位
			// RGB颜色分量。

			// Graphics2D 它是用于在 Java(tm) 平台上呈现二维形状、文本和图像的基础类。
			Graphics2D g = image.createGraphics();// 创建一个Graphics2D 可用于绘制此图像。

			g.drawImage(src, 0, 0, wideth, height, null);// 呈现一个图像

			// 水印文件
			Image src_biao = ImageIO.read(new File(pressImg));// 得到水印图片io
			int wideth_biao = src_biao.getWidth(null);// 图片宽度
			int height_biao = src_biao.getHeight(null);// 图片高度

			// 设置颜色 透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));// SRC_ATOP 目标色中的源色部分将被合成到目标色中

			// 呈现一个图像
			g.drawImage(src_biao, (wideth - wideth_biao) / 2,
					(height - height_biao) / 2, wideth_biao, height_biao, null);

			// 水印文件结束
			g.dispose();
			ImageIO.write((BufferedImage) image, "gif", img);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void pressText(String pressText, String targetImg, String desImg, 
			String fontName, int fontStyle, Color color, int fontSize, int x,
			int y, float alpha) {
		try {
			
			File img = new File(targetImg);
			Image src = ImageIO.read(img);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.drawImage(src, 0, 0, width, height, null);
			g.setColor(color);
			Font font = new Font(fontName, fontStyle, fontSize);
			
			g.setFont(font);
			
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			g.drawString(pressText, (width - (getLength(pressText) * fontSize))
					/ 2 + x, (height - fontSize) / 2 + y);
			g.dispose();
			
			File imgdes = new File(desImg);
			ImageIO.write((BufferedImage) image, "jpg", imgdes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void pressTextFromWeb(String[] pressText,String bg, String targeturl, String desImg, 
			String fontName, int fontStyle, int fontSize) {
		try {
			if(pressText == null || pressText.length == 0 || pressText.length > 3)
				return ;
			URL url = new URL(targeturl);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			DataInputStream imagein = new DataInputStream(connection.getInputStream());
			
			Image src = ImageIO.read(imagein);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			
			
			Image bgimg = ImageIO.read(new File(bg));
			
			int bgwidth = bgimg.getWidth(null);
			int bgheight = bgimg.getHeight(null);
			
			BufferedImage image = new BufferedImage(bgwidth, bgheight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			g.drawImage(bgimg, 0, 0, bgwidth, bgheight, null);
			
			
			g.drawImage(src, 56, 19, width, height, null);
			g.setColor(new Color(51, 153, 204));
			Font font = new Font(fontName, fontStyle, fontSize);
			g.setFont(font);
			
			int start_height = 240;
			if(pressText.length == 1){
				g.drawString(pressText[0], (bgwidth - 5 - (getLength(pressText[0]) * fontSize))/2, start_height);
			}else if(pressText.length == 2){
				int wordwidth = getLength(pressText[0]) + getLength(pressText[1]);
				g.setColor(new Color(34,139,34));
				g.drawString(pressText[0], (bgwidth - wordwidth * fontSize )/2-5, start_height);
				g.setColor(new Color(255,185,15));
				g.drawString(pressText[1], (bgwidth - wordwidth * fontSize )/2 + getLength(pressText[0]) * fontSize, start_height + fontSize/2);
			}else{
				if(getLength(pressText[0]) > getLength(pressText[1]) || getLength(pressText[0]) > getLength(pressText[2])){
					String temp = pressText[2];
					pressText[2] = pressText[0];
					pressText[0] = temp;
				}
				
				if(getLength(pressText[1]) > getLength(pressText[0]) || getLength(pressText[1]) > getLength(pressText[2])){
					String temp = pressText[2];
					pressText[2] = pressText[1];
					pressText[1] = temp;
				}
				
				for(int i=0 ; i < pressText.length; i++){
					int wordwidth = getLength(pressText[0]) + getLength(pressText[1]);
					g.setColor(new Color(34,139,34));
					g.drawString(pressText[0], (bgwidth - wordwidth * fontSize )/2-5, start_height);
					g.setColor(new Color(255,185,15));
					g.drawString(pressText[1], (bgwidth - wordwidth * fontSize )/2 + getLength(pressText[0]) * fontSize, start_height + fontSize/2);
					g.setColor(new Color(51, 153, 204));
					g.drawString(pressText[2], (bgwidth - 5 - (getLength(pressText[2]) * fontSize))/2, start_height + fontSize + 3 + fontSize/2);
				}
			}
			
			
			g.dispose();
			File imgdes = new File(desImg);
			ImageIO.write((BufferedImage) image, "jpg", imgdes);
			imagein.close();
			connection.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void resize(String filePath, int height, int width, boolean bb) {
		try {
			double ratio = 0.0; // 缩放比例
			File f = new File(filePath);
			BufferedImage bi = ImageIO.read(f);
			Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);
			// 计算比例
			if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
				if (bi.getHeight() > bi.getWidth()) {
					ratio = (new Integer(height)).doubleValue()
							/ bi.getHeight();
				} else {
					ratio = (new Integer(width)).doubleValue() / bi.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform
						.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(bi, null);
			}
			if (bb) {
				BufferedImage image = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_RGB);
				Graphics2D g = image.createGraphics();
				g.setColor(Color.white);
				g.fillRect(0, 0, width, height);
				if (width == itemp.getWidth(null))
					g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
							itemp.getWidth(null), itemp.getHeight(null),
							Color.white, null);
				else
					g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
							itemp.getWidth(null), itemp.getHeight(null),
							Color.white, null);
				g.dispose();
				itemp = image;
			}
			ImageIO.write((BufferedImage) itemp, "jpg", f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

	public static int getLength(String text) {
		int length = 0;
		for (int i = 0; i < text.length(); i++) {
			if (new String(text.charAt(i) + "").getBytes().length > 1) {
				length += 2;
			} else {
				length += 1;
			}
		}
		return length / 2;
	}
	
	// 0.9f 透明度 值大 就不 透明 。
	public static void main(String[] args) throws IOException {
		//pressImage("E:/shuiying.gif", "E:/mubiao.gif", 0, 0, 0.9f);
		String[] words = {"帅气","我爱国际米兰","田斌爱房迪"};
		 //pressTextFromWeb(words, "http://tp3.sinaimg.cn/1248040874/180/5624017333/1", "H:/web/1302952694817_1.jpg","微软雅黑", Font.PLAIN, 24);
		// resize("G:\imgtest\test1.jpg", 500, 500, true);
	}
}