package me.sso.ti.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.sso.ti.WebBase;
import me.sso.ti.result.Result;

import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年2月9日 下午1:35:29
 */
@Controller
@RequestMapping(value = "/gzip")
public class GzipController extends WebBase {

	@RequestMapping(value = "/{gzipId}")
	public void gzip(@PathVariable Long gzipId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Result result = gzipService.getGzip(gzipId);
		if (!result.isSuccess()) {
			return;
		}
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			File gzipFile = (File) result.get("gzipFile");
			request.setCharacterEncoding("UTF-8");
			long fileLength = gzipFile.length();
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-disposition", "attachment; filename=" + gzipFile.getName());
			response.setHeader("Content-Length", String.valueOf(fileLength));
			response.setContentLength((int) fileLength);
			in = new BufferedInputStream(new FileInputStream(gzipFile));
			out = new BufferedOutputStream(response.getOutputStream());
			StreamUtils.copy(in, out);
		} finally {
			if (in != null) in.close();
			if (out != null) out.close();
		}
	}
}