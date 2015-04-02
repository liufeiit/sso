package me.sso.ti.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.sso.ti.WebBase;
import me.sso.ti.commons.Result;
import me.sso.ti.commons.StreamUtils;
import me.sso.ti.request.JobQueryRequest;
import me.sso.ti.ro.QueryJobRequest;

import org.springframework.stereotype.Controller;
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
@RequestMapping("job")
public class JobController extends WebBase {

	@RequestMapping("/list")
	public ModelAndView list(JobQueryRequest request) {
		QueryJobRequest req = request.buildRequest();
		Result result = reportService.queryJob(req);
		ModelAndView mv = createModelView("job-list", result);
		mv.addObject("query", req.getQuery());
		return mv;
	}
	
	@RequestMapping("/download/{jobId}")
	public ModelAndView download(@PathVariable Long jobId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Result result = reportService.downloadJobReport(jobId);
		if(!result.isSuccess()) {
			return new ModelAndView("redirect:/job/list?job_id=" + jobId);
		}
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			File target = result.getResponse(File.class);
			request.setCharacterEncoding("UTF-8");
			long fileLength = target.length();
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-disposition", "attachment; filename=" + target.getName());
			response.setHeader("Content-Length", String.valueOf(fileLength));
			in = new BufferedInputStream(new FileInputStream(target));
			out = new BufferedOutputStream(response.getOutputStream());
			StreamUtils.copy(in, out);
		} finally {
			if (in != null) in.close();
			if (out != null) out.close();
		}
		return null;
	}
}