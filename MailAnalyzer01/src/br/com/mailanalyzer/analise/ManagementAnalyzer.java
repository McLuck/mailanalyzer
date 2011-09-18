/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.analise;

import br.com.mailanalyzer.dao.ComposicaoDAO;
import br.com.mailanalyzer.dao.RaizDAO;
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
        String assunto1 = "teste1";
        mapa.put(assunto1, new Raiz());
        RaizDAO rdao = new RaizDAO();
        List<Raiz> lista = rdao.obterTodos();
        for(Raiz r : lista){
            mapa.put(r.getName(), r);
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
        ComposicaoDAO cdao = new ComposicaoDAO();
        Object o = cdao.salvar(composicaoComum);
        cdao.commit();
        if(o instanceof Composicao){
            composicaoComum = (Composicao)o;
        }else if(o instanceof Integer){
            int id = (Integer)o;
            composicaoComum.setId((Integer)o);
        }

        //Add na lista para assuntos a serem carregados posteriormente
        composicoesComuns.add(composicaoComum);

        //Carrega em todas as Raizes ativas
        Set<String> keys = mapa.keySet();
        for(String key : keys){
            mapa.get(key).append(composicaoComum);
        }
    }

    
}
