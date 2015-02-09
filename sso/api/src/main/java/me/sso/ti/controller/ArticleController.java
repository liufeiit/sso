package me.sso.ti.controller;

import javax.validation.Valid;

import me.sso.ti.result.Result;
import me.sso.ti.result.ResultCode;
import me.sso.ti.ro.ArticleSearchRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
@RequestMapping(value = "/article")
public class ArticleController extends BaseController {
	
	@RequestMapping(value = "/list", produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> search(@Valid ArticleSearchRequest request, BindingResult validResult) {
		if (validResult.hasErrors()) {
			return toResponse(Result.newError().with(ResultCode.Error_Valid_Request));
		}
		Result result = articleService.search(request);
		return toResponse(result);
	}
	
	@RequestMapping(value = "/{articleId}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> item(@PathVariable Long articleId) {
		Result result = articleService.getArticle(articleId);
		return toResponse(result);
	}
}