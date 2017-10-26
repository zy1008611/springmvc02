package com.zhidi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
/**
 * 日期转换器
 * @author Administrator
 *
 */
public class DateConverter implements Converter<String, Date>{

	/**
	 * 将String 类型的时间传入，返回Date类型的时间
	 */
	@Override
	public Date convert(String arg0) {
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(arg0);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
