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
public class Logradouro implements Serializable{
  
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long log_id;
    
    @JoinColumn(name = "log_bai_id", nullable = false)
    @ManyToOne
    private Bairro log_bairro_id;
    
    @Column(nullable = false)
    private String log_CEP;
    
    @Column(nullable = false)
    private String log_descricao;
    
    
    public Long getLog_id() {
        return log_id;
    }

    public void setLog_id(Long log_id) {
        this.log_id = log_id;
    }

    public Bairro getLog_bairro_id() {
        return log_bairro_id;
    }

    public void setLog_bairro_id(Bairro log_bairro_id) {
        this.log_bairro_id = log_bairro_id;
    }

    public String getLog_CEP() {
        return log_CEP;
    }

    public void setLog_CEP(String log_CEP) {
        this.log_CEP = log_CEP;
    }

    public String getLog_descricao() {
        return log_descricao;
    }

    public void setLog_descricao(String log_descricao) {
        this.log_descricao = log_descricao;
    }

     
    
    
}
