package me.sso.ti.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import me.sso.ti.http.JSONResponseBody;
import me.sso.ti.result.Result;
import me.sso.ti.result.ResultCode;
import me.sso.ti.srv.AccountService;
import me.sso.ti.srv.ArticleService;
import me.sso.ti.srv.FavoriteService;
import me.sso.ti.srv.GzipService;
import me.sso.ti.srv.ImageService;
import me.sso.ti.srv.StyleService;
import me.sso.ti.utils.CalendarUtils;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年12月27日 下午2:38:54
 */
public class BaseController {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier(value = "accountService")
	protected AccountService accountService;
	
	@Autowired
	@Qualifier(value = "articleService")
	protected ArticleService articleService;
	
	@Autowired
	@Qualifier(value = "styleService")
	protected StyleService styleService;
	
	@Autowired
	@Qualifier(value = "favoriteService")
	protected FavoriteService favoriteService;
	
	@Autowired
	@Qualifier(value = "imageService")
	protected ImageService imageService;
	
	@Autowired
	@Qualifier(value = "gzipService")
	protected GzipService gzipService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(CalendarUtils.S_YYYY_MM_DD_HH_MM_SS);
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public final ResponseEntity<String> handleException(final Throwable ex) {
		log.error("Api Request Error.", ex);
		return toResponse(Result.newError().with(ResultCode.Err_Exception), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	protected ResponseEntity<String> toResponse(Result result) {
		return JSONResponseBody.newInstance().code(result.getResultCode()).message(result.getMessage())
				.with(result.getData()).toResponse();
	}
	
	protected ResponseEntity<String> toResponse(Result result, HttpStatus status) {
		return JSONResponseBody.newInstance().code(result.getResultCode()).message(result.getMessage())
				.with(result.getData()).toResponse(status);
	}
	
	protected void out(Result result, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		json.accumulate("success", result.isSuccess());
		json.accumulate("resultCode", result.getResultCode());
		json.accumulate("message", result.getMessage());
		json.accumulate("data", result.getData());
		PrintWriter w = null;
		try {
			w = response.getWriter();
			w.write(json.toString());
			w.flush();
		} catch (Exception e) {
			log.error("Response JSON Error. ", e);
		} finally {
			if(w != null) {
				w.close();
			}
		}
	}
}