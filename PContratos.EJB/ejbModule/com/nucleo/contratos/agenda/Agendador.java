package com.nucleo.contratos.agenda;

import javax.ejb.Remote;

@Remote
public interface Agendador {
	void agenda();
}
