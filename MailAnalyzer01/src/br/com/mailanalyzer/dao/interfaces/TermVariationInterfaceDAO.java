package br.com.mailanalyzer.dao.interfaces;

import br.com.mailanalyzer.domain.TermVariation;
import java.util.List;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 28-04-2011
 *
 */
public interface TermVariationInterfaceDAO extends BaseInterfaceDAO<TermVariation> {
    /**
     * Recupera uma lista de TermVariation buscando por nome parcial.
     * 
     * @param nome Nome parcial para busca
     * @return lista de TermVariation
     */
    List<TermVariation> getLikeName(String nome);
    
    /**
     * Recupera uma lista de TermVariation buscando por variacoes parciais.
     * 
     * @param variations Variacoes parcial para busca
     * @return lista de TermVariation
     */
    List<TermVariation> getLikeVariations(String variations);
    
    /**
     * Recupera uma lista de TermVariation buscando por replacers parcial.
     * 
     * @param replacers Subistitutos parcial para busca em campo de termos replacers
     * @return lista de TermVariation
     */
    List<TermVariation> getLikeReplacers(String replacers);
}
