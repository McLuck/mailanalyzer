/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.analise;

import br.com.mailanalyzer.dao.ComposicaoDAO;
import br.com.mailanalyzer.dao.RaizDAO;
import br.com.mailanalyzer.domain.RaizDomain;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author McLuck
 */
public class ManagementAnalyzer {
    private ManagementAnalyzer(){}
    
    //Repositorio de assuntos
    private static Map<String, Raiz> mapa = new HashMap<String, Raiz>();
    private static Set<Composicao> composicoesComuns;

    public static void LoadAssuntos(){
        RaizDAO rdao = new RaizDAO();
        List<RaizDomain> lista = rdao.obterTodos();
        for(RaizDomain r : lista){
            RaizAdapter adapter = new RaizAdapter(r);
            mapa.put(adapter.getRaiz().getName(), adapter.getRaiz());
        }
    }
    public static Set<Composicao> getComposicoesComuns(){
        return composicoesComuns;
    }

    /**
     * Add composicoes comuns em todas as Raizes
     * @param composicaoComum Objeto de composicao nao nulo.
     */
    public static void addComposicaoComum(Composicao composicaoComum){
        //Salvar composicao
        ComposicaoDAO cdao = new ComposicaoDAO();
        //cdao.salvar(composicaoComum);
        //Add na lista para assuntos a serem carregados posteriormente
        composicoesComuns.add(composicaoComum);

        //Carrega em todas as Raizes ativas
        Set<String> keys = mapa.keySet();
        for(String key : keys){
            mapa.get(key).append(composicaoComum);
        }
    }

    
}
