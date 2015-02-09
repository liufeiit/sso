package me.sso.ti.controller;

import me.sso.ti.result.Result;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年2月9日 下午1:35:29
 */
@Controller
@RequestMapping(value = "/style")
public class StyleController extends BaseController {
	
	@RequestMapping(value = "/list", produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> list() {
		Result result = styleService.getStyleList();
		return toResponse(result);
	}
	
	@RequestMapping(value = "/{styleId}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> style(@PathVariable Long styleId) {
		Result result = styleService.getStyle(styleId);
		return toResponse(result);
	}
}