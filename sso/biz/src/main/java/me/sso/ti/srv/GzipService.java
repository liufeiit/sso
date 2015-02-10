package me.sso.ti.srv;

import java.io.File;

import me.sso.ti.result.Result;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年2月10日 上午10:53:27
 */
public interface GzipService {
	String GzipRepositoryDir = File.separator + ".vfs" + File.separator + ".zfs" + File.separator;

	String GzipRepository = System.getProperty("user.home", "..") + GzipRepositoryDir;

	public enum GzipRepositoryType {

		Local_0001("local_0001://", GzipRepository, "本地库0001", true),

		Local_0002("local_0002://", System.getProperty("user.dir", "..") + GzipRepositoryDir, "本地库0002", false),

		Remote_0101("remote://", "", "远程库0101", false),

		Remote_0102("remote://", "", "远程库0102", false),

		;

		public final String prefix;
		public final String repository;
		public final String description;
		public final boolean open;

		private GzipRepositoryType(String prefix, String repository, String description, boolean open) {
			this.prefix = prefix;
			this.repository = repository;
			this.description = description;
			this.open = open;
		}

		public String getGzipURL(String gzipName) {
			return prefix + gzipName;
		}

		public String getGzipPath(String gzipName) {
			return repository + gzipName;
		}
	}

	Result upload(MultipartFile gzip);

	Result getGzip(Long id);

}