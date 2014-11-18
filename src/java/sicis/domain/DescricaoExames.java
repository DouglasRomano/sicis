package sicis.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DescricaoExames implements Serializable {

  private static final long serialVersionUID = 1L;

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long des_id;

  @Column(nullable = false)
  private String des_nomeexame;

  @Column(nullable = false)
  private String des_categoria;

  @Column
  private String des_codigoProcedimento;

  @Column
  private Float des_valor;

  public Long getDes_id() {
    return des_id;
  }

  public void setDes_id(Long des_id) {
    this.des_id = des_id;
  }

  public String getDes_nomeexame() {
    return des_nomeexame;
  }

  public void setDes_nomeexame(String des_nomeexame) {
    this.des_nomeexame = des_nomeexame;
  }

  public String getDes_categoria() {
    return des_categoria;
  }

  public void setDes_categoria(String des_categoria) {
    this.des_categoria = des_categoria;
  }

  public String getDes_codigoProcedimento() {
    return des_codigoProcedimento;
  }

  public void setDes_codigoProcedimento(String des_codigoProcedimento) {
    this.des_codigoProcedimento = des_codigoProcedimento;
  }

  public Float getDes_valor() {
    return des_valor;
  }

  public void setDes_valor(Float des_valor) {
    this.des_valor = des_valor;
  }

}
