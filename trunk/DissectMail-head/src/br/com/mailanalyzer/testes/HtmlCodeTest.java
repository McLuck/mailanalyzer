/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.testes;

import br.com.mailanalyzer.dao.HtmlCodeDAO;
import br.com.mailanalyzer.domain.HtmlCode;

/**
 *
 * @author McLuck
 */
public class HtmlCodeTest {
    public static String[][] codes = {
        {"Boleto Banc�rio","<div style='border-style:inset; border-color:#98FB98; width:500px; border-width:4px;'><img src='http://www.mundopm.com.br/eventos/plm/images/logo_veris_ibta_128.jpg' alt='veris logo'><p style='float:top; font-family:verdana; font-size:20pt;'>Resposta </p><p style='font-family:Times New Roman;'>A segunda via do boleto devera ser requerida pelo portal do aluno ou diretamente na secretaria. Caso sua data de vencimento tenha passado, o sistema ir� calcular taxas de juros e multas para o novo boleto e emiti-lo com um novo vencimento. Se voc� j� efetuou o pagamento do boleto, pode ainda agilizar a baixa no sistema, enviando o seu RA junto com a autentica��o banc�ria para o e-mail boletopago@veris.edu.br</p></div>"},
        {"Informa��es sobre Matr�cula","<div style=''border-style:inset; border-color:#98FB98; width:500px; border-width:4px;''><img src=''http://www.mundopm.com.br/eventos/plm/images/logo_veris_ibta_128.jpg'' alt=''veris logo''><p style=''float:top; font-family:verdana; font-size:20pt;''>Resposta </p><p style=''font-family:Times New Roman;''>A data limite para entrega dos documentos pendentes da matricula s�o 2 meses ap�s a realiza��o da  mesma. Se os documentos n�o foram entregues a tempo, dever� comparecer a secretaria para tratar do assunto.</p></div>"},
        {"Declara��o Escolar","<div style='border-style:inset; border-color:#98FB98; width:500px; border-width:4px;'><img src='http://www.mundopm.com.br/eventos/plm/images/logo_veris_ibta_128.jpg' alt='veris logo'><p style='float:top; font-family:verdana; font-size:20pt;'>Resposta </p><p style='font-family:Times New Roman;'>A declara��o escolar pode ser requerida no portal do aluno ou diretamente na secretaria e pode ser retirada no mesmo dia na secretaria da faculdade. Se voc� j� fez o pedido da declara��o e ela ainda n�o foi feita, compare�a a secretaria.</p></div> "},
        {"Pagamento em Atraso","<div style='border-style:inset; border-color:#98FB98; width:500px; border-width:4px;'><img src='http://www.mundopm.com.br/eventos/plm/images/logo_veris_ibta_128.jpg' alt='veris logo'><p style='float:top; font-family:verdana; font-size:20pt;'>Resposta </p><p style='font-family:Times New Roman;'>Acordos de pagamento de mensalidades atrasadas ou mensalidades regulares devem negociados diretamente com o departamento financeiro na faculdade. Os pagamentos de acordos podem ser efetuados na faculdade, apenas com car�o de debito. Maiores informa��es sobre o seu acordo dever�o ser enviadas diretamente para o departamento financeiro da veris no e-mail: financeiro@veris.edu.br, ou diretamente na faculdade.</p></div> "},
        {"Acesso ao Portal do Aluno","<div style='border-style:inset; border-color:#98FB98; width:500px; border-width:4px;'><img src='http://www.mundopm.com.br/eventos/plm/images/logo_veris_ibta_128.jpg' alt='veris logo'><p style='float:top; font-family:verdana; font-size:20pt;'>Resposta </p><p style='font-family:Times New Roman;'>O acesso ao portal do aluno se da pelo seu RA que � o mesmo numero do seu CPF. Problemas com perda de senhas podem ser resolvidos diretamente na p�gina do portal clicando na op��o: Esqueci Minha Senha ou voc� pode enviar um e-mail para: recsenha@veris.edu.br com seu RA + data de nascimento. Este e-mail deve ser enviado a partir de do e-mail cadastrado em sua ficha. Neste caso, o portal ir� gerar uma senha tempor�ria para seu acesso.. Se voc�, mesmo com os dados, n�o consegue acessar o portal, favor comparecer a secretaria para melhor an�lise do problema.</p></div> "},
        {"Grade Hor�ria","<div style='border-style:inset; border-color:#98FB98; width:500px; border-width:4px;'><img src='http://www.mundopm.com.br/eventos/plm/images/logo_veris_ibta_128.jpg' alt='veris logo'><p style='float:top; font-family:verdana; font-size:20pt;'>Resposta </p><p style='font-family:Times New Roman;'>Infelizmente a grade hor�ria s� pode ser montada na secretaria por enquanto. Nossos sistemas ser�o adaptados em breve para poder proporcionar mais esta flexibilidade aos aluno. <br/>Obrigado!</p></div> "},
        {"DEFAULT","<p>Sua mensagemfoi recebida por n�s e analisada automaticamente. Se desejar que seu email n�o seja interceptado pelo analisador autom�tico, envie #TAG_REPROVA# no assunto de seu email que ele sera encaminhado para uma pessoa tratar a mensagem.</p><div>#MSG_RESPOSTA#</div><div>#MSG_RECEBIDA#</div>"}
    };
    public static void main(String...a){
        HtmlCodeDAO hdao = new HtmlCodeDAO();
        for(String[] code : codes){
            HtmlCode html = new HtmlCode();
            html.setCodigo(code[1]);
            html.setNome(code[0]);
            hdao.salvar(html);
        }
        hdao.commit();
        hdao.close();
    }
}
