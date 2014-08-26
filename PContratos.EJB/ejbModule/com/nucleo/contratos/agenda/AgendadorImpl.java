package com.nucleo.contratos.agenda;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.TimerService;

public class AgendadorImpl implements Agendador {
	
	@Resource
	private TimerService timerService;

	@Override
	@Schedule(hour="18",minute="0")
	public void agenda() {
		System.out.println("Executou o teste das 18:00");
	}

}
