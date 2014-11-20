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


@Entity(name = "examesautorizados")
public class Autorizar implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long aut_id;
  
  @JoinColumn(name = "aut_solExa_id")
  @ManyToOne
  private SolicitacoesExames solicitacoes;
  
  @Column
  private String aut_profAgendamento;
  
  @JoinColumn(name = "aut_localAgendado")
  @ManyToOne
  private Unidade localDeRealizacao;
 
  @Temporal(TemporalType.DATE)
  @Column
  private Date aut_data_horaAgendada;
  
  @Temporal(TemporalType.DATE)
  @Column
  private Date aut_data_horaAtual;
  
  
  // getters and setters

  public Long getAut_id() {
    return aut_id;
  }

  public void setAut_id(Long aut_id) {
    this.aut_id = aut_id;
  }

  public SolicitacoesExames getSolicitacoes() {
    return solicitacoes;
  }

  public void setSolicitacoes(SolicitacoesExames solicitacoes) {
    this.solicitacoes = solicitacoes;
  }

  public String getAut_profAgendamento() {
    return aut_profAgendamento;
  }

  public void setAut_profAgendamento(String aut_profAgendamento) {
    this.aut_profAgendamento = aut_profAgendamento;
  }


  public Unidade getLocalDeRealizacao() {
    return localDeRealizacao;
  }

  public void setLocalDeRealizacao(Unidade localDeRealizacao) {
    this.localDeRealizacao = localDeRealizacao;
  }

  public Date getAut_data_horaAgendada() {
    return aut_data_horaAgendada;
  }

  public void setAut_data_horaAgendada(Date aut_data_horaAgendada) {
    this.aut_data_horaAgendada = aut_data_horaAgendada;
  }

  public Date getAut_data_horaAtual() {
    return aut_data_horaAtual;
  }

  public void setAut_data_horaAtual(Date aut_data_horaAtual) {
    this.aut_data_horaAtual = aut_data_horaAtual;
  }

  
}
