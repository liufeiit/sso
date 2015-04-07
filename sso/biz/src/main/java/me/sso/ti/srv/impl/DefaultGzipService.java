package me.sso.ti.srv.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import me.sso.ti.dataobject.GzipDO;
import me.sso.ti.result.Result;
import me.sso.ti.result.ResultCode;
import me.sso.ti.srv.BaseService;
import me.sso.ti.srv.GzipService;
import me.sso.ti.utils.GuidUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年2月10日 上午10:53:52
 */
@Service(value = "gzipService")
public class DefaultGzipService extends BaseService implements GzipService {

	@Override
	protected void init() throws Exception {
		super.init();
		for (GzipRepositoryType type : GzipRepositoryType.values()) {
			try {
				if (!type.open) {
					continue;
				}
				File repo = new File(type.repository);
				if(repo.exists()) {
					continue;
				}
				repo.mkdirs();
			} catch (Exception e) {
				log.error("Init Gzip Repository Error.", e);
			}
		}
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public Result upload(MultipartFile gzip) {
		if(gzip == null) {
			return Result.newError().with(ResultCode.Error_Gzip_Upload);
		}
		GzipRepositoryType type = GzipRepositoryType.Local_0001;
		String gzipName = genGzipName(gzip);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(type.getGzipPath(gzipName));
			StreamUtils.copy(gzip.getInputStream(), out);
			GzipDO gzipDO = new GzipDO();
			gzipDO.setGmt_created(new Date());
			gzipDO.setUrl(type.getGzipURL(gzipName));
			gzipDAO.persist(gzipDO);
			return Result.newSuccess().with(ResultCode.Success).with("gzip_id", gzipDO.getId()).with("gzip_key", gzipDO.getUrl());
		} catch (IOException e) {
			log.error("Upload gzip Error.", e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					log.error("Close gzip IO Error.", e);
				}
			}
		}
		return Result.newError().with(ResultCode.Error_Gzip_Upload);
	}

	@Override
	public Result getGzip(Long id) {
		if (id == null || id <= 0L) {
			return Result.newError().with(ResultCode.Error_Gzip_NotExist);
		}
		GzipDO gzip = gzipDAO.get(id);
		if (gzip == null) {
			return Result.newError().with(ResultCode.Error_Gzip_NotExist);
		}
		String gzipURL = getGzipPath(gzip);
		if (StringUtils.isEmpty(gzipURL)) {
			return Result.newError().with(ResultCode.Error_Gzip_NotExist);
		}
		File gzipFile = new File(gzipURL);
		if(!gzipFile.exists()) {
			return Result.newError().with(ResultCode.Error_Gzip_NotExist);
		}
		return Result.newSuccess().with(ResultCode.Success).response(gzipFile);
	}

	public static String getGzipPath(GzipDO gzip) {
		if (gzip == null) {
			return StringUtils.EMPTY;
		}
		for (GzipRepositoryType type : GzipRepositoryType.values()) {
			if (!type.open) {
				continue;
			}
			String url = gzip.getUrl();
			if (!StringUtils.startsWith(url, type.prefix)) {
				continue;
			}
			return type.getGzipPath(StringUtils.substring(url, StringUtils.length(type.prefix), StringUtils.length(url)));
		}
		return StringUtils.EMPTY;
	}

	private String genGzipName(MultipartFile gzip) {
		String gzipType = getGzipType(gzip.getOriginalFilename());
		return GuidUtils.guid() + "." + gzipType;
	}

	public static String getGzipType(final String fileName) {
		String fileType = "";
		int index = fileName.lastIndexOf(".");
		if (index != -1) {
			fileType = fileName.substring(index + 1);
		}
		return fileType;
	}
}