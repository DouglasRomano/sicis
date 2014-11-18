package sicis.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import sicis.domain.Autorizar;
import sicis.domain.Controle;
import sicis.domain.Solicitacoes;
import sicis.domain.SolicitacoesExames;
import sicis.domain.Unidade;

@ManagedBean
@ViewScoped
public class AutorizarMB implements Serializable {

  private Autorizar autorizar;

  private SolicitacoesExames solicitacaoSelecionada;

  private List<SolicitacoesExames> solicitacoesPendentes;
  private List<Unidade> listUnidades;

  private GenericDAO genericDAO;

  private transient FacesContext facesContext;

  @PostConstruct
  public void init() {
    autorizar = new Autorizar();
    solicitacaoSelecionada = null;
        
    genericDAO = new GenericDAO();

    searchSolicitacoesPendentes();
   
  }

  private Date pegarDataAtual() {
    Calendar calendar = new GregorianCalendar();
    Date date = new Date();
    calendar.setTime(date);
    return calendar.getTime();
  }

  public void selecionarProcedimento(SolicitacoesExames solicitacoes) {
    solicitacaoSelecionada = solicitacoes;
    RequestContext.getCurrentInstance().execute("PF('dialogAutorizar').show()");
     listAllUnidades();
  }

  private void searchSolicitacoesPendentes() {
    try {
      solicitacoesPendentes = genericDAO.searchObject(SolicitacoesExames.class, "solicitacoes",
              new String[]{"solicitacoes.solicitacao"},
              new String[]{"solicitacao"},
              new String[]{"solicitacao.sol_status"},
              new Object[]{"Pendente"});
    } catch (Exception ex) {
      Logger.getLogger(AutorizarMB.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void listAllUnidades() {
    try {
      listUnidades = genericDAO.listAll(Unidade.class);
    } catch (Exception ex) {
      Logger.getLogger(AutorizarMB.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void btnConfirmarAgendamento() {
    facesContext = FacesContext.getCurrentInstance();
    Solicitacoes solicitacao = solicitacaoSelecionada.getSolicitacao();
    solicitacao.setSol_status("Autorizado");
    autorizar.setAut_data_horaAtual(new Timestamp(System.currentTimeMillis()));
    autorizar.setAut_profAgendamento(SecurityContextHolder.getContext().getAuthentication().getName());
    autorizar.setSolicitacoes(solicitacaoSelecionada);

    try {
      List<Controle> listControle = genericDAO.searchObject(Controle.class, "controle", null, null,
              new String[]{"exame"}, new Object[]{solicitacaoSelecionada.getDescricaoExames()});
      if (listControle.isEmpty()) {
        genericDAO.save(autorizar);
        genericDAO.update(solicitacao);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Autorizado com sucesso"));
      } else {
        Controle controle = listControle.get(0);
        if (controle.getCtrl_limiteUnitario() > 0
                && controle.getCtrl_limiteValor() > solicitacaoSelecionada.getDescricaoExames().getDes_valor()) {

          controle.setCtrl_limiteUnitario(controle.getCtrl_limiteUnitario() - 1);
          controle.setCtrl_limiteValor(controle.getCtrl_limiteValor() - solicitacaoSelecionada.getDescricaoExames().getDes_valor());
          genericDAO.save(autorizar);
          genericDAO.update(solicitacao);
          facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Autorizado com sucesso"));
        } else {
          facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Limite", "o limite para esse procedimento já foi alcançado!"));
        }
      }
    } catch (Exception ex) {
      Logger.getLogger(AutorizarMB.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      init();
    }

  }

  //getters and setters
  public Autorizar getAutorizar() {
    return autorizar;
  }

  public void setAutorizar(Autorizar autorizar) {
    this.autorizar = autorizar;
  }

  public List<SolicitacoesExames> getSolicitacoesPendentes() {
    return solicitacoesPendentes;
  }

  public void setSolicitacoesPendentes(List<SolicitacoesExames> solicitacoesPendentes) {
    this.solicitacoesPendentes = solicitacoesPendentes;
  }

  public List<Unidade> getListUnidades() {
    return listUnidades;
  }

  public void setListUnidades(List<Unidade> listUnidades) {
    this.listUnidades = listUnidades;
  }

}
