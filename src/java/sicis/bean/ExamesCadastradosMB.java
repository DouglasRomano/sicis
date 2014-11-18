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
import org.hibernate.exception.ConstraintViolationException;

import sicis.dao.GenericDAO;
import sicis.domain.DescricaoExames;

@ManagedBean
@ViewScoped
public class ExamesCadastradosMB implements Serializable {

   private static final long serialVersionUID = 1L;

   private DescricaoExames descricaoExames;

   private List<DescricaoExames> listExames;

   private GenericDAO genericDAO;

   private transient FacesContext facesContext;

   @PostConstruct
   public void ExamesCadastradosMB() {
      descricaoExames = new DescricaoExames();
      genericDAO = new GenericDAO();

      listarExames();
   }

   public void listarExames() {
      try {
         listExames = genericDAO.listAll(DescricaoExames.class);
      } catch (Exception ex) {
         Logger.getLogger(ExamesCadastradosMB.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public void saveOrUpdate() {
      facesContext = FacesContext.getCurrentInstance();

      try {
         genericDAO.save(descricaoExames);
         facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso", "Exame Cadastrado com Sucesso"));
      } catch (ConstraintViolationException cve) {
         facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Operação não realizada", "Já existe algum procedimento com essa numeração"));
      } catch (Exception ex) {
         Logger.getLogger(UnidadeMB.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public DescricaoExames getDescricaoExames() {
      return descricaoExames;
   }

   public void setDescricaoExames(DescricaoExames descricaoExames) {
      this.descricaoExames = descricaoExames;
   }

   public List<DescricaoExames> getListExames() {
      return listExames;
   }

   public void setListExames(List<DescricaoExames> listExames) {
      this.listExames = listExames;
   }

}
