package sicis.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "solicitacaoexames")
public class SolicitacoesExames implements Serializable{
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long solExa_id;

  @JoinColumn(name = "solExa_numero_sol", nullable = false)
  @ManyToOne
  private Solicitacoes solicitacao;
  
  @JoinColumn(name = "solExa_des_id", nullable = false)
  @ManyToOne
  private DescricaoExames descricaoExames;
  
  @Column(nullable = false)
  private Integer solExa_Pedido;  
  
  //getters and setters
  public Long getSolExa_id() {
    return solExa_id;
  }

  public void setSolExa_id(Long solExa_id) {
    this.solExa_id = solExa_id;
  }

  public Solicitacoes getSolicitacao() {
    return solicitacao;
  }

  public void setSolicitacao(Solicitacoes solicitacao) {
    this.solicitacao = solicitacao;
  }

  public DescricaoExames getDescricaoExames() {
    return descricaoExames;
  }

  public void setDescricaoExames(DescricaoExames descricaoExames) {
    this.descricaoExames = descricaoExames;
  }

  public Integer getSolExa_Pedido() {
    return solExa_Pedido;
  }

  public void setSolExa_Pedido(Integer solExa_Pedido) {
    this.solExa_Pedido = solExa_Pedido;
  }
  
  
}
