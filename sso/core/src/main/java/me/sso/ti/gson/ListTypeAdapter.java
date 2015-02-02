package me.sso.ti.gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月30日 上午11:59:17
 */
@SuppressWarnings("rawtypes")
public class ListTypeAdapter extends TypeAdapter<List> {
	
	private final TypeAdapter<List> defaultListTypeAdapter;

	public ListTypeAdapter(TypeAdapter<List> defaultListTypeAdapter) {
		super();
		this.defaultListTypeAdapter = defaultListTypeAdapter;
	}

	@Override
	public void write(JsonWriter out, List value) throws IOException {
		if(value == null) {
			value = new ArrayList();
		}
		defaultListTypeAdapter.write(out, value);
	}

	@Override
	public List read(JsonReader in) throws IOException {
		return defaultListTypeAdapter.read(in);
	}
}