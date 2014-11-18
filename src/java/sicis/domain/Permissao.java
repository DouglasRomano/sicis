package sicis.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import sicis.convert.BaseEntity;

@Entity
public class Permissao implements BaseEntity, Serializable {

  private static final long serialVersionUID = 1L;

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private long permi_id;

  @Column
  private String permi_Descricao;

  @Column
  private String permi_Roles;

  @Column
  private String permi_titulo;

  public long getPermi_id() {
    return permi_id;
  }

  public void setPermi_id(long permi_id) {
    this.permi_id = permi_id;
  }

  public String getPermi_Descricao() {
    return permi_Descricao;
  }

  public void setPermi_Descricao(String permi_Descricao) {
    this.permi_Descricao = permi_Descricao;
  }

  public String getPermi_Roles() {
    return permi_Roles;
  }

  public void setPermi_Roles(String permi_Roles) {
    this.permi_Roles = permi_Roles;
  }

  public String getPermi_titulo() {
    return permi_titulo;
  }

  public void setPermi_titulo(String permi_titulo) {
    this.permi_titulo = permi_titulo;
  }

  @Override
  public Long getId() {
    return this.permi_id;
  }

}
