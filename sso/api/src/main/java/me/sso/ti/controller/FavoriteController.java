package me.sso.ti.controller;

import javax.validation.Valid;

import me.sso.ti.result.Result;
import me.sso.ti.result.ResultCode;
import me.sso.ti.ro.FavoriteRequest;
import me.sso.ti.ro.PageRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年12月27日 下午2:36:45
 */
@Controller
@RequestMapping(value = "/rel")
public class FavoriteController extends BaseController {
	
	@RequestMapping(value = "/fav", produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> do_fav(@Valid FavoriteRequest request, BindingResult validResult) {
		if (validResult.hasErrors()) {
			return toResponse(Result.newError().with(ResultCode.Error_Valid_Request));
		}
		Result result = favoriteService.doFavorite(request);
		return toResponse(result);
	}
	
	@RequestMapping(value = "/fav/cancel", produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> do_cancel_fav(@Valid FavoriteRequest request, BindingResult validResult) {
		if (validResult.hasErrors()) {
			return toResponse(Result.newError().with(ResultCode.Error_Valid_Request));
		}
		Result result = favoriteService.doCancel(request);
		return toResponse(result);
	}

	@RequestMapping(value = "/fav/list", produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> fav_list(@Valid PageRequest request, BindingResult validResult) {
		Result result = favoriteService.favList(request);
		return toResponse(result);
	}
}