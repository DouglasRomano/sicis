package sicis.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cidade implements Serializable{

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "cid_id")
    private Long cidade_id;
    
    @Column(name = "cid_descricao", nullable = false)  
    private String cidadeDescricao;
    
    @Column(name = "cid_estado", nullable = false)
    private String estado;

    //geters and setters
    
    public Long getCidade_id() {
        return cidade_id;
    }

    public void setCidade_id(Long cidade_id) {
        this.cidade_id = cidade_id;
    }

    public String getCidadeDescricao() {
        return cidadeDescricao;
    }

    public void setCidadeDescricao(String cidadeDescricao) {
        this.cidadeDescricao = cidadeDescricao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
        
}