package me.sso.ti.controller;

import me.sso.ti.WebBase;
import me.sso.ti.commons.Result;
import me.sso.ti.request.HomeQueryRequest;
import me.sso.ti.ro.QueryJobRequest;

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
@RequestMapping("home")
public class HomeController extends WebBase {

	@RequestMapping("/index")
	public ModelAndView index(HomeQueryRequest request) {
		QueryJobRequest req = request.buildRequest();
		Result result = reportService.queryJob(req);
		ModelAndView mv = createModelView("index", result);
		mv.addObject("query", req.getQuery());
		Result data = reportService.jobData();
		mv.addAllObjects(data.getData());
		return mv;
	}
}