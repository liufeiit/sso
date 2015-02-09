package me.sso.ti.gson;

import java.util.List;
import java.util.Map;

import me.sso.ti.vo.ArticleVO;
import me.sso.ti.vo.StyleVO;
import me.sso.ti.vo.UserVO;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月30日 下午12:01:41
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SmartTypeAdapterFactory implements TypeAdapterFactory {

	@Override
	public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
		Class<? super T> rawType = type.getRawType();
		if(String.class == rawType) {
			return (TypeAdapter<T>) new StringTypeAdapter((TypeAdapter<String>) gson.getDelegateAdapter(this, type));
		}
		if(Number.class.isAssignableFrom(rawType)) {
			return (TypeAdapter<T>) new NumberTypeAdapter((TypeAdapter<Number>) gson.getDelegateAdapter(this, type));
		}
		if(Boolean.class == rawType) {
			return (TypeAdapter<T>) new BooleanTypeAdapter((TypeAdapter<Boolean>) gson.getDelegateAdapter(this, type));
		}
		if (Map.class == rawType)  {
			return (TypeAdapter<T>) new MapTypeAdapter((TypeAdapter<Map>) gson.getDelegateAdapter(this, type));
		}
		if (List.class == rawType) {
			return (TypeAdapter<T>) new ListTypeAdapter((TypeAdapter<List>) gson.getDelegateAdapter(this, type));
		}
		if(rawType.isArray()) {
			return (TypeAdapter<T>) new ArrayTypeAdapter((TypeAdapter<Object[]>) gson.getDelegateAdapter(this, type));
		}
		if(UserVO.class == rawType) {
			return (TypeAdapter<T>) new ObjectTypeAdapter((TypeAdapter<UserVO>) gson.getDelegateAdapter(this, type), UserVO.class);
		}
		if(ArticleVO.class == rawType) {
			return (TypeAdapter<T>) new ObjectTypeAdapter((TypeAdapter<ArticleVO>) gson.getDelegateAdapter(this, type), ArticleVO.class);
		}
		if(StyleVO.class == rawType) {
			return (TypeAdapter<T>) new ObjectTypeAdapter((TypeAdapter<StyleVO>) gson.getDelegateAdapter(this, type), StyleVO.class);
		}
		return null;
	}
}