package sicis.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Profissional implements Serializable {
   
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long pro_id;
    
    @JoinColumn(name = "pro_cli_id")
    @OneToOne
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
    
    
}
