package sicis.bean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sicis.dao.GenericDAO;
import sicis.domain.Permissao;

@ManagedBean
@ViewScoped
public class PermissaoMB implements Serializable{
  
	private static final long serialVersionUID = 1L;
	
	private Permissao permissao;
    
    private List <Permissao> listaDePermissoes;
    private Permissao permissaoSelecionada;
    
    private GenericDAO genericDAO;
    
    @PostConstruct
    public void PermissaoMB(){
        genericDAO = new GenericDAO();
        listPermissoes();
    }
    
    public void saveOrUpdate(){
        try {
            genericDAO.save(permissao);
        } catch (Exception ex) {
            Logger.getLogger(PermissaoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Permissao> listPermissoes(){
        listaDePermissoes = null;
        try {
            listaDePermissoes = genericDAO.listAll(Permissao.class);
        } catch (Exception ex) {
            Logger.getLogger(PermissaoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaDePermissoes;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public List<Permissao> getListaDePermissoes() {
        return listaDePermissoes;
    }

    public void setListaDePermissoes(List<Permissao> listaDePermissoes) {
        this.listaDePermissoes = listaDePermissoes;
    }

    public Permissao getPermissaoSelecionada() {
        return permissaoSelecionada;
    }

    public void setPermissaoSelecionada(Permissao permissaoSelecionada) {
        this.permissaoSelecionada = permissaoSelecionada;
    }

 
    
    
    
    
}

