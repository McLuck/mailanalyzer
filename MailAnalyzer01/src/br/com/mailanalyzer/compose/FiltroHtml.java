package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;
import br.com.mailanalyzer.fluxo.MutableComponent;
import br.com.mailanalyzer.fluxo.PropertyRetriever;
import br.com.mailanalyzer.log.Log;
import br.com.mailanalyzer.main.Base;
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

    public void execute() {
        try {
            Log.d(this.getClass().getSimpleName(), "Executando...");
            if (msg == null) {
                stop = true;
                return;
            }
            msg = getValidText(msg);

        } catch (Exception e) {
            Log.d(this.getClass().getSimpleName(), e);
        }
        Log.d(this.getClass().getSimpleName(), "Finalizado.");
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
