package test;

import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年12月31日 上午12:36:51
 */
public class ImageProps {

	public static void main(String[] args) throws Exception {
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
		ImageReader reader = readers.next();
		// File file = new
		// File("C://Users/Administrator/Desktop/111/IMG_1428.JPG");
		URL url = new URL("http://ganliaotv.qiniudn.com/12_100yuanimg.jpg");
		ImageInputStream input = ImageIO.createImageInputStream(url.openStream());
		reader.setInput(input, true);
		int width = reader.getWidth(0);
		int height = reader.getHeight(0);
		System.out.println(width + "/" + height);
	}
}