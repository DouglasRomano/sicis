package sicis.bean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

import org.springframework.security.core.context.SecurityContextHolder;
import sicis.dao.GenericDAO;
import sicis.domain.Profissional;

@ManagedBean(name = "loginEfetuadoMB")
@ViewScoped
public class LoginEfetuadoMB implements Serializable {

  private static final long serialVersionUID = 1L;

  private Profissional user;
  private String userRede;
  private String novaSenha;

  private String senhaAntiga;
  private String renderizarSenhaAtual;
  private String validatorSenha;

  private GenericDAO genericDAO;

  private transient FacesContext facesContext;

  @PostConstruct
  public void loginEfetuado() {
    userRede = SecurityContextHolder.getContext().getAuthentication().getName();
    genericDAO = new GenericDAO();

    limparInputs();
    verificarUsuario();
    verificarSenhaEmBranco();
  }

  private void limparInputs() {
    validatorSenha = new String();
    novaSenha = new String();
    senhaAntiga = new String();
  }

  public void verificarUsuario() {
    List<Profissional> listProfissional;
    try {
      listProfissional = genericDAO.searchObject(Profissional.class,"profissional",
              null, null,
              new String[]{"pro_login"}, new Object[]{userRede});
      user = listProfissional.get(0);
    } catch (Exception ex) {
      Logger.getLogger(LoginEfetuadoMB.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  public void verificarSenhaEmBranco() {
    facesContext = FacesContext.getCurrentInstance();

    if (user.getPro_senha().equals("123")) {
      renderizarSenhaAtual = "true";
      RequestContext.getCurrentInstance().execute("PF('dialogSenha').show();");

    } else {
      renderizarSenhaAtual = "false";
    }
  }

  public void alterarSenha() {
    if ((user.getPro_senha().equals(senhaAntiga) && novaSenha.equals(validatorSenha)) || user.getPro_senha().equals("123")) {
      saveNovaSenha(novaSenha);
    } else {
      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Senha atual não coincidente", "A senha atual informada não confere!"));
    }
  }

  private void saveNovaSenha(String senha) {
    facesContext = FacesContext.getCurrentInstance();
    user.setPro_senha(senha);

    try {
      genericDAO.update(user);
      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Senha alterada", "Sua nova senha foi salva com sucesso e já esta em vigor"));
      limparInputs();
    } catch (Exception ex) {
      Logger.getLogger(LoginEfetuadoMB.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public Profissional getUser() {
    return user;
  }

  public void setUser(Profissional user) {
    this.user = user;
  }

  public String getRenderizarSenhaAtual() {
    return renderizarSenhaAtual;
  }

  public void setRenderizarSenhaAtual(String renderizarSenhaAtual) {
    this.renderizarSenhaAtual = renderizarSenhaAtual;
  }

  public String getSenhaAntiga() {
    return senhaAntiga;
  }

  public void setSenhaAntiga(String senhaAntiga) {
    this.senhaAntiga = senhaAntiga;
  }

  public String getNovaSenha() {
    return novaSenha;
  }

  public void setNovaSenha(String novaSenha) {
    this.novaSenha = novaSenha;
  }

  public String getValidatorSenha() {
    return validatorSenha;
  }

  public void setValidatorSenha(String validatorSenha) {
    this.validatorSenha = validatorSenha;
  }

}
