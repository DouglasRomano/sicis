package sicis.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.security.core.context.SecurityContextHolder;
import sicis.dao.GenericDAO;
import sicis.domain.Cliente;
import sicis.domain.DescricaoExames;
import sicis.domain.Profissional;
import sicis.domain.Solicitacoes;
import sicis.domain.SolicitacoesExames;

@ManagedBean
@ViewScoped
public class SolicitacoesMB implements Serializable {

  private static final long serialVersionUID = 1L;

  @ManagedProperty("#{enviarObjectMB}")
  private EnviarObjectMB enviarObjectMB;

  private Cliente cliente;
  private DescricaoExames descricaoExames;
  private SolicitacoesExames gerenciadorSolicitacoes;
  private Solicitacoes solicitacoes;

  private Integer numeroDoPedido;
  private String selectTipoProcedimento;
  private boolean renderizarDadosEncontrados;
  private Date dataSolicitacao;

  private Cliente clienteSelecionado;
  private DescricaoExames procedimentoEscolhido;
  private Profissional profissionalSelecionado;

  private List<DescricaoExames> listDescricaoExames;
  private List<Profissional> listProfissionais;
  private List<SolicitacoesExames> listaDeSolicitacoesAPersistir;

  private GenericDAO genericDAO;

  private transient FacesContext facesContext;

