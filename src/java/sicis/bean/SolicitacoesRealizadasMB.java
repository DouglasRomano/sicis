package sicis.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import sicis.domain.SolicitacoesExames;

@ManagedBean
@ViewScoped
public class SolicitacoesRealizadasMB implements Serializable {

  @ManagedProperty("#{enviarObjectMB}")
  private EnviarObjectMB enviarObjectMB;

  private List<SolicitacoesExames> listaSolicitacoes;

  @PostConstruct
  public void init() {
    if (enviarObjectMB.getListSolicitacoes() != null) {
      listaSolicitacoes = enviarObjectMB.getListSolicitacoes();
      enviarObjectMB.setListSolicitacoes(null);
    }
  }

  public List<SolicitacoesExames> getListaSolicitacoes() {
    return listaSolicitacoes;
  }

  public void setListaSolicitacoes(List<SolicitacoesExames> listaSolicitacoes) {
    this.listaSolicitacoes = listaSolicitacoes;
  }

  public EnviarObjectMB getEnviarObjectMB() {
    return enviarObjectMB;
  }

  public void setEnviarObjectMB(EnviarObjectMB enviarObjectMB) {
    this.enviarObjectMB = enviarObjectMB;
  }

}
