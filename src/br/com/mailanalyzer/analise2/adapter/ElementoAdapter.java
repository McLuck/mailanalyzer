
package br.com.mailanalyzer.analise2.adapter;

import br.com.mailanalyzer.analise2.Raiz;
import br.com.mailanalyzer.analise2.Elemento;
import br.com.mailanalyzer.dao.ElementoDAO;
import br.com.mailanalyzer.dao.PalavraDAO;
import br.com.mailanalyzer.domain.ComposicaoDomain;
import br.com.mailanalyzer.domain.ElementoDomain;


/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 25-09-2011
 */
public class ElementoAdapter {
    private ElementoDomain dominio;
    private Elemento logica;

    public ElementoAdapter(ElementoDomain dominio){
        this.dominio = dominio;
        logica = new Elemento();
        logica.setId(dominio.getId());
        logica.setPalavra(dominio.getPalavra().getPalavra(), Raiz.CARREGAR_SINONIMOS);
        logica.setPeso(dominio.getPeso());
    }

    public ElementoAdapter(Elemento elemento, ComposicaoDomain pai){
        this.logica = elemento;
        this.dominio = new ElementoDomain();
        dominio.setId(elemento.getId());
        dominio.setPalavra(PalavraDAO.getInstance().getPalavra(elemento.getPalavra()));
        dominio.setPeso(elemento.getPeso());
        dominio.setPai(pai);
        dominio.setDataRegistro(new java.util.Date().getTime());
    }

    public ElementoDomain getDominio(){
        return dominio;
    }
    public Elemento getElemento(){
        return logica;
    }
}
