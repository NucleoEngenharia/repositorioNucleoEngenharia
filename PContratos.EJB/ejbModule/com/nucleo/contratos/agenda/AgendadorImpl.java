package com.nucleo.contratos.agenda;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.TimerService;

import com.nucleo.contratos.dao.AtividadeDAO;
import com.nucleo.contratos.dao.FuncionarioDAO;
import com.nucleo.contratos.email.HorariosEmail;
import com.nucleo.contratos.entity.Atividades;
import com.nucleo.contratos.entity.DetalhamentoAtividade;
import com.nucleo.contratos.entity.Funcionario;
@Stateless
public class AgendadorImpl implements Agendador {
	@EJB
	private FuncionarioDAO funcionarioDAO;
	@EJB
	private AtividadeDAO atividadeDAO;
	
	private List<Funcionario>funcionarios;
	@Resource
	private TimerService timerService;
	
	private StringBuilder pagina;
	
	private Atividades atividade;
	
	private HorariosEmail email;
	
	@Override
	@Schedule(hour="19",minute="00")
	public void agenda() {
		email = new HorariosEmail();
		email.enviarHorariosEmail(geraRelatorioDeAtividade());
	}
	
	public String geraRelatorioDeAtividade(){
		pagina = new StringBuilder();
		pagina.append("<html>");
		pagina.append("<body>");
		pagina.append("<table>");
		funcionarios = funcionarioDAO.listarTodos();
		
		SimpleDateFormat hora = new SimpleDateFormat("hh:MM:ss");
		for(Funcionario f: funcionarios){
			atividade = atividadeDAO.buscarPorDataEFuncionario(Calendar.getInstance(), f);
			pagina.append("<tr>");
			pagina.append("<th>Nome</th>");
			pagina.append("<th>Matricula</th>");
			pagina.append("<th>CN</th>");
			pagina.append("<th>Login</th>");
			pagina.append("</tr>");
			
			pagina.append("<tr>");
			pagina.append("<td>"+f.getNome()+"</td>");
			pagina.append("<td>"+f.getMatricula()+"</td>");
			pagina.append("<td>"+f.getCn()+"</td>");
			pagina.append("<td>"+hora.format(atividade.getDataCriacao().getTime())+"</td>");
			pagina.append("</tr>");
					for(DetalhamentoAtividade da: atividade.getDetalhamentoAtividade()){
						pagina.append("<tr>");
						pagina.append("<td>"+da.getTexto()+"</td>");
						pagina.append("<td>"+hora.format(da.getHora().getTime())+"</td>");
						pagina.append("</tr>");
					}
		}
		pagina.append("</table>");
		pagina.append("</body>");
		pagina.append("</html>");
		return pagina.toString();
	}

}
