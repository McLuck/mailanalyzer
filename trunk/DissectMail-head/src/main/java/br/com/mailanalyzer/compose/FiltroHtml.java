package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;
import br.com.mailanalyzer.fluxo.MutableComponent;
import br.com.mailanalyzer.fluxo.PropertyRetriever;
import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.main.Base;
import br.com.mailanalyzer.main.Config;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.htmlparser.beans.StringBean;
import org.htmlparser.util.ParserException;
import org.htmlparser.Parser;
import org.htmlparser.beans.HTMLTextBean;
import org.htmlparser.visitors.TextExtractingVisitor;
import org.htmlparser.parserapplications.filterbuilder.FilterBuilder;
import org.htmlparser.beans.FilterBean;

/**
 *
 * @author Lobo
 * @contact pedro.lobo29@gmail.com
 * @version 1.0
 * @Date 07/05/2011
 * @reviser ---
 */
public class FiltroHtml implements InterfaceComposeFlow, PropertyRetriever, MutableComponent {

    private boolean stop = false;
    private static final String TAG = "Filtro HTML";

    public void execute() {
        try {
        	if(Config.isNivelLogMaximo()){
        		L.d(TAG, this.getClass(), "Iniciando filtro HTML na mensagem: \n".concat(msg));
        	}
            if (msg == null) {
                stop = true;
                return;
            }
            msg = getValidText(msg);
            if(Config.isNivelLogMaximo()){
        		L.d(TAG, this.getClass(), "Mensagem com filtro HTML aplicado: \n".concat(msg));
        	}
        } catch (Exception e) {
            L.e("Filtro de HTML", this, "Falhou ao tentar remover HTML da mensagem", e);
        }
        
    }

    public boolean stopFlow() {
        return stop;
    }

    public FiltroHtml() {
    }

    private String getValidText(String html) throws ParserException {
        Parser parse = new Parser();
        parse.setInputHTML(html);
        TextExtractingVisitor visitor = new TextExtractingVisitor();
        parse.visitAllNodesWith(visitor);
        String content = (visitor.getExtractedText());

        return content;
    }

    public Object getPropertyName() {
        return Base.FIELD_FILTRO_HTML;
    }

    public Object getPropertyValue() {
        return msg;
    }

    public void updateComponent(Object obj) {
        if (obj instanceof String) {
            this.msg = (String) obj;
        } else if (obj instanceof Message) {
            this.msg = ((Message) obj).getMensagem();
        } else {
            this.msg = null;
        }
    }
    private String msg;
}
