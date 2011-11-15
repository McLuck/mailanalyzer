

package br.com.mailanalyzer.testes;
import org.htmlparser.beans.StringBean;
import org.htmlparser.util.ParserException;
import org.htmlparser.Parser;
import org.htmlparser.beans.HTMLTextBean;
import org.htmlparser.visitors.TextExtractingVisitor;
import org.htmlparser.parserapplications.filterbuilder.FilterBuilder;
import org.htmlparser.beans.FilterBean;

public class testhtml {

    public static void main(String args[]) throws Exception
    {
        String html = "<div class='chapeu'><a name='vC-duasfotos1' href='http://noticias.uol.com.br/album/110507_album.jhtm?fotoNav=7'>China</a></div> ";
        System.out.println(getHTMLContentsAsText(html));
    }
    static public String getHTMLContentsAsText(String html) throws ParserException
    {
        Parser parse = new Parser();
        parse.setInputHTML(html);
        TextExtractingVisitor visitor = new TextExtractingVisitor();
        parse.visitAllNodesWith(visitor);
        String content = (visitor.getExtractedText());
       
        return content;
    }
    }
