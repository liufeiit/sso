package me.sso.ti.utils;

import java.io.File;

import me.ocs.commons.response.json.JSONResponseBody;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年2月10日 下午5:35:43
 */
public class RestUtils {
	
	public static void main(String[] args) {
//		String uri = "http://121.42.44.23/image/upload";
		String uri = "http://121.42.44.23/article/list";
		LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
		parameters.add("image", new File("/Users/yp/2014-05-24-17165900.jpg"));
		JSONResponseBody response = postForObject(uri, parameters, JSONResponseBody.class);
		System.out.println(response.toJson());
	}
	
	public static <T> T postForObject(String uri, LinkedMultiValueMap<String, Object> parameters, Class<T> retClazz) {
		RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
		return restTemplate.postForObject(uri, parameters, retClazz);
	}
}