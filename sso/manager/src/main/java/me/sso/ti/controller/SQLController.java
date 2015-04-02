package me.sso.ti.controller;

import javax.validation.Valid;

import me.sso.ti.WebBase;
import me.sso.ti.commons.JobBeanNames;
import me.sso.ti.commons.Result;
import me.sso.ti.request.SQLCreateRequest;
import me.sso.ti.request.SQLQueryRequest;
import me.sso.ti.request.SQLUpdateRequest;
import me.sso.ti.ro.QuerySQLRequest;
import me.sso.ti.ro.WriteSQLRequest;
import me.sso.ti.vo.SQL;

import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("sql")
public class SQLController extends WebBase {

	@RequestMapping("/create")
	public ModelAndView create(@Valid SQLCreateRequest request, BindingResult validResult) {
		if (request.getEnter()) {
			ModelAndView mv = createModelView("sql-create");
			mv.addObject("req", request);
			return mv;
		}
		if (validResult.hasErrors()) {
			Result result = Result.newError().with(me.sso.ti.commons.ResultCode.ValidRequestError);
			ModelAndView mv = createModelView("sql-create", result);
			mv.addObject("req", request);
			return mv;
		}
		WriteSQLRequest sqlRequest = request.buildRequest();
		if (StringUtils.isNotBlank(request.getSql_select_clause())) {
			sqlRequest.setReader_bean_name(JobBeanNames.SQL_PAGING_READER);
			sqlRequest.setWriter_bean_name(JobBeanNames.DATARESULT_CSV_WRITER);
		} else if (StringUtils.isNotBlank(request.getDap_sql())) {
			sqlRequest.setReader_bean_name(JobBeanNames.SQL_CURSOR_READER);
			sqlRequest.setWriter_bean_name(JobBeanNames.DATARESULT_CSV_WRITER);
		} else {
			Result result = Result.newError().with(me.sso.ti.commons.ResultCode.ValidRequestError);
			ModelAndView mv = createModelView("sql-create", result);
			mv.addObject("req", request);
			return mv;
		}
		Result result = configService.createSQL(sqlRequest);
		if (!result.isSuccess()) {
			ModelAndView mv = createModelView("sql-create", result);
			mv.addObject("req", request);
			return mv;
		}
		SQL sql = result.getResponse(SQL.class);
		return new ModelAndView("redirect:/sql/detail/" + sql.getId());
	}

	@RequestMapping("/edit")
	public ModelAndView update(@Valid SQLUpdateRequest request, BindingResult validResult) {
		if (request.getEnter()) {
			Result result = configService.getSQL(request.getSql_id());
			ModelAndView mv = createModelView("sql-edit", result);
			if (result.isSuccess()) {
				SQL sql = result.getResponse(SQL.class);
				request.mergeSQL(sql);
				mv.addObject("req", request);
			}
			return mv;
		}
		if (validResult.hasErrors()) {
			Result result = Result.newSuccess().with(me.sso.ti.commons.ResultCode.ValidRequestError);
			ModelAndView mv = createModelView("sql-edit", result);
			mv.addObject("req", request);
			return mv;
		}
		WriteSQLRequest sqlRequest = request.buildRequest();
		if (StringUtils.isNotBlank(request.getSql_select_clause())) {
			sqlRequest.setReader_bean_name(JobBeanNames.SQL_PAGING_READER);
			sqlRequest.setWriter_bean_name(JobBeanNames.DATARESULT_CSV_WRITER);
		} else if (StringUtils.isNotBlank(request.getDap_sql())) {
			sqlRequest.setReader_bean_name(JobBeanNames.SQL_CURSOR_READER);
			sqlRequest.setWriter_bean_name(JobBeanNames.DATARESULT_CSV_WRITER);
		} else {
			Result result = Result.newSuccess().with(me.sso.ti.commons.ResultCode.ValidRequestError);
			ModelAndView mv = createModelView("sql-edit", result);
			mv.addObject("req", request);
			return mv;
		}
		Result result = configService.updateSQL(sqlRequest);
		if (!result.isSuccess()) {
			ModelAndView mv = createModelView("sql-edit", result);
			mv.addObject(Success, true);
			mv.addObject("req", request);
			return mv;
		}
		SQL sql = result.getResponse(SQL.class);
		return new ModelAndView("redirect:/sql/detail/" + sql.getId());
	}

	@RequestMapping("/detail/{sql_id}")
	public ModelAndView detail(@PathVariable Long sql_id) {
		Result result = configService.getSQL(sql_id);
		ModelAndView mv = createModelView("sql-detail", result);
		return mv;
	}

	@RequestMapping("/list")
	public ModelAndView list(@Valid SQLQueryRequest request, BindingResult validResult) {
		QuerySQLRequest req = request.buildRequest();
		Result result = configService.querySQL(req);
		ModelAndView mv = createModelView("sql-list", result);
		mv.addObject("query", req.getQuery());
		return mv;
	}
}