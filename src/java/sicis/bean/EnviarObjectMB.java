package sicis.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sicis.domain.Cliente;
import sicis.domain.SolicitacoesExames;

@ManagedBean(name = "enviarObjectMB",eager = true)
@SessionScoped
public class EnviarObjectMB implements Serializable{
  
  private Cliente cliente;
  private List<SolicitacoesExames> listSolicitacoes;
  
  @PostConstruct
  public void enviarObjeto(){
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public List<SolicitacoesExames> getListSolicitacoes() {
    return listSolicitacoes;
  }

  public void setListSolicitacoes(List<SolicitacoesExames> listSolicitacoes) {
    this.listSolicitacoes = listSolicitacoes;
  }

 
  
  
}
