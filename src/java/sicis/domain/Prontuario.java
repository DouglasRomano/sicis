package sicis.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Prontuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue
    @Id
    private Long pront_id;
    
    @JoinColumn(name = "pront_cliente_id", nullable = false)
    @ManyToOne
    private Cliente cliente;
    
    @JoinColumn(name = "pront_ubs_id", nullable = false)
    @ManyToOne
    private Unidade unidade;
    
    @Column 
    private String pront_numeracao;

    public Long getPront_id() {
        return pront_id;
    }

    public void setPront_id(Long pront_id) {
        this.pront_id = pront_id;
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

    public String getPront_numeracao() {
        return pront_numeracao;
    }

    public void setPront_numeracao(String pront_numeracao) {
        this.pront_numeracao = pront_numeracao;
    }
    
    
}
