package me.sso.ti.gson;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月30日 下午12:46:53
 */
public class NumberTypeAdapter extends TypeAdapter<Number> {

	private final TypeAdapter<Number> defaultNumbeTypeAdapter;
	
	public NumberTypeAdapter(TypeAdapter<Number> defaultNumbeTypeAdapter) {
		super();
		this.defaultNumbeTypeAdapter = defaultNumbeTypeAdapter;
	}

	@Override
	public void write(JsonWriter out, Number value) throws IOException {
		if(value == null) {
			value = 0;
		}
		defaultNumbeTypeAdapter.write(out, value);
	}

	@Override
	public Number read(JsonReader in) throws IOException {
		return defaultNumbeTypeAdapter.read(in);
	}

}