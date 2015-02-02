package me.sso.ti.gson;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月30日 下午1:37:39
 */
public class BooleanTypeAdapter extends TypeAdapter<Boolean> {

	private final TypeAdapter<Boolean> defaultBooleanTypeAdapter;
	
	public BooleanTypeAdapter(TypeAdapter<Boolean> defaultBooleanTypeAdapter) {
		super();
		this.defaultBooleanTypeAdapter = defaultBooleanTypeAdapter;
	}

	@Override
	public void write(JsonWriter out, Boolean value) throws IOException {
		if(value == null) {
			value = Boolean.FALSE;
		}
		defaultBooleanTypeAdapter.write(out, value);
	}

	@Override
	public Boolean read(JsonReader in) throws IOException {
		return defaultBooleanTypeAdapter.read(in);
	}
}
