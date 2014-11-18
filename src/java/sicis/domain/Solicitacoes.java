package sicis.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Solicitacoes implements Serializable {

  private static final long serialVersionUID = 1L;

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long sol_id;

  @JoinColumn(name = "sol_cliente", nullable = false)
  @ManyToOne
  private Cliente cliente;
  
  @JoinColumn(name = "sol_profissional", nullable = false)
  @ManyToOne
  private Profissional profissionalPedido;
  
  @Column(nullable = false)
  private String sol_user;
  
  @Temporal(TemporalType.DATE)
  @Column(nullable = false)
  private Date sol_datePedido;
    
  @Column(nullable = false)
  private String sol_status;
  
  //getters and setters
  public Long getSol_id() {
    return sol_id;
  }

  public void setSol_id(Long sol_id) {
    this.sol_id = sol_id;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Profissional getProfissionalPedido() {
    return profissionalPedido;
  }

  public void setProfissionalPedido(Profissional profissionalPedido) {
    this.profissionalPedido = profissionalPedido;
  }

  public String getSol_user() {
    return sol_user;
  }

  public void setSol_user(String sol_user) {
    this.sol_user = sol_user;
  }


  public Date getSol_datePedido() {
    return sol_datePedido;
  }

  public void setSol_datePedido(Date sol_datePedido) {
    this.sol_datePedido = sol_datePedido;
  }

  public String getSol_status() {
    return sol_status;
  }

  public void setSol_status(String sol_status) {
    this.sol_status = sol_status;
  }
  

}
