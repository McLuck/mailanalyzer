/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.utils;

import br.com.mailanalyzer.domain.Palavra;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas Israel
 */
public class ListPalavra extends ArrayList<Palavra>{
    public ListPalavra(List<Palavra> palavras){
        super(palavras.size());
        this.addAll(palavras);
    }

    public ListPalavra(){
        super();
    }

    public ListPalavra(int i){
        super(i);
    }

    @Override
    public boolean contains(Object o){
        if(o!=null && o instanceof String){
            Palavra p = new Palavra();
            p.setPalavra(String.valueOf(o));
            return super.contains(p);
        }
        return super.contains(o);
    }
}
