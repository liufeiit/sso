package me.sso.ti.gson;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月30日 下午12:08:16
 */
public class StringTypeAdapter extends TypeAdapter<String> {

	private final TypeAdapter<String> defaultStringTypeAdapter;
	
	public StringTypeAdapter(TypeAdapter<String> defaultStringTypeAdapter) {
		super();
		this.defaultStringTypeAdapter = defaultStringTypeAdapter;
	}

	@Override
	public void write(JsonWriter out, String value) throws IOException {
		if(StringUtils.isBlank(value)) {
			value = StringUtils.EMPTY;
		}
		defaultStringTypeAdapter.write(out, value);
	}

	@Override
	public String read(JsonReader in) throws IOException {
		return defaultStringTypeAdapter.read(in);
	}

}