  @PostConstruct
  public void solicitacoesMB() {
    cliente = new Cliente();
    descricaoExames = new DescricaoExames();
    renderizarDadosEncontrados = false;
    listaDeSolicitacoesAPersistir = new ArrayList<>();
    gerenciadorSolicitacoes = new SolicitacoesExames();
    solicitacoes = new Solicitacoes();
    listDescricaoExames = new ArrayList<>();
    genericDAO = new GenericDAO();

    if (!(enviarObjectMB.getCliente() == null)) {
      clienteSelecionado = enviarObjectMB.getCliente();
      cliente = enviarObjectMB.getCliente();
      enviarObjectMB.setCliente(null);
    }
    try {
      // buscar ultimo ID do banco para criar o Numero do Pedido
      numeroDoPedido = genericDAO.searchLastId(SolicitacoesExames.class, "solExa_Pedido", "solicitacaoexames");
    } catch (Exception ex) {
      Logger.getLogger(SolicitacoesMB.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void criarNovoProcedimento() {
    descricaoExames = new DescricaoExames();
    selectTipoProcedimento = null;
    renderizarDadosEncontrados = false;
    listarProfissionais();
  }

  public void btnConfirmar() {
    if (!procedimentoEscolhido.getDes_nomeexame().equals("")) {
      listDescricaoExames.add(procedimentoEscolhido);

      if (clienteSelecionado != null && profissionalSelecionado != null && descricaoExames != null) {
        salvarSolicitacao();
        salvarListaSolicitacao();
        listaDeSolicitacoesAPersistir.add(gerenciadorSolicitacoes);
        profissionalSelecionado = null;
      }

    } else {
      facesContext = FacesContext.getCurrentInstance();
      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta!", "Nenhum procedimento foi selecionado"));
    }
  }

  private void salvarSolicitacao() {
    solicitacoes.setCliente(clienteSelecionado);
    solicitacoes.setProfissionalPedido(profissionalSelecionado);
    solicitacoes.setSol_user(SecurityContextHolder.getContext().getAuthentication().getName());
    solicitacoes.setSol_datePedido(dataSolicitacao);
    solicitacoes.setSol_status("Pendente");
  }

  private void salvarListaSolicitacao() {
    gerenciadorSolicitacoes.setSolicitacao(solicitacoes);
    gerenciadorSolicitacoes.setDescricaoExames(procedimentoEscolhido);
    gerenciadorSolicitacoes.setSolExa_Pedido(numeroDoPedido);
  }

  public void btnSalvarSolicitacao() {
    for (SolicitacoesExames solicitacoesNaoPersistidas : listaDeSolicitacoesAPersistir) {
      try {
        genericDAO.save(solicitacoesNaoPersistidas.getSolicitacao());
        genericDAO.save(solicitacoesNaoPersistidas);
        System.out.println(solicitacoesNaoPersistidas.getSolExa_id());
        System.out.println(solicitacoes.getSol_id());
      } catch (Exception ex) {
        Logger.getLogger(SolicitacoesMB.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public float somatoriaProcedimentos() {
    float somatoria = 0;
    for (DescricaoExames listDescricaoExame : listDescricaoExames) {
      somatoria += listDescricaoExame.getDes_valor();
    }
    return somatoria;
  }

  public void searchDescricaoExames() {
    facesContext = FacesContext.getCurrentInstance();

    renderizarDadosEncontrados = true;
    List<DescricaoExames> listDescricaoExames;

    if (!descricaoExames.getDes_codigoProcedimento().isEmpty() || !descricaoExames.getDes_nomeexame().isEmpty()) {
      try {
        listDescricaoExames = genericDAO.searchObject(DescricaoExames.class,"procedimentos",
                null, null,
                new String[]{"des_codigoProcedimento", "des_nomeexame"},
                new Object[]{descricaoExames.getDes_codigoProcedimento(), descricaoExames.getDes_nomeexame()});
        if (listDescricaoExames.isEmpty()) {
          facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Procedimento nao encontrado!", "Não há nenhum procedimento cadastrado com esses dados"));
        } else if (listDescricaoExames.size() == 1) {
          procedimentoEscolhido = listDescricaoExames.get(0);
        }
      } catch (Exception ex) {
        Logger.getLogger(SolicitacoesMB.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public void searchPaciente() {
    facesContext = FacesContext.getCurrentInstance();
    List<Cliente> listCliente;

    try {
      listCliente = genericDAO.searchObject(Cliente.class,"cliente",
              null, null,
              new String[]{"cli_CNS"},
              new Object[]{cliente.getCli_CNS()});
      if (listCliente.isEmpty()) {
        clienteSelecionado = null;
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Paciente não encontrado", "Não existe paciente registrado com cartão SUS"));
      } else if (listCliente.size() == 1) {
        clienteSelecionado = listCliente.get(0);
      }

    } catch (Exception ex) {
      Logger.getLogger(SolicitacoesMB.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void listarProfissionais() {
    try {
      listProfissionais = genericDAO.listAll(Profissional.class);
    } catch (Exception ex) {
      Logger.getLogger(SolicitacoesMB.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  // getters and setters
  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Cliente getClienteSelecionado() {
    return clienteSelecionado;
  }

  public void setClienteSelecionado(Cliente clienteSelecionado) {
    this.clienteSelecionado = clienteSelecionado;
  }

  public List<DescricaoExames> getListDescricaoExames() {
    return listDescricaoExames;
  }

  public void setListDescricaoExames(List<DescricaoExames> listDescricaoExames) {
    this.listDescricaoExames = listDescricaoExames;
  }

  public String getSelectTipoProcedimento() {
    return selectTipoProcedimento;
  }

  public void setSelectTipoProcedimento(String selectTipoProcedimento) {
    this.selectTipoProcedimento = selectTipoProcedimento;
  }

  public DescricaoExames getDescricaoExames() {
    return descricaoExames;
  }

  public void setDescricaoExames(DescricaoExames descricaoExames) {
    this.descricaoExames = descricaoExames;
  }

  public DescricaoExames getProcedimentoEscolhido() {
    return procedimentoEscolhido;
  }

  public void setProcedimentoEscolhido(DescricaoExames procedimentoEscolhido) {
    this.procedimentoEscolhido = procedimentoEscolhido;
  }

  public boolean isRenderizarDadosEncontrados() {
    return renderizarDadosEncontrados;
  }

  public void setRenderizarDadosEncontrados(boolean renderizarDadosEncontrados) {
    this.renderizarDadosEncontrados = renderizarDadosEncontrados;
  }

  public EnviarObjectMB getEnviarObjectMB() {
    return enviarObjectMB;
  }

  public void setEnviarObjectMB(EnviarObjectMB enviarObjectMB) {
    this.enviarObjectMB = enviarObjectMB;
  }

  public List<Profissional> getListProfissionais() {
    return listProfissionais;
  }

  public void setListProfissionais(List<Profissional> listProfissionais) {
    this.listProfissionais = listProfissionais;
  }

  public Date getDataSolicitacao() {
    return dataSolicitacao;
  }

  public void setDataSolicitacao(Date dataSolicitacao) {
    this.dataSolicitacao = dataSolicitacao;
  }

  public Profissional getProfissionalSelecionado() {
    return profissionalSelecionado;
  }

  public void setProfissionalSelecionado(Profissional profissionalSelecionado) {
    this.profissionalSelecionado = profissionalSelecionado;
  }

  public List<SolicitacoesExames> getListaDeSolicitacoesAPersistir() {
    return listaDeSolicitacoesAPersistir;
  }

  public void setListaDeSolicitacoesAPersistir(List<SolicitacoesExames> listaDeSolicitacoesAPersistir) {
    this.listaDeSolicitacoesAPersistir = listaDeSolicitacoesAPersistir;
  }

}
