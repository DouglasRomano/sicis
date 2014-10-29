package sicis.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
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

   public <T> List<T> searchObject(Class<T> object, String[] propertyNames, Object[] valores) throws Exception {
      List<T> listObjectReturn;
      Session sessao = HibernateUtil.getSession();
      Criteria consultar = sessao.createCriteria(object);

      for (int x = 0; x < propertyNames.length; x++) {
         if (valores[x] != null) {
            if (valores[x] instanceof String) {
               if (!(valores[x].equals(""))) {
                  consultar.add(Restrictions.like(propertyNames[x], "%" +valores[x]+ "%"));
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

}
