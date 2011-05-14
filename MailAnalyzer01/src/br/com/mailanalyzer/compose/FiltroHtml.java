package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;
import br.com.mailanalyzer.fluxo.MutableComponent;
import br.com.mailanalyzer.fluxo.PropertyRetriever;
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

    public void execute() {
        try {
            msg = getValidText(msg);
        } catch (ParserException ex) {
            Logger.getLogger(FiltroHtml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean stopFlow() {
        return false;
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
        this.msg = (String) obj;
    }
    private String msg;
}
