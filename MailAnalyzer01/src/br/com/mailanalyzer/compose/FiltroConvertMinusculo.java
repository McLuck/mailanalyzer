
package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;

/**
 *
 * @author Lobo
 * @contact pedro.lobo29@gmail.com
 * @version 1.0
 * @Date 07/05/2011
 * @reviser ---
 */
public class FiltroConvertMinusculo implements InterfaceComposeFlow{

    public void execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean stopFlow() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //Converte toda String para minusculo.
    public String parse(String txt){
    return txt.toLowerCase();
    }

    
}
