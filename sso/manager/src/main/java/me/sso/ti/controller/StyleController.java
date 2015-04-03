package me.sso.ti.controller;

import javax.validation.Valid;

import me.sso.ti.WebBase;
import me.sso.ti.result.Result;
import me.sso.ti.result.ResultCode;
import me.sso.ti.ro.CreateStyleRequest;
import me.sso.ti.vo.StyleVO;

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
@RequestMapping("style")
public class StyleController extends WebBase {

	private static final String STYLE_DETAIL_VIEW_NAME = "style-detail";
	private static final String STYLE_CREATE_VIEW_NAME = "style-create";
	private static final String STYLE_LIST_VIEW_NAME = "style-list";

	@RequestMapping("/list")
	public ModelAndView list() {
		Result result = styleService.getStyleList();
		ModelAndView mv = createModelView(STYLE_LIST_VIEW_NAME, result);
		return mv;
	}

	@RequestMapping("/create")
	public ModelAndView create(@Valid CreateStyleRequest request, BindingResult validResult) {
		if (request.getEnter()) {
			ModelAndView mv = createModelView(STYLE_CREATE_VIEW_NAME);
			mv.addObject("req", request);
			return mv;
		}
		if (validResult.hasErrors()) {
			Result result = Result.newError().with(ResultCode.Error_Valid_Request);
			ModelAndView mv = createModelView(STYLE_CREATE_VIEW_NAME, result);
			mv.addObject("req", request);
			return mv;
		}
		Result result = styleService.createStyle(request);
		if (!result.isSuccess()) {
			ModelAndView mv = createModelView(STYLE_CREATE_VIEW_NAME, result);
			mv.addObject("req", request);
			return mv;
		}
		StyleVO style = result.getResponse(StyleVO.class);
		return new ModelAndView("redirect:/style/detail/" + style.getId());
	}

	@RequestMapping("/detail/{style_id}")
	public ModelAndView detail(@PathVariable Long style_id) {
		Result result = styleService.getStyle(style_id);
		ModelAndView mv = createModelView(STYLE_DETAIL_VIEW_NAME, result);
		return mv;
	}
}