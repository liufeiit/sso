package me.sso.ti.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.image.codec.jpeg.JPEGCodec;


/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年2月9日 上午12:05:05
 */
public class ImageEncoder {

	public static void encode(InputStream in, HttpServletResponse response) throws IOException {
		OutputStream out = response.getOutputStream();
		response.setContentType("image/jpeg;charset=UTF-8");
		ImageEncoder.encode(in, out);
	}

	public static void encode(InputStream in, OutputStream out) throws IOException {
		try {
			JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(in);
			BufferedImage image = decoder.decodeAsBufferedImage();
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
		} finally {
			in.close();
		}

	}

	public static void imageResize(File originalImage, File targetImage, int toWidth, float quality) throws IOException {
		if (quality > 1) {
			throw new IllegalArgumentException("Quality has to be between 0 and 1.");
		}
		ImageIcon imageIcon = new ImageIcon(originalImage.getCanonicalPath());
		Image image = imageIcon.getImage();
		Image resizedImage = null;
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		if (width > height) {
			resizedImage = image.getScaledInstance(toWidth, (toWidth * height) / width, Image.SCALE_SMOOTH);
		} else {
			resizedImage = image.getScaledInstance((toWidth * width) / height, toWidth, Image.SCALE_SMOOTH);
		}
		Image tmpImage = new ImageIcon(resizedImage).getImage();
		BufferedImage bufferedImage = new BufferedImage(tmpImage.getWidth(null), tmpImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics g = bufferedImage.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, tmpImage.getWidth(null), tmpImage.getHeight(null));
		g.drawImage(tmpImage, 0, 0, null);
		g.dispose();
		float softenFactor = 0.05f;
		float[] softenArray = { 0, softenFactor, 0, softenFactor, 1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
		Kernel kernel = new Kernel(3, 3, softenArray);
		ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
		bufferedImage = cOp.filter(bufferedImage, null);
		FileOutputStream out = new FileOutputStream(targetImage);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);
		param.setQuality(quality, true);
		encoder.setJPEGEncodeParam(param);
		encoder.encode(bufferedImage);
	}

	public static void main(String[] args) throws IOException {
		File originalImage = new File("/Users/yp/2014-05-24-17165900.jpg");
		imageResize(originalImage, new File("/Users/yp/2014-05-24-17165900-0.jpg"), 150, 0.7f);
		imageResize(originalImage, new File("/Users/yp/2014-05-24-17165900-1.jpg"), 150, 1f);
	}
}