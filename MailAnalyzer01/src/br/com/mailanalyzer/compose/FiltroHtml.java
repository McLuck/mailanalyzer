package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;
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
public class FiltroHtml implements InterfaceComposeFlow {

    public void execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean stopFlow() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public FiltroHtml() {
    }
    
    public String getValidText(String html) throws ParserException{
        Parser parse = new Parser();
        parse.setInputHTML(html);
        TextExtractingVisitor visitor = new TextExtractingVisitor();
        parse.visitAllNodesWith(visitor);
        String content = (visitor.getExtractedText());

        return content;
    }
        
    }

