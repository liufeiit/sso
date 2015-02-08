package me.sso.ti.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

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
			com.sun.image.codec.jpeg.JPEGImageDecoder decoder = com.sun.image.codec.jpeg.JPEGCodec.createJPEGDecoder(in);
			BufferedImage image = decoder.decodeAsBufferedImage();
			com.sun.image.codec.jpeg.JPEGImageEncoder encoder = com.sun.image.codec.jpeg.JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
		} finally {
			in.close();
		}
		
	}
}