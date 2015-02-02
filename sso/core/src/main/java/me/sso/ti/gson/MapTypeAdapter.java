package me.sso.ti.gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月30日 上午11:49:45
 */
@SuppressWarnings("rawtypes")
public class MapTypeAdapter extends TypeAdapter<Map> {

	private final TypeAdapter<Map> defaultMapTypeAdapter;
	
	public MapTypeAdapter(TypeAdapter<Map> defaultMapTypeAdapter) {
		super();
		this.defaultMapTypeAdapter = defaultMapTypeAdapter;
	}

	@Override
	public void write(JsonWriter out, Map value) throws IOException {
		if(value == null) {
			value = new HashMap<Object, Object>();
		}
		defaultMapTypeAdapter.write(out, value);
	}

	@Override
	public Map read(JsonReader in) throws IOException {
		return defaultMapTypeAdapter.read(in);
	}
}