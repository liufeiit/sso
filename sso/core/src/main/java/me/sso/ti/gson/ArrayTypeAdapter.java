package me.sso.ti.gson;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月30日 下午1:09:21
 */
public class ArrayTypeAdapter extends TypeAdapter<Object[]> {

	private final TypeAdapter<Object[]> defaultArrayTypeAdapter;
	
	public ArrayTypeAdapter(TypeAdapter<Object[]> defaultArrayTypeAdapter) {
		super();
		this.defaultArrayTypeAdapter = defaultArrayTypeAdapter;
	}

	@Override
	public void write(JsonWriter out, Object[] value) throws IOException {
		if(value == null) {
			value = new Object[]{};
		}
		defaultArrayTypeAdapter.write(out, value);
	}

	@Override
	public Object[] read(JsonReader in) throws IOException {
		return defaultArrayTypeAdapter.read(in);
	}
}