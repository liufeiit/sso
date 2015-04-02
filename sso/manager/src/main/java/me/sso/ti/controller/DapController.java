package me.sso.ti.controller;

import me.sso.ti.WebBase;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年3月26日 下午4:10:58
 */
@Controller
public class DapController extends WebBase {

	@RequestMapping("/login")
	public ModelAndView index() {
		ModelAndView mv = createModelView("dev");
		return mv;
	}
}