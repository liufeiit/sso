package me.sso.ti.controller;

import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

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
	
	private static final int CACHE_SECONDS = 7 * 24 * 3600;
	
	private static final String HEADER_EXPIRES = "Expires";

	private static final String HEADER_CACHE_CONTROL = "Cache-Control";

	@RequestMapping("/{imageId}")
	public ResponseEntity<byte[]> image(@PathVariable Long imageId, HttpServletResponse response) throws Exception {
		Result result = imageService.getImage(imageId);
		if (!result.isSuccess()) {
			return null;
		}
		response.setDateHeader(HEADER_EXPIRES, System.currentTimeMillis() + CACHE_SECONDS * 1000L);
		String headerValue = "max-age=" + CACHE_SECONDS + ", must-revalidate";
		response.setHeader(HEADER_CACHE_CONTROL, headerValue);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(StreamUtils.copyToByteArray(new FileInputStream(result.getResponse(String.class))), headers, HttpStatus.CREATED);
	}
}