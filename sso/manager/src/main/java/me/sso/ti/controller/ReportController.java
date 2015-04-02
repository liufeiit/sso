package me.sso.ti.controller;

import javax.validation.Valid;

import me.sso.ti.WebBase;
import me.sso.ti.commons.Result;
import me.sso.ti.data.DataExtractResult;
import me.sso.ti.data.DataExtractVendor;
import me.sso.ti.request.ReportQueryRequest;
import me.sso.ti.request.ReportWriteRequest;
import me.sso.ti.ro.QueryReportRequest;
import me.sso.ti.ro.ReportGenerateRequest;
import me.sso.ti.vo.Report;

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
@RequestMapping("report")
public class ReportController extends WebBase {

	@RequestMapping("/list")
	public ModelAndView list(@Valid ReportQueryRequest req, BindingResult validResult) {
		QueryReportRequest request = req.buildRequest();
		Result result = configService.queryReport(request);
		ModelAndView mv = createModelView("report-list", result);
		mv.addObject("query", request.getQuery());
		return mv;
	}

	@RequestMapping("/create")
	public ModelAndView create(@Valid ReportWriteRequest request, BindingResult validResult) {
		if(request.getEnter()) {
			ModelAndView mv = createModelView("report-create");
			mv.addObject("req", request);
			return mv;
		}
		Result result = configService.createReport(request.buildRequest());
		if (!result.isSuccess()) {
			ModelAndView mv = createModelView("report-create", result);
			mv.addObject("req", request);
			return mv;
		}
		Report report = result.getResponse(Report.class);
		return new ModelAndView("redirect:/report/detail/" + report.getId());
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(@Valid ReportWriteRequest request, BindingResult validResult) {
		if(request.getEnter()) {
			Result result = configService.getReport(request.getReport_id(), true);
			ModelAndView mv = createModelView("report-edit", result);
			if(result.isSuccess()) {
				Report report = result.getResponse(Report.class);
				request.mergeReport(report);
				mv.addObject("req", request);
			}
			return mv;
		}
		Result result = configService.updateReport(request.buildRequest());
		if (!result.isSuccess()) {
			ModelAndView mv = createModelView("report-edit", result);
			mv.addObject("req", request);
			return mv;
		}
		Report report = result.getResponse(Report.class);
		return new ModelAndView("redirect:/report/detail/" + report.getId());
	}
	
	@RequestMapping("/detail/{report_id}")
	public ModelAndView detail(@PathVariable Long report_id) {
		Result result = configService.getReport(report_id, true);
		ModelAndView mv = createModelView("report-detail", result);
		return mv;
	}
	
	@RequestMapping("/run/{report_id}")
	public ModelAndView run(@PathVariable Long report_id) {
		ReportGenerateRequest request = new ReportGenerateRequest();
		request.setNotify(true);
		request.setReportId(report_id);
		request.setVendor(DataExtractVendor.SpringBatchSQL);
		Result result = reportService.generate(request);
		if(result.isSuccess()) {
			DataExtractResult dataExtractResult = result.getResponse(DataExtractResult.class);
			return new ModelAndView("redirect:/job/list?job_id=" + dataExtractResult.getJobId());
		}
		return null;
	}
}