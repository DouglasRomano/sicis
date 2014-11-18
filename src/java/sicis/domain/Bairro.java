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
public class Bairro implements Serializable {

  private static final long serialVersionUID = 1L;

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long bai_id;

  @JoinColumn(name = "bai_cid_id", nullable = false)
  @ManyToOne
  private Cidade cidade;

  @Column(nullable = false)
  private String bai_descricao;

  public Long getBai_id() {
    return bai_id;
  }

  public void setBai_id(Long bai_id) {
    this.bai_id = bai_id;
  }

  public Cidade getCidade() {
    return cidade;
  }

  public void setCidade(Cidade cidade) {
    this.cidade = cidade;
  }

  public String getBai_descricao() {
    return bai_descricao;
  }

  public void setBai_descricao(String bai_descricao) {
    this.bai_descricao = bai_descricao;
  }

}
