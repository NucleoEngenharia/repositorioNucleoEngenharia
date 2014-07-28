package com.nucleo.dao.generic;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;

import com.nucleo.dao.LogAlteracaoDAO;
import com.nucleo.entity.log.LogAlteracao;

public class DAOImpl<T, K> implements DAO<T, K>{
	@EJB
	private Factor factor;
	
	protected static EntityManager em;
	private Class<T> entityClass;
	@PostConstruct
	private void init(){
		em = factor.getEntityManagerMedicaoControle();
	}
	@EJB
	LogAlteracaoDAO logAlteracaoDAO;

	@SuppressWarnings("all")
	public DAOImpl() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Override
	public void inserir(T entity, int usuario){
		try {
			entityClass.getMethod("setDataCriacao", Calendar.class).invoke(entity,
					Calendar.getInstance());
		} catch (Exception e) {}
		try {
			entityClass.getMethod("setUsuarioCriacao", Integer.TYPE).invoke(entity, usuario);
		} catch (Exception e) {}

		try {

			for (Field field : entity.getClass().getDeclaredFields()) {

				if (field.isAnnotationPresent(OneToOne.class)) {
					List<CascadeType> cascades = Arrays.asList(field.getAnnotation(OneToOne.class)
							.cascade());

					if (cascades.contains(CascadeType.ALL)
							|| cascades.contains(CascadeType.PERSIST)) {
						String strField = field.getName();
						String strMethod = "get" + strField.substring(0, 1).toUpperCase()
								+ strField.substring(1);
						Method method = null;
						try {
							method = entity.getClass().getMethod(strMethod);
							Object obj = method.invoke(entity);
							obj.getClass().getMethod("setDataCriacao", Calendar.class)
									.invoke(obj, Calendar.getInstance());
							obj.getClass().getMethod("setUsuarioCriacao", Integer.TYPE)
									.invoke(obj, usuario);
						} catch (NoSuchMethodException e) {}
						 catch (NullPointerException e) {}
					}
				}

			}

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} finally {
			em.persist(entity);
		}
	}

