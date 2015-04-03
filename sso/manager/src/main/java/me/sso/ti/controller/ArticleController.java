package me.sso.ti.controller;

import javax.validation.Valid;

import me.sso.ti.WebBase;
import me.sso.ti.result.Result;
import me.sso.ti.result.ResultCode;
import me.sso.ti.ro.ArticleSearchRequest;
import me.sso.ti.ro.CreateArticleRequest;
import me.sso.ti.vo.ArticleVO;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年3月26日 下午4:10:58
 */
@Controller
@RequestMapping("article")
public class ArticleController extends WebBase {

	private static final String ARTICLE_DETAIL_VIEW_NAME = "article-detail";
	private static final String ARTICLE_CREATE_VIEW_NAME = "article-create";
	private static final String ARTICLE_LIST_VIEW_NAME = "article-list";

	@RequestMapping("/list")
	public ModelAndView list(ArticleSearchRequest request) {
		Result result = articleService.search(request);
		ModelAndView mv = createModelView(ARTICLE_LIST_VIEW_NAME, result);
		request.setQueryString();
		mv.addObject("query", request.getQuery());
		return mv;
	}

	@RequestMapping("/create")
	public ModelAndView create(@Valid CreateArticleRequest request, BindingResult validResult) {
		if (request.getEnter()) {
			ModelAndView mv = createModelView(ARTICLE_CREATE_VIEW_NAME);
			mv.addObject("req", request);
			return mv;
		}
		if (validResult.hasErrors()) {
			Result result = Result.newError().with(ResultCode.Error_Valid_Request);
			ModelAndView mv = createModelView(ARTICLE_CREATE_VIEW_NAME, result);
			mv.addObject("req", request);
			return mv;
		}
		Result result = articleService.createArticle(request);
		if (!result.isSuccess()) {
			ModelAndView mv = createModelView(ARTICLE_CREATE_VIEW_NAME, result);
			mv.addObject("req", request);
			return mv;
		}
		ArticleVO article = result.getResponse(ArticleVO.class);
		return new ModelAndView("redirect:/article/detail/" + article.getId());
	}

	@RequestMapping("/detail/{article_id}")
	public ModelAndView detail(@PathVariable Long article_id) {
		Result result = articleService.getArticle(article_id);
		ModelAndView mv = createModelView(ARTICLE_DETAIL_VIEW_NAME, result);
		return mv;
	}
}