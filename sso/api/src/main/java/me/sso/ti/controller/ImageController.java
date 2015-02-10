package me.sso.ti.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import me.sso.ti.result.Result;
import me.sso.ti.utils.ImageEncoder;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年2月9日 下午1:35:29
 */
@Controller
@RequestMapping(value = "/image")
public class ImageController extends BaseController {
	
	@RequestMapping(value = "/upload", produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> list(@RequestParam(value = "image", required = true) MultipartFile image) {
		Result result = imageService.upload(image);
		return toResponse(result);
	}
	
	@RequestMapping(value = "/{imageId}")
	public void style(@PathVariable Long imageId, HttpServletResponse response) throws Exception {
		Result result = imageService.getImage(imageId);
		if(!result.isSuccess()) {
			out(result, response);
			return;
		}
		ImageEncoder.encode(new FileInputStream((File) result.get("imageFile")), response);
	}
}