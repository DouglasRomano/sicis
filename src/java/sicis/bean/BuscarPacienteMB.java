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
import org.primefaces.context.RequestContext;
import sicis.dao.GenericDAO;

import sicis.domain.Cliente;
import sicis.domain.Prontuario;

@ManagedBean
@ViewScoped
public class BuscarPacienteMB implements Serializable {

   private static final long serialVersionUID = 1L;

   private Cliente cliente;
   private Prontuario prontuario;

   private List<Cliente> listCliente;
   private List<Prontuario> listProntuario;
   private Cliente clienteSelecionado;

   public boolean renderizarUpdate;
   
   private GenericDAO genericDAO;

   private transient FacesContext facesContext;
   private transient RequestContext requestContext;

   @PostConstruct
   public void buscarPacienteMB() {

      cliente = new Cliente();
      prontuario = new Prontuario();

      renderizarUpdate = false;
      
      genericDAO = new GenericDAO();
   }
   
   public void renderUpdate(){
      renderizarUpdate = true;
   }

   public void restartObjetos() {
      listCliente = null;
      cliente = new Cliente();
      clienteSelecionado = null;
      prontuario = new Prontuario();
   }

   public void selecionarPacienteDT() {
      if (clienteSelecionado == null) {
         clienteSelecionado = prontuario.getCliente();
      } else if (prontuario.getPront_id() == null) {
         prontuario.setCliente(clienteSelecionado);
         searchPacientebyProntuario();
      }
     
   }

   public void searchPacientebyDocs() {
      facesContext = FacesContext.getCurrentInstance();
      requestContext = RequestContext.getCurrentInstance();

      List<Cliente> listCliente;
      try {
         listCliente = genericDAO.searchObject(Cliente.class,
                 new String[]{"cli_nasc", "cli_CPF", "cli_CNS", "cli_nome", "cli_nomedamae"},
                 new Object[]{cliente.getCli_nasc(), cliente.getCli_CPF(), cliente.getCli_CNS(), cliente.getCli_nome(), cliente.getCli_nomedamae()});
         if (listCliente.isEmpty()) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Não foi encontrado nenhum Paciente"));
         } else if (listCliente.size() == 1) {
            clienteSelecionado = listCliente.get(0);
            prontuario.setCliente(clienteSelecionado);
            searchPacientebyProntuario();
            requestContext.execute("PF('dialogPaciente').show();");
         } else {
            this.listCliente = listCliente;
            requestContext.execute("PF('dialogTable').show();");
         }
      } catch (Exception ex) {
         Logger.getLogger(BuscarPacienteMB.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public void searchPacientebyProntuario() {
      facesContext = FacesContext.getCurrentInstance();
      requestContext = RequestContext.getCurrentInstance();

      try {
         listProntuario = genericDAO.searchObject(Prontuario.class, new String[]{"cliente", "unidade", "pront_numeracao"},
                 new Object[]{clienteSelecionado, prontuario.getUnidade(), prontuario.getPront_numeracao()});
         if (listProntuario.isEmpty()) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta!", "Esse paciente não tem nenhum prontuario cadastrado"));
         } else if (listProntuario.size() == 1) {
            prontuario = listProntuario.get(0);
            clienteSelecionado = prontuario.getCliente();
            requestContext.execute("PF('dialogPaciente').show();");
         } else {
            listCliente = new ArrayList<>();
            for (Prontuario newProntuario : listProntuario) {
               listCliente.add(newProntuario.getCliente());
            }
            requestContext.execute("PF('dialogTable').show();");
         }
      } catch (Exception ex) {
         Logger.getLogger(BuscarPacienteMB.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public void updatePaciente(){
      facesContext = FacesContext.getCurrentInstance();
      try {
         genericDAO.update(clienteSelecionado);
         genericDAO.update(prontuario);
         facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Atualizado!", "Paciente salvo com sucesso"));
      } catch (Exception ex) {
         Logger.getLogger(BuscarPacienteMB.class.getName()).log(Level.SEVERE, null, ex);
      }
      
   }
   
   public void deletePaciente(){
      
   }
   
   // getters and setters
   public Cliente getCliente() {
      return cliente;
   }

   public void setCliente(Cliente cliente) {
      this.cliente = cliente;
   }

   public Prontuario getProntuario() {
      return prontuario;
   }

   public void setProntuario(Prontuario prontuario) {
      this.prontuario = prontuario;
   }

   public Cliente getClienteSelecionado() {
      return clienteSelecionado;
   }

   public void setClienteSelecionado(Cliente clienteSelecionado) {
      this.clienteSelecionado = clienteSelecionado;
   }

   public List<Cliente> getListCliente() {
      return listCliente;
   }

   public void setListCliente(List<Cliente> listCliente) {
      this.listCliente = listCliente;
   }  

   public boolean isRenderizarUpdate() {
      return renderizarUpdate;
   }

   public void setRenderizarUpdate(boolean renderizarUpdate) {
      this.renderizarUpdate = renderizarUpdate;
   }
   
}
