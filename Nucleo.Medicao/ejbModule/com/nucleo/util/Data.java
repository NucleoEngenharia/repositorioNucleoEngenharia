package com.nucleo.util;

import java.util.Calendar;
import java.util.Date;

public class Data {

	public static int diferencaEntreDatas(Date dataInicial, Date dataFinal) {
		int MILLIS_IN_DAY = 86400000;  
		  
        Calendar c1 = Calendar.getInstance();  
        c1.setTime(dataFinal);  
        c1.set(Calendar.MILLISECOND, 0);  
        c1.set(Calendar.SECOND, 0);  
        c1.set(Calendar.MINUTE, 0);  
        c1.set(Calendar.HOUR_OF_DAY, 0);  
  
        Calendar c2 = Calendar.getInstance();  
        c2.setTime(dataInicial);  
        c2.set(Calendar.MILLISECOND, 0);  
        c2.set(Calendar.SECOND, 0);  
        c2.set(Calendar.MINUTE, 0);  
        c2.set(Calendar.HOUR_OF_DAY, 0);  
        return (int) ((c1.getTimeInMillis() - c2.getTimeInMillis()) / MILLIS_IN_DAY); 
	}

}
