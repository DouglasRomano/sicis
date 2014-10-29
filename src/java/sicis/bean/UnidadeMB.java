package sicis.bean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import sicis.dao.GenericDAO;
import sicis.domain.Bairro;
import sicis.domain.Cidade;
import sicis.domain.Logradouro;
import sicis.domain.Unidade;
import sicis.manipuladores.Manipulador;

@ManagedBean
@ViewScoped
public class UnidadeMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Unidade unidade;
    private Cidade cidade;
    private Bairro bairro;
    private Logradouro logradouro;

    private Manipulador manipulador;
    private GenericDAO genericDAO;

    private List<Unidade> listUnidades;
    private Unidade unidadeSelecionada;

    private String opcaoHeader;
    
    private boolean selectBtnSearchCidade = false;
    private boolean selectBtnSearchBairro = false;
    private boolean selectBtnSearchLogradouro = false;

    private transient RequestContext requestContext;
    private transient FacesContext facesContext;

    @PostConstruct
    public void UnidadeMB() {

        unidadeSelecionada = new Unidade();
        opcaoHeader = new String();
        
        unidade = new Unidade();
        cidade = new Cidade();
        bairro = new Bairro();
        logradouro = new Logradouro();

        manipulador = new Manipulador();
        genericDAO = new GenericDAO();

        listarUnidades();
    }
    
    public String headerSaveDialog() {        
        return manipulador.inserirHeader(opcaoHeader);
    }

    public void listarUnidades() {
        try {
            listUnidades = genericDAO.listAll(Unidade.class);
        } catch (Exception ex) {
            Logger.getLogger(UnidadeMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchCidade() {
        facesContext = FacesContext.getCurrentInstance();
        requestContext = RequestContext.getCurrentInstance();
        List<Cidade> listCidade;
        try {
            listCidade = genericDAO.searchObject(Cidade.class,
                    new String[]{"cidadeDescricao", "estado"},
                    new Object[]{cidade.getCidadeDescricao(), cidade.getEstado()});
            selectBtnSearchCidade = true;

            if (listCidade.isEmpty()) {
                opcaoHeader = "cadastrar cidade";

                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cidade não encontrada", "Cadastre-a para prosseguir!"));
                requestContext.getCurrentInstance().execute("PF('cadastrarDialog').show();");

            } else if (listCidade.size() == 1) {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cidade encontrada", "Prossiga o cadastro!"));
                cidade = listCidade.get(0);
            } else {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Informe mais detalhes!", "Sua busca retornou muitos registros"));
            }
        } catch (Exception ex) {
            
        }

    }

    public void searchBairro() {

        if (selectBtnSearchCidade == true && this.cidade.getCidade_id() != null) {

            facesContext = FacesContext.getCurrentInstance();
            requestContext = RequestContext.getCurrentInstance();

            List<Bairro> listBairro;
            try {
                bairro.setCidade(cidade);
                listBairro = genericDAO.searchObject(Bairro.class,
                        new String[]{"cidade", "bai_descricao"},
                        new Object[]{bairro.getCidade(), "%" + bairro.getBai_descricao() + "%"});
                selectBtnSearchBairro = true;

                if (listBairro.isEmpty()) {
                    opcaoHeader = "cadastrar bairro";

                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bairro não encontrado", "Cadastre-o para prosseguir!"));
                    requestContext.getCurrentInstance().execute("PF('cadastrarDialog').show();");
                } else if (listBairro.size() == 1) {
                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bairro encontrado", "Prossiga o cadastro!"));
                    bairro = listBairro.get(0);
                } else {
                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Informe mais detalhes!", "Sua busca retornou muitos registros"));
                }

            } catch (Exception ex) {
                
            }
        } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alterta!", "Verifique a Cidade para prosseguir"));
        }

    }

    public void searchLogradouro() {
        facesContext = FacesContext.getCurrentInstance();
        requestContext = RequestContext.getCurrentInstance();

        if (selectBtnSearchBairro == true && this.bairro.getCidade() != null) {
            List<Logradouro> listLogradouro;
            try {

                logradouro.setLog_bairro_id(this.bairro);
                listLogradouro = genericDAO.searchObject(Logradouro.class,
                        new String[]{"log_bairro_id", "log_descricao"},
                        new Object[]{logradouro.getLog_bairro_id(), "%" + logradouro.getLog_descricao() + "%"});
                selectBtnSearchLogradouro = true;
               
                if (listLogradouro.isEmpty()) {
                    opcaoHeader = "cadastrar logradouro";

                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Logradouro não encontrado", "Cadastre-o para prosseguir!"));
                    requestContext.getCurrentInstance().execute("PF('cadastrarDialog').show();");
                } else if (listLogradouro.size() == 1) {
                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logradouro encontrado", "Prossiga o cadastro!"));
                    logradouro = listLogradouro.get(0);
                } else {
                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Informe mais detalhes!", "Sua busca retornou muitos campos"));
                }
            } catch (Exception ex) {
                Logger.getLogger(UnidadeMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alterta!", "Verifique o Bairro primeiramente"));
        }

    }

    public void save() {
        if (selectBtnSearchLogradouro == true) {

            facesContext = FacesContext.getCurrentInstance();

            try {
                unidade.setLocal_log_id(logradouro);
                genericDAO.save(unidade);
            } catch (Exception ex) {
                Logger.getLogger(UnidadeMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Verifique o Logradouro para prosseguir"));
        }

    }

    public void salvarEndereco() {
        switch (opcaoHeader) {
            case "cadastrar cidade":
                try {
                    genericDAO.save(cidade);
                    searchCidade();
                } catch (Exception ex) {
                    Logger.getLogger(UnidadeMB.class.getName()).log(Level.SEVERE, null, ex);
                }   break;
            case "cadastrar bairro":
                try {
                    genericDAO.save(bairro);
                    searchBairro();
                } catch (Exception ex) {
                    Logger.getLogger(UnidadeMB.class.getName()).log(Level.SEVERE, null, ex);
            }   break;
            case "cadastrar logradouro":
                try {
                    genericDAO.save(logradouro);
                    searchLogradouro();
                } catch (Exception ex) {
                    Logger.getLogger(UnidadeMB.class.getName()).log(Level.SEVERE, null, ex);
            }   break;
        }
        facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Salvo com Sucesso","Prossiga"));
    }

    // Getters and Setters
    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Unidade getUnidadeSelecionada() {
        return unidadeSelecionada;
    }

    public void setUnidadeSelecionada(Unidade unidadeSelecionada) {
        this.unidadeSelecionada = unidadeSelecionada;
    }

    public List<Unidade> getListUnidades() {
        return listUnidades;
    }

    public void setListUnidades(List<Unidade> listUnidades) {
        this.listUnidades = listUnidades;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }
}
