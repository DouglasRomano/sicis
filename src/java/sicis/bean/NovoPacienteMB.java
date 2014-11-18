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
import sicis.dao.GenericDAO;
import sicis.domain.Bairro;
import sicis.domain.Cidade;
import sicis.domain.Cliente;
import sicis.domain.Logradouro;
import sicis.domain.Prontuario;
import sicis.domain.Unidade;
import sicis.manipuladores.Manipulador;

@ManagedBean
@ViewScoped
public class NovoPacienteMB implements Serializable {

   private static final long serialVersionUID = 1L;

   private Cliente cliente;
   private Logradouro logradouro;
   private Bairro bairro;
   private Cidade cidade;
   private Prontuario prontuario;
   private Unidade unidade;

   private String opcaoHeader;
   private Logradouro logSelecionado;
   private List<Logradouro> listaLogradouro;

   private Manipulador manipulador;
   private GenericDAO genericDAO;

   private transient FacesContext facesContext;
   private transient RequestContext requestContext;

   @PostConstruct
   public void novoPaciente() {
      cliente = new Cliente();
      bairro = new Bairro();
      logradouro = new Logradouro();
      cidade = new Cidade();
      prontuario = new Prontuario();

      opcaoHeader = new String();
      manipulador = new Manipulador();
      genericDAO = new GenericDAO();
   }

   public String ajustarHeader() {
      return manipulador.inserirHeader(opcaoHeader);
   }

   public void selecionarItemDatabase() {
      facesContext = FacesContext.getCurrentInstance();
      if (!(logSelecionado == null)) {
         logradouro = logSelecionado;
         facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logradouro selecionado", "Prossiga o cadastro!"));
      }
   }

