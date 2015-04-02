package me.sso.ti.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.sso.ti.WebBase;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年3月26日 下午4:01:37
 */
public class GlobalExceptionHandler extends WebBase implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		log.error("Handler : " + handler + " Error.", ex);
		ModelAndView mv = createModelView("error");
		mv.addObject("Status", 500);
		mv.addObject(Message, ex.getLocalizedMessage());
		return mv;
	}
}