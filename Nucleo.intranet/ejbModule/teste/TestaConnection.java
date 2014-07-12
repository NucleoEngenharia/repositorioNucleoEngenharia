package teste;

import java.sql.Connection;

import javax.ejb.EJB;

import com.nucleo.intranet.FactoringConnection.Factor;

public class TestaConnection {

	private static Connection conn = null;
	@EJB
	private Factor factor;
	public TestaConnection() {
	}
	public static void main(String[] args) {
		
		System.out.println("A conexao é: "+conn);
	}

}
