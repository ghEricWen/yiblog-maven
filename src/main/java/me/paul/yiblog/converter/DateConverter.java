package me.paul.yiblog.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class DateConverter extends StrutsTypeConverter{

	@Override
	public Object convertFromString(Map map, String[] strs, Class c) {
		String str = strs[0];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date result = null;
		try {
			result = sdf.parse(str);
		} catch (ParseException e) {
		}
		return result;
	}

	@Override
	public String convertToString(Map map, Object object) {
		Date date = (Date) object ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(date);
	}

}
