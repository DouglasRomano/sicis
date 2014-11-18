package sicis.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.Alias;
import sicis.domain.DescricaoExames;
import sicis.util.HibernateUtil;

public class GenericDAO implements Serializable {

  private static final long serialVersionUID = 1L;

  public void save(Object object) throws Exception {

    Session sessao = HibernateUtil.getSession();
    Transaction transacao = sessao.beginTransaction();
    sessao.save(object);
    transacao.commit();
    sessao.close();
  }

  public void update(Object object) throws Exception {

    Session sessao = HibernateUtil.getSession();
    Transaction transacao = sessao.beginTransaction();
    sessao.update(object);
    transacao.commit();
    sessao.close();
  }

  public void delete(Object object) throws Exception {

    Session sessao = HibernateUtil.getSession();
    Transaction transacao = sessao.beginTransaction();
    sessao.delete(object);
    transacao.commit();
    sessao.close();
  }

  public <T> List<T> listAll(Class<T> object) throws Exception {

    List<T> listObjectReturn;
    Session sessao = HibernateUtil.getSession();
    Criteria consultar = sessao.createCriteria(object);
    listObjectReturn = consultar.list();
    sessao.close();
    return listObjectReturn;
  }

  public <T> Object searchById(Class<T> object, Long id) throws Exception {

    Object objectReturn;
    Session sessao = HibernateUtil.getSession();
    Criteria consultar = sessao.createCriteria(object);
    consultar.add(Restrictions.idEq(id));
    objectReturn = consultar.uniqueResult();
    sessao.close();
    return objectReturn;
  }

  public <T> List<T> searchObject(Class<T> object, String objectName, String[] caminhos, String[] colunas, String[] propertyNames, Object[] valores) throws Exception {
    List<T> listObjectReturn;
    Session sessao = HibernateUtil.getSession();
    Criteria consultar = sessao.createCriteria(object, objectName);

    if (caminhos != null) {
      for (int x = 0; x < caminhos.length; x++) {
        consultar.createAlias(caminhos[x], colunas[x]);
      }
    }

    for (int x = 0; x < propertyNames.length; x++) {
      if (valores[x] != null) {
        if (valores[x] instanceof String) {
          if (!(valores[x].equals(""))) {
            consultar.add(Restrictions.like(propertyNames[x], "%" + valores[x] + "%"));
          }
        } else {
          consultar.add(Restrictions.like(propertyNames[x], valores[x]));
        }
      }
    }
    listObjectReturn = consultar.list();
    sessao.close();
    return listObjectReturn;
  }

  public <T> Integer searchLastId(Class<T> object, String propertyID, String tabela) throws Exception {

    Integer idResult;
    Session sessao = HibernateUtil.getSession();
    SQLQuery query = sessao.createSQLQuery("select Max(" + propertyID + ") from " + tabela);
    idResult = (Integer) query.uniqueResult();
    if (idResult == null) {
      idResult = 0;
    }
    idResult += 1;
    sessao.close();
    return idResult;
  }

  public <T> List<T> searchCustom(String sql) throws Exception {
    List<T> lista;
    Session sessao = HibernateUtil.getSession();
    SQLQuery query = sessao.createSQLQuery(sql);
    lista = query.list();
    sessao.close();
    return lista;
  }

  // dedicado
  public int ContarProcedimento(DescricaoExames procedimento) throws Exception {
    int contador = 0;
    Session sessao = HibernateUtil.getSession();
    SQLQuery query = sessao.createSQLQuery("SELECT COUNT(*) FROM examesautorizados autorizados "
            + "INNER JOIN solicitacaoexames solicitacao "
            + "ON autorizados.aut_solExa_id = solicitacao.solExa_id "
            + "INNER JOIN descricaoexames procedimento "
            + "ON solicitacao.solExa_des_id = procedimento.des_id "
            + "WHERE procedimento.des_codigoProcedimento = '" + procedimento.getDes_codigoProcedimento() + "'");
    Object resultado = query.uniqueResult();
    if (resultado != null) {
      contador = Integer.parseInt(resultado.toString());
    }
    sessao.close();
    return contador;
  }

}
