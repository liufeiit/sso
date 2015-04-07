package me.sso.ti.controller;

import java.io.FileInputStream;

import me.sso.ti.WebBase;
import me.sso.ti.result.Result;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年2月9日 下午1:35:29
 */
@Controller
@RequestMapping(value = "/image")
public class ImageController extends WebBase {

	@RequestMapping("/{imageId}")
	public ResponseEntity<byte[]> image(@PathVariable Long imageId) throws Exception {
		Result result = imageService.getImage(imageId);
		if (!result.isSuccess()) {
			return null;
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(StreamUtils.copyToByteArray(new FileInputStream(result.getResponse(String.class))), headers, HttpStatus.CREATED);
	}
}