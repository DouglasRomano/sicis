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
import sicis.dao.GenericDAO;
import sicis.domain.Cliente;
import sicis.domain.Permissao;
import sicis.domain.Profissional;
import sicis.domain.Unidade;
import sicis.manipuladores.Manipulador;

@ManagedBean
@ViewScoped
public class NovoProfissionalMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
    private Profissional profissional; 
    
    private String parametroBusca;
    private String parametroValor;
  
    private Unidade unidadeSelecionada;
    private Permissao permissaoSelecionada;
    
    private Manipulador manipulador;
    private GenericDAO genericDAO;
    
    private transient FacesContext facesContext;
    
    @PostConstruct
    public void novoProfissional(){
        
        cliente = new Cliente();
        profissional = new Profissional();
        
        parametroBusca = new String();
        parametroValor = new String();
        
        manipulador = new Manipulador();
        genericDAO = new GenericDAO();
    }

    public String selecionarMask(){
        return manipulador.selectMask(parametroBusca);
    }
      
    public void searchCliente(){
        facesContext = FacesContext.getCurrentInstance();        
        
        List<Cliente> listCliente;
        
        try {            
            listCliente = genericDAO.searchObject(Cliente.class, new String[]{parametroBusca}, 
                    new Object[]{parametroValor});
            
            if(listCliente.isEmpty()){
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Paciente não encontrado",
                        "Não foram encontrados registros os dados informados"));
            }
            else cliente = listCliente.get(0);
            
        } catch (Exception ex) {
            //
        }
    }

    // Getters and Setters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getParametroBusca() {
        return parametroBusca;
    }

    public void setParametroBusca(String parametroBusca) {
        this.parametroBusca = parametroBusca;
    }
    
    public void save(){
        profissional.setCliente(cliente);
        profissional.setPermissao(permissaoSelecionada);
        profissional.setUnidade(unidadeSelecionada);
        try {
            genericDAO.save(profissional);
        } catch (Exception ex) {
            Logger.getLogger(NovoProfissionalMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public String getParametroValor() {
        return parametroValor;
    }

    public void setParametroValor(String parametroValor) {
        this.parametroValor = parametroValor;
    }

    public Unidade getUnidadeSelecionada() {
        return unidadeSelecionada;
    }

    public void setUnidadeSelecionada(Unidade unidadeSelecionada) {
        this.unidadeSelecionada = unidadeSelecionada;
    }

    public Permissao getPermissaoSelecionada() {
        return permissaoSelecionada;
    }

    public void setPermissaoSelecionada(Permissao permissaoSelecionada) {
        this.permissaoSelecionada = permissaoSelecionada;
    }
    
    
}
