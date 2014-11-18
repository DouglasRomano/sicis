package sicis.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cliente implements Serializable {

   private static final long serialVersionUID = 1L;
   
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Id
   private Long cli_id;

   @JoinColumn(nullable = false, name = "cli_log_id")
   @ManyToOne
   private Logradouro cli_log_id;

   @Column(nullable = false)
   private String cli_numeroResidencial;
   
   @Column(nullable = false)
   private String cli_nome;
   
   @Temporal(TemporalType.DATE)
   @Column(nullable = false)
   private Date cli_nasc;

   @Column(nullable = false)
   private String cli_cor;
   
   @Column
   private String cli_RG;

   @Column(unique = true)
   private String cli_CPF;

   @Column(unique = true)
   private String cli_CNS;
   
   @Column(nullable = false)
   private String cli_nomedamae;

   @Column(nullable = false)
   private String cli_nomedopai;

   @Column(nullable = false)
   private String cli_sexo;

   @Column(nullable = false)
   private String cli_telefone;

   public Long getCli_id() {
      return cli_id;
   }

   public void setCli_id(Long cli_id) {
      this.cli_id = cli_id;
   }

   public Logradouro getCli_log_id() {
      return cli_log_id;
   }

   public void setCli_log_id(Logradouro cli_log_id) {
      this.cli_log_id = cli_log_id;
   }

   public String getCli_numeroResidencial() {
      return cli_numeroResidencial;
   }

   public void setCli_numeroResidencial(String cli_numeroResidencial) {
      this.cli_numeroResidencial = cli_numeroResidencial;
   }

   public String getCli_nome() {
      return cli_nome;
   }

   public void setCli_nome(String cli_nome) {
      this.cli_nome = cli_nome;
   }

   public Date getCli_nasc() {
      return cli_nasc;
   }

   public void setCli_nasc(Date cli_nasc) {
      this.cli_nasc = cli_nasc;
   }

   public String getCli_cor() {
      return cli_cor;
   }

   public void setCli_cor(String cli_cor) {
      this.cli_cor = cli_cor;
   }

   public String getCli_RG() {
      return cli_RG;
   }

   public void setCli_RG(String cli_RG) {
      this.cli_RG = cli_RG;
   }

   public String getCli_CPF() {
      return cli_CPF;
   }

   public void setCli_CPF(String cli_CPF) {
      this.cli_CPF = cli_CPF;
   }

   public String getCli_CNS() {
      return cli_CNS;
   }

   public void setCli_CNS(String cli_CNS) {
      this.cli_CNS = cli_CNS;
   }

   public String getCli_nomedamae() {
      return cli_nomedamae;
   }

   public void setCli_nomedamae(String cli_nomedamae) {
      this.cli_nomedamae = cli_nomedamae;
   }

   public String getCli_nomedopai() {
      return cli_nomedopai;
   }

   public void setCli_nomedopai(String cli_nomedopai) {
      this.cli_nomedopai = cli_nomedopai;
   }

   public String getCli_sexo() {
      return cli_sexo;
   }

   public void setCli_sexo(String cli_sexo) {
      this.cli_sexo = cli_sexo;
   }

   public String getCli_telefone() {
      return cli_telefone;
   }

   public void setCli_telefone(String cli_telefone) {
      this.cli_telefone = cli_telefone;
   }

}