   public void searchCidade() {
      facesContext = FacesContext.getCurrentInstance();
      requestContext = RequestContext.getCurrentInstance();

      List<Cidade> listCidade;
      try {
         listCidade = genericDAO.searchObject(Cidade.class,"cidade",
                 null, null,
                 new String[]{"cidadeDescricao", "estado"},
                 new Object[]{cidade.getCidadeDescricao(), cidade.getEstado()});
         if (listCidade.isEmpty()) {
            opcaoHeader = "cadastrar cidade";

            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cidade não encontrada", "Cadastre-a para prosseguir!"));
            requestContext.getCurrentInstance().execute("PF('cadastrarDialog').show();");
         } else if (listCidade.size() == 1) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cidade encontrada", "Prossiga o cadastro!"));
            cidade = listCidade.get(0);
         } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Informe mais detalhes", "Sua busca retornou muitos registros"));
         }
      } catch (Exception ex) {
         Logger.getLogger(NovoPacienteMB.class.getName()).log(Level.SEVERE, null, ex);
      }

   }

   public void searchBairro() {

      facesContext = FacesContext.getCurrentInstance();
      requestContext = RequestContext.getCurrentInstance();

      List<Bairro> listBairro;
      try {
         bairro.setCidade(cidade);
         listBairro = genericDAO.searchObject(Bairro.class,"bairro",
                 null, null,
                 new String[]{"cidade", "bai_descricao"},
                 new Object[]{bairro.getCidade(), "%" + bairro.getBai_descricao() + "%"});

         if (listBairro.isEmpty()) {
            opcaoHeader = "cadastrar bairro";

            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bairro não encontrado", "Cadastre-o para prosseguir!"));
            requestContext.getCurrentInstance().execute("PF('cadastrarDialog').show();");
         } else if (listBairro.size() == 1) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bairro encontrado", "Prossiga o cadastro!"));
            bairro = listBairro.get(0);
         } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Informe mais detalhes!", "Sua busca retornou muitos registros"));
         }

      } catch (Exception ex) {

      }

   }

   public void searchLogradouro() {
      facesContext = FacesContext.getCurrentInstance();
      requestContext = RequestContext.getCurrentInstance();

      List<Logradouro> listLogradouro;
      try {
         logradouro.setLog_bairro_id(this.bairro);
         listLogradouro = genericDAO.searchObject(Logradouro.class,"logradouro",
                 null, null,
                 new String[]{"log_bairro_id", "log_descricao"},
                 new Object[]{logradouro.getLog_bairro_id(), "%" + logradouro.getLog_descricao() + "%"});

         if (listLogradouro.isEmpty()) {
            opcaoHeader = "cadastrar logradouro";

            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Logradouro não encontrado", "Cadastre-o para prosseguir!"));
            requestContext.getCurrentInstance().execute("PF('cadastrarDialog').show();");
         } else if (listLogradouro.size() == 1) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logradouro encontrado", "Prossiga o cadastro!"));
            logradouro = listLogradouro.get(0);
         } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Informe mais detalhes!", "Sua busca retornou muitos campos"));
         }
      } catch (Exception ex) {
         Logger.getLogger(UnidadeMB.class.getName()).log(Level.SEVERE, null, ex);
      }

   }

   public void listLogradouros() {

      facesContext = FacesContext.getCurrentInstance();
      requestContext = RequestContext.getCurrentInstance();
      logradouro.setLog_bairro_id(bairro);
      try {
         listaLogradouro = genericDAO.searchObject(Logradouro.class,"logradouro",
                 null, null,
                 new String[]{"log_bairro_id", "log_descricao"},
                 new Object[]{logradouro.getLog_bairro_id(), "%" + logradouro.getLog_descricao() + "%"});
         if (listaLogradouro.size() == 1) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logradouro Encontrado", "Prossiga o Cadastro!"));
            this.logradouro = listaLogradouro.get(0);
         } else if (listaLogradouro.size() > 1) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Foram encontrados mais de um Logradouro", "Selecione o correspondente!"));
            requestContext.execute("PF('logDialogDT').show();");
         } else {
            opcaoHeader = "cadastrar logradouro";
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Logradouro não encontrado", "Cadastre-o para prosseguir!"));
            requestContext.execute("PF('cadastrarDialog').show();");
         }
      } catch (Exception ex) {

      }
   }

   public void searchCliente() {
      facesContext = FacesContext.getCurrentInstance();
      requestContext = RequestContext.getCurrentInstance();

      List<Cliente> listCliente;

      try {
         listCliente = genericDAO.searchObject(Cliente.class,"cliente",
                 null, null,new String[]{"cli_CNS"}, new Object[]{cliente.getCli_CNS()});

         cliente = listCliente.get(0);

      } catch (Exception ex) {
         Logger.getLogger(NovoPacienteMB.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public void saveEndereco() {
      switch (opcaoHeader) {
         case "cadastrar cidade":
            try {
               genericDAO.save(cidade);
               searchCidade();
            } catch (Exception ex) {
               Logger.getLogger(UnidadeMB.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
         case "cadastrar bairro":
            try {
               genericDAO.save(bairro);
               searchBairro();
            } catch (Exception ex) {
               Logger.getLogger(UnidadeMB.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
         case "cadastrar logradouro":
            try {
               genericDAO.save(logradouro);
            } catch (Exception ex) {
               Logger.getLogger(UnidadeMB.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
      }
      facesContext = FacesContext.getCurrentInstance();
      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com Sucesso", "Prossiga"));
   }

   public void saveProntuario() {
      prontuario.setCliente(cliente);

      try {
         genericDAO.save(prontuario);

      } catch (Exception ex) {
         Logger.getLogger(NovoPacienteMB.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public void savePaciente() {
      cliente.setCli_log_id(logradouro);
      try {
         genericDAO.save(cliente);
         searchCliente();
      } catch (Exception ex) {
         Logger.getLogger(NovoPacienteMB.class.getName()).log(Level.SEVERE, null, ex);
      }

   }
   

   //Salvar

   public void confirmSave() {

      facesContext = FacesContext.getCurrentInstance();

      savePaciente();
      saveProntuario();
      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Esse paciente foi salvo"));
   }

   // getters and setters
   public Cliente getCliente() {
      return cliente;
   }

   public void setCliente(Cliente cliente) {
      this.cliente = cliente;
   }

   public Logradouro getLogSelecionado() {
      return logSelecionado;
   }

   public void setLogSelecionado(Logradouro logSelecionado) {
      this.logSelecionado = logSelecionado;
   }

   public List<Logradouro> getListaLogradouro() {
      return listaLogradouro;
   }

   public void setListaLogradouro(List<Logradouro> listaLogradouro) {
      this.listaLogradouro = listaLogradouro;
   }

   public Logradouro getLogradouro() {
      return logradouro;
   }

   public void setLogradouro(Logradouro logradouro) {
      this.logradouro = logradouro;
   }

   public Bairro getBairro() {
      return bairro;
   }

   public void setBairro(Bairro bairro) {
      this.bairro = bairro;
   }

   public Cidade getCidade() {
      return cidade;
   }

   public void setCidade(Cidade cidade) {
      this.cidade = cidade;
   }

   public Unidade getUnidade() {
      return unidade;
   }

   public void setUnidade(Unidade unidade) {
      this.unidade = unidade;
   }

   public Prontuario getProntuario() {
      return prontuario;
   }

   public void setProntuario(Prontuario prontuario) {
      this.prontuario = prontuario;
   }

}
