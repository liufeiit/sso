package me.sso.ti.srv.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import me.sso.ti.dataobject.ImageDO;
import me.sso.ti.result.Result;
import me.sso.ti.result.ResultCode;
import me.sso.ti.srv.BaseService;
import me.sso.ti.srv.ImageService;
import me.sso.ti.utils.GuidUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年2月9日 下午11:20:41
 */
@Service(value = "imageService")
public class DefaultImageService extends BaseService implements ImageService {

	@Override
	protected void init() throws Exception {
		super.init();
		for (ImageRepositoryType type : ImageRepositoryType.values()) {
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
				log.error("Init Image Repository Error.", e);
			}
		}
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public Result upload(MultipartFile image) {
		if(image == null) {
			return Result.newError().with(ResultCode.Error_Image_Upload);
		}
		ImageRepositoryType type = ImageRepositoryType.Local_0001;
		String imageName = genImageName(image);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(type.getImagePath(imageName));
			StreamUtils.copy(image.getInputStream(), out);
			ImageDO imageDO = new ImageDO();
			imageDO.setGmt_created(new Date());
			imageDO.setUrl(type.getImageURL(imageName));
			imageDAO.persist(imageDO);
			return Result.newSuccess().with(ResultCode.Success).with("image_id", imageDO.getId()).with("image_key", imageDO.getUrl());
		} catch (IOException e) {
			log.error("Upload Image Error.", e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					log.error("Close Image IO Error.", e);
				}
			}
		}
		return Result.newError().with(ResultCode.Error_Image_Upload);
	}

	@Override
	public Result getImage(Long id) {
		if (id == null || id <= 0L) {
			return Result.newError().with(ResultCode.Error_Image_NotExist);
		}
		ImageDO image = imageDAO.get(id);
		if (image == null) {
			return Result.newError().with(ResultCode.Error_Image_NotExist);
		}
		String imageURL = getImagePath(image);
		return Result.newSuccess().with(ResultCode.Success).response(imageURL);
	}

	public String getImagePath(ImageDO image) {
		if (image == null) {
			return DefaultImagePath;
		}
		for (ImageRepositoryType type : ImageRepositoryType.values()) {
			if (!type.open) {
				continue;
			}
			String url = image.getUrl();
			if (!StringUtils.startsWith(url, type.prefix)) {
				continue;
			}
			return type.getImagePath(StringUtils.substring(url, StringUtils.length(type.prefix), StringUtils.length(url)));
		}
		return DefaultImagePath;
	}

	private String genImageName(MultipartFile image) {
		String imageType = getImageType(image.getOriginalFilename());
		return GuidUtils.guid() + "." + imageType;
	}

	public String getImageType(final String fileName) {
		String fileType = "";
		int index = fileName.lastIndexOf(".");
		if (index != -1) {
			fileType = fileName.substring(index + 1);
		}
		return fileType;
	}
}
