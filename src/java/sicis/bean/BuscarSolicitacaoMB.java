package sicis.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import sicis.dao.GenericDAO;
import sicis.domain.Cliente;
import sicis.domain.DescricaoExames;
import sicis.domain.Profissional;
import sicis.domain.Solicitacoes;
import sicis.domain.SolicitacoesExames;

@ManagedBean
@ViewScoped
public class BuscarSolicitacaoMB implements Serializable {

  private Cliente cliente;
  private Cliente nomeProfissional;
  private DescricaoExames procedimentos;
  private SolicitacoesExames solicitacoes;

  private GenericDAO genericDAO;

  private List<SolicitacoesExames> listaSolicitacoesExames;

  private transient FacesContext facesContext;

  @PostConstruct
  public void init() {
    solicitacoes = new SolicitacoesExames();
    cliente = new Cliente();
    nomeProfissional = new Cliente();
    procedimentos = new DescricaoExames();

    genericDAO = new GenericDAO();
  }

  public void searchSolicitacoes() {
    facesContext = FacesContext.getCurrentInstance();

    ArrayList<String> caminhos = new ArrayList<>();
    ArrayList<String> colunas = new ArrayList<>();
    ArrayList<String> propertyName = new ArrayList<>();
    ArrayList<Object> listObjetos = new ArrayList<>();

    caminhos.add("solicitacoes.solicitacao");
    colunas.add("solicitacao");

    try {
      if (!(solicitacoes.getSolExa_Pedido().equals(0))) {
        listaSolicitacoesExames = genericDAO.searchObject(SolicitacoesExames.class, "solicitacoes",
                null, null,
                new String[]{"solExa_Pedido"}, new Object[]{solicitacoes.getSolExa_Pedido()});
      } else {
        if (!(nomeProfissional.getCli_nome().isEmpty())) {
          caminhos.add("solicitacao.profissionalPedido");
          colunas.add("profissionalPedido");
          caminhos.add("profissionalPedido.cliente");
          colunas.add("cliente");

          propertyName.add("cliente.cli_nome");
          listObjetos.add(nomeProfissional.getCli_nome());
        }
        if (!(procedimentos.getDes_codigoProcedimento().isEmpty())) {
          caminhos.add("solicitacoes.descricaoExames");
          colunas.add("descricaoExames");

          propertyName.add("descricaoExames.des_codigoProcedimento");
          listObjetos.add(procedimentos.getDes_codigoProcedimento());
        }

        if (!(cliente.getCli_CNS().isEmpty())) {
          caminhos.add("solicitacao.cliente");
          colunas.add("cliente");

          propertyName.add("cliente.cli_CNS");
          listObjetos.add(cliente.getCli_CNS());
        }
        String[] arrayCaminhos = caminhos.toArray(new String[caminhos.size()]);
        String[] arrayColunas = colunas.toArray(new String[colunas.size()]);
        String[] arrayPropertyName = propertyName.toArray(new String[propertyName.size()]);
        Object[] arrayObjects = listObjetos.toArray(new Object[listObjetos.size()]);

        listaSolicitacoesExames = genericDAO.searchObject(SolicitacoesExames.class, "solicitacoes",
                arrayCaminhos, arrayColunas, arrayPropertyName, arrayObjects);
      }

    } catch (Exception ex) {
      Logger.getLogger(BuscarSolicitacaoMB.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  // getters and setters
  public SolicitacoesExames getSolicitacoes() {
    return solicitacoes;
  }

  public void setSolicitacoes(SolicitacoesExames solicitacoes) {
    this.solicitacoes = solicitacoes;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public DescricaoExames getProcedimentos() {
    return procedimentos;
  }

  public void setProcedimentos(DescricaoExames procedimentos) {
    this.procedimentos = procedimentos;
  }

  public Cliente getNomeProfissional() {
    return nomeProfissional;
  }

  public void setNomeProfissional(Cliente nomeProfissional) {
    this.nomeProfissional = nomeProfissional;
  }

  public List<SolicitacoesExames> getListaSolicitacoesExames() {
    return listaSolicitacoesExames;
  }

  public void setListaSolicitacoesExames(List<SolicitacoesExames> listaSolicitacoesExames) {
    this.listaSolicitacoesExames = listaSolicitacoesExames;
  }

}