	@Override
	public void deletarPorId(K id, int usuario){
		T entity = em.find(entityClass, id);
		
		Method excluido = null;
		try {
			excluido = entityClass.getMethod("isExcluido");
		} catch (Exception e) {}

		if (excluido == null) {
			// deleta o registro
			em.remove(entity);
		} else {
			try {
				entity.getClass().getMethod("setUsuarioExclusao", Integer.TYPE).invoke(entity, usuario);
				entity.getClass().getMethod("setDataExclusao", Calendar.class).invoke(entity, Calendar.getInstance());
				entity.getClass().getMethod("setExcluido", Boolean.TYPE).invoke(entity, true);
				em.merge(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deletar(T entity, int usuario){
		
		Method excluido = null;
		try {
			excluido = entityClass.getMethod("isExcluido");
		} catch (Exception e) {}

		if (excluido == null) {
			// para for�ar a entidade ser gerenciada pelo em
			em.merge(entity);
			em.remove(entity);
		} else {
			try {
				entity.getClass().getMethod("setUsuarioExclusao", Integer.TYPE).invoke(entity, usuario);
				entity.getClass().getMethod("setDataExclusao", Calendar.class).invoke(entity, Calendar.getInstance());
				entity.getClass().getMethod("setExcluido", Boolean.TYPE).invoke(entity, true);
				em.merge(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public T buscarPorID(K id){
		return em.find(entityClass, id);
	}

	@Override
	public void alterar(T entity, int usuario){
		try {

			Method methodId = entity.getClass().getMethod("getId");
			int idRegistro = (int) methodId.invoke(entity);

			T objOld = (T) em.find(entityClass, idRegistro);

			for (Field field : entity.getClass().getDeclaredFields()) {

				if (field.isAnnotationPresent(OneToMany.class)
						|| field.isAnnotationPresent(ManyToMany.class)
						|| field.isAnnotationPresent(Transient.class)) {
					continue;
				}

				String strField = field.getName();
				String strMethod = "get" + strField.substring(0, 1).toUpperCase()
						+ strField.substring(1);
				Method method = null;
				try {
					method = entity.getClass().getMethod(strMethod);
				} catch (NoSuchMethodException e) {
					strMethod = "is" + strField.substring(0, 1).toUpperCase()
							+ strField.substring(1);
					try {
						method = entity.getClass().getMethod(strMethod);
					} catch (NoSuchMethodException e2) {
						continue;
					}
				}

				Object retNew = method.invoke(entity);
				Object retOld = method.invoke(objOld);
				if (retOld == null && retNew == null)
					continue;
				if (retOld == null && retNew == "" || retOld == "" && retNew == null)
					continue;

				if (field.isAnnotationPresent(ManyToOne.class)) {
					retOld = (int) entity.getClass().getMethod("getId").invoke(objOld);
					retNew = (int) entity.getClass().getMethod("getId").invoke(entity);

					if (retOld.equals(0)) {
						retNew.getClass().getMethod("setDataCriacao", Calendar.class)
								.invoke(retNew, Calendar.getInstance());
						retNew.getClass().getMethod("setUsuarioCriacao", Integer.TYPE)
								.invoke(retNew, usuario);
					}
					if (retNew.equals(0)) {
						retOld.getClass().getMethod("setDataExclusao", Calendar.class)
								.invoke(retOld, Calendar.getInstance());
						retOld.getClass().getMethod("setUsuarioExclusao", Integer.TYPE)
								.invoke(retOld, usuario);
					}
				}

				if ((retOld == null && retNew != null) || (retOld != null && retNew == null)
						|| (!retOld.equals(retNew))) {

					// Calendar
					if (retOld instanceof Calendar || retNew instanceof Calendar) {
						SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY hh:mm");
						if (retOld != null) {
							Calendar oldCal = (Calendar) retOld;
							retOld = format.format(oldCal.getTime());
						}
						if (retNew != null) {
							Calendar newCal = (Calendar) retNew;
							retNew = format.format(newCal.getTime());
						}
					}

					if (field.isAnnotationPresent(OneToOne.class)) {
						/*retOld = (String) retOld.getClass().getMethod("identificationToLog")
								.invoke(retOld);
						retNew = (String) retNew.getClass().getMethod("identificationToLog")
								.invoke(retNew);
							*/
						try{
						if (retOld.equals(0)||retOld.equals(null)) {
							retNew.getClass().getMethod("setDataCriacao", Calendar.class)
									.invoke(retNew, Calendar.getInstance());
							retNew.getClass().getMethod("setUsuarioCriacao", Integer.class)
									.invoke(retNew, usuario);
						}
						if (retNew.equals(0)||retNew.equals(null)) {
							retOld.getClass().getMethod("setDataExclusao", Calendar.class)
									.invoke(retOld, Calendar.getInstance());
							retOld.getClass().getMethod("setUsuarioExclusao", Integer.TYPE)
									.invoke(retOld, usuario);
						}
						}catch (NullPointerException e) {}
					}

					LogAlteracao log = new LogAlteracao();
					log.setTabela(entity.getClass().getName());
					log.setColuna(method.getName().replace("get", ""));
					log.setIdRegistro(idRegistro);
					log.setValorAntigo((retOld != null) ? retOld.toString() : null);
					log.setValorNovo((retNew != null) ? retNew.toString() : null);
					log.setDataAlteracao(Calendar.getInstance());
					log.setUsuarioAlteracao(usuario);
					registraLog(log);
				}
			}
			em.merge(entity);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	private void registraLog(LogAlteracao log){
		logAlteracaoDAO.inserir(log);
	}

	@Override
	public List<T> buscarTodos(){
		String strQuery = "Select u from " + entityClass.getName() + " u ";
		TypedQuery<T> query = null;
		Method excluido = null;

		try {
			excluido = entityClass.getMethod("isExcluido");
		} catch (Exception e) {}

		if (excluido == null) {
			query = em.createQuery(strQuery, entityClass);
		} else {
			strQuery = strQuery + " Where u.excluido = :param ";
			query = em.createQuery(strQuery, entityClass);
			query.setParameter("param", false);
		}

		return query.getResultList();
	}

}
