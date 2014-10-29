package sicis.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.security.core.context.SecurityContextHolder;


@ManagedBean(name="loginEfetuadoMB")
@ViewScoped
public class LoginEfetuadoMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String user;
	
	@PostConstruct
	public void loginEfetuado(){
		verificarUsuario();
	}
	
	public void verificarUsuario(){
		user = SecurityContextHolder.getContext().getAuthentication().getName();
	}

	public java.lang.String getUser() {
		return user;
	}

	public void setUser(java.lang.String user) {
		this.user = user;
	}

}
