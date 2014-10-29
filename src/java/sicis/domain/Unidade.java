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
import sicis.convert.BaseEntity;


@Entity
@Table(name = "localatendimento")
public class Unidade implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long local_id;

    @JoinColumn(name = "local_log_id")
    @ManyToOne
    private Logradouro local_log_id;

    @Column
    private String local_Descricao;

    @Column
    private String local_Numero;

    @Column
    private String local_Telefone;

    public Long getLocal_id() {
        return local_id;
    }

    public void setLocal_id(Long local_id) {
        this.local_id = local_id;
    }

    public Logradouro getLocal_log_id() {
        return local_log_id;
    }

    public void setLocal_log_id(Logradouro locl_log_id) {
        this.local_log_id = locl_log_id;
    }

    public String getLocal_Descricao() {
        return local_Descricao;
    }

    public void setLocal_Descricao(String local_Descricao) {
        this.local_Descricao = local_Descricao;
    }

    public String getLocal_Numero() {
        return local_Numero;
    }

    public void setLocal_Numero(String local_Numero) {
        this.local_Numero = local_Numero;
    }

    public String getLocal_Telefone() {
        return local_Telefone;
    }

    public void setLocal_Telefone(String local_Telefone) {
        this.local_Telefone = local_Telefone;
    }

    @Override
    public Long getId() {
        return local_id;
    }
    
    
}
