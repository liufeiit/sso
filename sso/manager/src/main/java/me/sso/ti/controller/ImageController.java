package me.sso.ti.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import me.sso.ti.WebBase;
import me.sso.ti.result.Result;
import me.sso.ti.utils.ImageEncoder;

import org.springframework.stereotype.Controller;
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
	
	@RequestMapping(value = "/{imageId}")
	public void image(@PathVariable Long imageId, HttpServletResponse response) throws Exception {
		Result result = imageService.getImage(imageId);
		if(!result.isSuccess()) {
			return;
		}
		ImageEncoder.encode(new FileInputStream((File) result.get("imageFile")), response);
	}
}