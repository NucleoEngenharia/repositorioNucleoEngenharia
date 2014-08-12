package com.nucleo.intranet.DAO;

import java.util.List;

import com.nucleo.seguranca.to.MenuTO;

public interface MenuDAO {
	public static final String MENU_CONTRATOS="menu_contratos";
	public static final String MENU_MEDICAO="menu_medicao";
	List<MenuTO> listarMenusFilhos(String coluna, String bitMenuPai);
	List<MenuTO> listarTodosMenus(String sistema);
}
