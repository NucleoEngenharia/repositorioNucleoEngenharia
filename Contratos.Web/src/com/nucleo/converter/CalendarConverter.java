package com.nucleo.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="CalendarConverter")
public class CalendarConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String dataString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar date = Calendar.getInstance();
		try {
			date.setTime(sdf.parse(dataString));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object dateCalendar) {
		String dateString="";
		if(dateCalendar!=null && dateCalendar instanceof GregorianCalendar){
			Calendar date = (Calendar) dateCalendar;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dateString = sdf.format(date.getTime());
		}
		return dateString;
	}

}
