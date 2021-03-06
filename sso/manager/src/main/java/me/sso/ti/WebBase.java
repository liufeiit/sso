package me.sso.ti;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import me.sso.ti.mime.ActivationMediaTypeFactory;
import me.sso.ti.result.Result;
import me.sso.ti.srv.AccountService;
import me.sso.ti.srv.ArticleService;
import me.sso.ti.srv.FavoriteService;
import me.sso.ti.srv.GzipService;
import me.sso.ti.srv.ImageService;
import me.sso.ti.srv.StyleService;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年3月26日 下午4:07:31
 */
public class WebBase implements ServletContextAware {

	private static final boolean jafPresent = ClassUtils.isPresent("javax.activation.FileTypeMap", WebBase.class.getClassLoader());
	
	protected Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("accountService")
	protected AccountService accountService;

	@Autowired
	@Qualifier("articleService")
	protected ArticleService articleService;

	@Autowired
	@Qualifier("styleService")
	protected StyleService styleService;

	@Autowired
	@Qualifier("favoriteService")
	protected FavoriteService favoriteService;

	@Autowired
	@Qualifier("imageService")
	protected ImageService imageService;

	@Autowired
	@Qualifier("gzipService")
	protected GzipService gzipService;

	@Autowired
	@Qualifier("multipartResolver")
	protected CommonsMultipartResolver multipartResolver;

	protected static final String message = "message";
	protected static final String resultcode = "resultcode";
	protected static final String data = "data";
	protected static final String success = "success";
	private static final String viewname = "viewname";

	protected String decoder(String val) {
		if (StringUtils.isBlank(val)) {
			return val;
		}
		try {
			return URLDecoder.decode(val, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			log.error("URLDecoder Error.", ex);
		}
		return val;
	}
	
	protected void out(Result result, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		json.accumulate("code", result.getResultCode());
		json.accumulate("message", result.getMessage());
		json.accumulate("data", result.getData());
		PrintWriter w = null;
		try {
			response.setContentType("application/json;charset=UTF-8");
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

	protected void returnJson(HttpServletResponse response, JSONObject json) {
		PrintWriter out = null;
		try {
			response.setContentType("application/json; charset=UTF-8");
			out = response.getWriter();
			out.write(json.toString());
			out.flush();
		} catch (IOException e) {
			log.error("Return JSON Error.", e);
		} finally {
			if (out != null) {
				out.close();
				out = null;
			}
		}
	}

	protected ModelAndView createModelView(String viewName) {
		ModelAndView mv = new ModelAndView(viewName);
		commons(mv);
		return mv;
	}

	protected ModelAndView createModelView(String viewName, Result result) {
		ModelAndView mv = new ModelAndView(viewName);
		commons(mv);
		mv.addAllObjects(result.getData());
		mv.addObject(data, result.getResponse());
		mv.addObject(message, result.getMessage());
		mv.addObject(resultcode, result.getResultCode());
		mv.addObject(success, result.isSuccess());
		return mv;
	}

	protected ModelAndView merge(ModelAndView mv, Result result) {
		mv.addAllObjects(result.getData());
		mv.addObject(data, result.getResponse());
		mv.addObject(message, result.getMessage());
		mv.addObject(resultcode, result.getResultCode());
		mv.addObject(success, result.isSuccess());
		return mv;
	}

	private void commons(ModelAndView mv) {
		mv.addObject("cdn", "/assets/");
		String name = mv.getViewName();
		mv.addObject(viewname, name);
	}

	protected MediaType getMediaType(String fileName) {
		MediaType mediaType = null;
		String mimeType = getServletContext().getMimeType(fileName);
		if (org.springframework.util.StringUtils.hasText(mimeType)) {
			mediaType = MediaType.parseMediaType(mimeType);
		}
		if (jafPresent && (mediaType == null || MediaType.APPLICATION_OCTET_STREAM.equals(mediaType))) {
			MediaType jafMediaType = ActivationMediaTypeFactory.getMediaType(fileName);
			if (jafMediaType != null && !MediaType.APPLICATION_OCTET_STREAM.equals(jafMediaType)) {
				mediaType = jafMediaType;
			}
		}
		return new MediaType(mediaType.getType(), mediaType.getSubtype());
	}

	private ServletContext servletContext;

	public ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}