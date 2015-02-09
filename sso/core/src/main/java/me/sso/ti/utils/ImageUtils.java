package me.sso.ti.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * @author 刘飞
 * img width=100 height=100 src="data:image/gif;base64,（此处填图片转成base64编码后的编码）"/>
 * 
 * @version 1.0.0
 * @since 2015年2月9日 上午10:25:41
 */
public class ImageUtils {
	
	public static void svgToStream(String svg, OutputStream out) throws IOException, TranscoderException {
		try {
			PNGTranscoder transcoder = new PNGTranscoder();
			TranscoderInput in = new TranscoderInput(new StringReader(svg));
			TranscoderOutput _out = new TranscoderOutput(out);
			transcoder.transcode(in, _out);
			out.flush();
		} finally {
			out.close();
		}
	}
	
	public static String encodeImgageToBase64(URL imageUrl) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(imageUrl);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "jpg", outputStream);
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(outputStream.toByteArray());
	}

	public static String encodeImgageToBase64(File imageFile) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(imageFile);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "jpg", out);
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(out.toByteArray());
	}

	public static void decodeBase64ToImage(String base64, String imageFile) throws IOException {
		FileOutputStream out = null;
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			out = new FileOutputStream(new File(imageFile));
			byte[] decoderBytes = decoder.decodeBuffer(base64);
			out.write(decoderBytes);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}