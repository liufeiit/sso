package me.sso.ti.srv;

import java.io.File;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年2月9日 下午10:51:55
 */
public interface ImageService {
	
	String ImageRepositoryDir = File.separator + ".vfs" + File.separator + ".ifs" + File.separator;
	
	String ImageRepository = System.getProperty("user.home", "..") + ImageRepositoryDir;
	
	String DefaultImagePath = ImageRepository + "default.jpg";
	
	public enum ImageRepositoryType {
		
		Local_0001("local_0001://", ImageRepository, "本地库0001", true),
		
		Local_0002("local_0002://", System.getProperty("user.dir", "..") + ImageRepository, "本地库0002", false),
		
		Remote_0101("remote://", "", "远程库0101", false),
		
		Remote_0102("remote://", "", "远程库0102", false),
		
		;
		
		public final String prefix;
		public final String repository;
		public final String description;
		public final boolean open;
		
		private ImageRepositoryType(String prefix, String repository, String description,  boolean open) {
			this.prefix = prefix;
			this.repository = repository;
			this.description = description;
			this.open = open;
		}
	}
	
	/**
	 * 获取图片地址
	 * @return 
	 */
	String getImagePath(String imageName);
}