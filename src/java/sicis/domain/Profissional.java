package sicis.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import sicis.convert.BaseEntity;

@Entity
public class Profissional implements BaseEntity, Serializable {

  private static final long serialVersionUID = 1L;

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long pro_id;

  @JoinColumn(name = "pro_cli_id")
  @ManyToOne
  private Cliente cliente;

  @JoinColumn(name = "pro_localatendimento_id")
  @ManyToOne
  private Unidade unidade;

  @JoinColumn(name = "pro_permissoes_id")
  @ManyToOne
  private Permissao permissao;

  @Column
  private String pro_cargo;

  @Column
  private String pro_documentacao;

  @Column
  private String pro_login;

  @Column
  private String pro_senha;


  public Long getPro_id() {
    return pro_id;
  }

  public void setPro_id(Long pro_id) {
    this.pro_id = pro_id;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Unidade getUnidade() {
    return unidade;
  }

  public void setUnidade(Unidade unidade) {
    this.unidade = unidade;
  }

  public Permissao getPermissao() {
    return permissao;
  }

  public void setPermissao(Permissao permissao) {
    this.permissao = permissao;
  }

  public String getPro_cargo() {
    return pro_cargo;
  }

  public void setPro_cargo(String pro_cargo) {
    this.pro_cargo = pro_cargo;
  }

  public String getPro_documentacao() {
    return pro_documentacao;
  }

  public void setPro_documentacao(String pro_documentacao) {
    this.pro_documentacao = pro_documentacao;
  }

  public String getPro_senha() {
    return pro_senha;
  }

  public void setPro_senha(String pro_senha) {
    this.pro_senha = pro_senha;
  }

  public String getPro_login() {
    return pro_login;
  }

  public void setPro_login(String pro_login) {
    this.pro_login = pro_login;
  }
  

  @Override
  public Long getId() {
    return pro_id;
  }

}
