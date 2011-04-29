package br.com.mailanalyzer.dao.interfaces;

import java.util.List;
import br.com.mailanalyzer.domain.DomainObject;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 28-04-2011
 *
 */
public interface BaseInterfaceDAO<T extends DomainObject> {

    /** Quantidade de registros por pagina��o */
    Integer QTD_REGISTROS_PAGINA = 9;

    /**
     * Atualiza um objeto na base de dados.
     * 
     * @param obj objeto persistente a atualizar
     */
    void atualizar(T obj);

    /**
     * Apaga um objeto na base de dados.
     * 
     * @param obj objeto persistente a apagar
     */
    void excluir(T obj);

    /**
     * Recupera um objeto a partir da sua chave prim�ria.
     * 
     * @param pk chave prim�ria do objeto persistente
     * 
     * @return objeto recuperado
     */
    T obter(Integer pk);

    /**
     * Recupera um objeto a partir da sua chave prim�ria. <br />
     * obs: o objeto recuperado ser� a partir de uma sess�o nova do hibernate, ignorando as altera��es j�
     * feitas no objeto da sess�o atual
     * 
     * @param pk chave prim�ria do objeto persistente
     * 
     * @return objeto recuperado
     */
    T obterAntigo(Integer pk);

    /**
     * Recupera todos os objetos.
     * 
     * @return um {@link List} de {@link DomainObject}
     */
    List<T> obterTodos();
}
