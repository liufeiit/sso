package me.sso.ti.controller;

import javax.validation.Valid;

import me.sso.ti.result.Result;
import me.sso.ti.result.ResultCode;
import me.sso.ti.ro.PushTokenRequest;
import me.sso.ti.ro.UserRequest;

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
@RequestMapping(value = "/account")
public class AccountController extends BaseController {
	
	@RequestMapping(value = "/register", produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> register(@Valid UserRequest request, BindingResult validResult) {
		if (validResult.hasErrors()) {
			return toResponse(Result.newError().with(ResultCode.Error_Valid_Request));
		}
		Result result = accountService.register(request);
		return toResponse(result);
	}
	
	@RequestMapping(value = "/token", produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> token(@Valid PushTokenRequest request, BindingResult validResult) {
		if (validResult.hasErrors()) {
			return toResponse(Result.newError().with(ResultCode.Error_Valid_Request));
		}
		Result result = accountService.token(request);
		return toResponse(result);
	}

	@RequestMapping(value = "/login", produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> login(@Valid UserRequest request, BindingResult validResult) {
		if (validResult.hasErrors()) {
			return toResponse(Result.newError().with(ResultCode.Error_Valid_Request));
		}
		Result result = accountService.login(request);
		return toResponse(result);
	}
}