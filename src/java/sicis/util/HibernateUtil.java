package sicis.util;

import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import sicis.domain.Bairro;
import sicis.domain.Cidade;
import sicis.domain.Cliente;
import sicis.domain.DescricaoExames;
import sicis.domain.Logradouro;
import sicis.domain.Permissao;
import sicis.domain.Profissional;
import sicis.domain.Prontuario;
import sicis.domain.Unidade;

public class HibernateUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	private static SessionFactory sessionFactory;

	private static SessionFactory getSessionFactory() throws Exception {
		if (sessionFactory == null) {
			Configuration configuration = new Configuration().configure();
			configuration.addAnnotatedClass(Bairro.class)
					.addAnnotatedClass(Cidade.class)
					.addAnnotatedClass(Cliente.class)
					.addAnnotatedClass(Logradouro.class)
					.addAnnotatedClass(Permissao.class)
					.addAnnotatedClass(Unidade.class)
					.addAnnotatedClass(DescricaoExames.class)
					.addAnnotatedClass(Prontuario.class)
					.addAnnotatedClass(Profissional.class);
			StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
			sb.applySettings(configuration.getProperties());
			StandardServiceRegistry standardServiceRegistry = sb.build();
			sessionFactory = configuration
					.buildSessionFactory(standardServiceRegistry);
		}
		return sessionFactory;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		HibernateUtil.sessionFactory = sessionFactory;
	}

	public static Session getSession() throws Exception {
		Session sessaoAtual = null;
		sessaoAtual = getSessionFactory().openSession();
		return sessaoAtual;
	}

}
