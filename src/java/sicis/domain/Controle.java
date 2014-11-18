package sicis.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Controle implements Serializable{
 
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long ctrl_id;
    
  @JoinColumn(name = "ctrl_des_Exa_id", nullable = false)
  @ManyToOne
  private DescricaoExames exame;
  
  @Column
  private Integer ctrl_limiteUnitario;
  
  @Column
  private Float ctrl_limiteValor;
  
  // getters and setters
  public Long getCtrl_id() {
    return ctrl_id;
  }

  public void setCtrl_id(Long ctrl_id) {
    this.ctrl_id = ctrl_id;
  }

  public DescricaoExames getExame() {
    return exame;
  }

  public void setExame(DescricaoExames exame) {
    this.exame = exame;
  }

  public Integer getCtrl_limiteUnitario() {
    return ctrl_limiteUnitario;
  }

  public void setCtrl_limiteUnitario(Integer ctrl_limiteUnitario) {
    this.ctrl_limiteUnitario = ctrl_limiteUnitario;
  }

  public Float getCtrl_limiteValor() {
    return ctrl_limiteValor;
  }

  public void setCtrl_limiteValor(Float ctrl_limiteValor) {
    this.ctrl_limiteValor = ctrl_limiteValor;
  }
  
  
}
