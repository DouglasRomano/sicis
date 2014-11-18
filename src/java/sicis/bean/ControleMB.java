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
import sicis.dao.GenericDAO;
import sicis.domain.Controle;
import sicis.domain.DescricaoExames;

@ManagedBean
@ViewScoped
public class ControleMB implements Serializable {

  private DescricaoExames procedimento;
  private Controle controle;

  private String disabilitarCampos;
  
  private String valorUnitario;
  private String valorTotal;

  private GenericDAO genericDAO;
  private transient FacesContext facesContext;

  @PostConstruct
  public void init() {
    procedimento = new DescricaoExames();
    genericDAO = new GenericDAO();
    controle = new Controle();
    disabilitarCampos = "true";
    
    valorTotal = new String();
    valorUnitario = new String();
  }

  private void renderizarUpdate() {
    disabilitarCampos = "false";
  }

  public void ajustarValores(String parametro){
    renderizarUpdate();
    if(parametro.equals("unitario")){
      controle.setCtrl_limiteValor(controle.getCtrl_limiteUnitario() * procedimento.getDes_valor());
    } else{
      controle.setCtrl_limiteUnitario(controle.getCtrl_limiteValor().intValue() / procedimento.getDes_valor().intValue());
    }
    
  }
  
  public void saveControle() {
    facesContext = FacesContext.getCurrentInstance();
    try {
      if(valorTotal.equals("")){
        valorTotal = "0";
      }
      if(valorUnitario.equals("")){
        valorUnitario = "0";
      }
      controle.setCtrl_limiteUnitario(Integer.parseInt(valorUnitario));
      controle.setCtrl_limiteValor(Float.parseFloat(valorTotal));
      if(controle.getCtrl_limiteUnitario()>0){
         if(controle.getCtrl_limiteValor()==0){
           controle.setCtrl_limiteValor(controle.getCtrl_limiteUnitario()* procedimento.getDes_valor());
         }
         else if(controle.getCtrl_limiteValor()>0){
           if(controle.getCtrl_limiteUnitario()==0){
             controle.setCtrl_limiteUnitario((Integer) controle.getCtrl_limiteValor().intValue() / procedimento.getDes_valor().intValue());
           }
         }
      }
      
      controle.setExame(procedimento);
      genericDAO.save(controle);
      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso","Restrição salva com Sucesso"));
    } catch (Exception ex) {
      Logger.getLogger(ControleMB.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void updateControle() {
    facesContext = FacesContext.getCurrentInstance();
    try {
      genericDAO.update(controle);
      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso","Atualizado com Sucesso"));
    } catch (Exception ex) {
      Logger.getLogger(ControleMB.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void deleteControle() {
    facesContext = FacesContext.getCurrentInstance();
    try {
      genericDAO.delete(controle);
      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso","Atualizado com Sucesso"));
    } catch (Exception ex) {
      Logger.getLogger(ControleMB.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void searchProcedimento() {
    facesContext = FacesContext.getCurrentInstance();

    List<DescricaoExames> listDescricaoExames;

    try {
      listDescricaoExames = genericDAO.searchObject(DescricaoExames.class,"procedimentos",
              null, null,
              new String[]{"des_codigoProcedimento", "des_nomeexame"},
              new Object[]{procedimento.getDes_codigoProcedimento(), procedimento.getDes_nomeexame()});
      if (listDescricaoExames.isEmpty()) {
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Procedimento nao encontrado!", "Não há nenhum procedimento cadastrado com esses dados"));
      } else if (listDescricaoExames.size() == 1) {
        procedimento = listDescricaoExames.get(0);
      }
    } catch (Exception ex) {
      Logger.getLogger(ControleMB.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void searchControle() {
    facesContext = FacesContext.getCurrentInstance();

    List<Controle> listControle;

    try {
      searchProcedimento();
      listControle = genericDAO.searchObject(Controle.class,"controle",
              null, null,
              new String[]{"exame"}, new Object[]{procedimento});
      if (listControle.isEmpty()) {
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Nenhuma restrição foi encontrada!"));
      } else if (listControle.size() == 1) {
        controle = listControle.get(0);
      }
    } catch (Exception ex) {
      Logger.getLogger(ControleMB.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  //Getters and Setters
  public DescricaoExames getProcedimento() {
    return procedimento;
  }

  public void setProcedimento(DescricaoExames procedimento) {
    this.procedimento = procedimento;
  }

  public Controle getControle() {
    return controle;
  }

  public void setControle(Controle controle) {
    this.controle = controle;
  }

  public String getDisabilitarCampos() {
    return disabilitarCampos;
  }

  public void setDisabilitarCampos(String disabilitarCampos) {
    this.disabilitarCampos = disabilitarCampos;
  }

  public String getValorUnitario() {
    return valorUnitario;
  }

  public void setValorUnitario(String valorUnitario) {
    this.valorUnitario = valorUnitario;
  }

  public String getValorTotal() {
    return valorTotal;
  }

  public void setValorTotal(String valorTotal) {
    this.valorTotal = valorTotal;
  }

}
