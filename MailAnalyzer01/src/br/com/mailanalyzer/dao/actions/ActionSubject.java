package br.com.mailanalyzer.dao.actions;

import br.com.mailanalyzer.dao.SubjectDAO;
import br.com.mailanalyzer.domain.Subject;
import br.com.mailanalyzer.utils.Converte;
import java.util.List;


/**
 *
 * @author Guilherme Lucas
 * @contact gfaria.mello@gmail.com
 * @version 1.0
 * @Date 07/05/2011
 * @reviser ---
 *
 */
public class ActionSubject {

    private Subject subject;

    /**
     * @return the variation
     */
    public Subject getVariation(Subject subj) {
        SubjectDAO dao = new SubjectDAO();
        subj = dao.obter(subj.getId());

        this.subject = subj;
        System.out.println("----- Recuperando pela ID: " + subj.getId());
        System.out.println("Nome: " + subj.getName());
        System.out.println("Texto: " + subj.getText());
        System.out.println("CommandFlowName: " + subj.getCommandFlowName());
        System.out.println("-----");
        return subject;
    }

    /**
     * @param variation the variation to set
     */
    public void setVariation(Subject subject) {
        this.subject = subject;
    }

    public void Salvar() {
    System.out.println("Cadastrando...");
    if(subject.getId() == 0){
        subject.setDataRegistro(new java.util.Date().getTime());
    }
    SubjectDAO dao = new SubjectDAO();
    dao.salvar(subject);
    System.out.println(subject.getName() + " foi salvo com sucesso. ID do objeto: " + subject.getId() + " - Na data: " + Converte.ToStringDataVisual(subject.getDataRegistro()) + " - " + Converte.DateToStringTimer(subject.getDataRegistro()));
    System.out.println("---");
    }
     public void alterar() {
        System.out.println("Alterando...");
        SubjectDAO dao = new SubjectDAO();
        dao.atualizar(subject);
        System.out.println(subject.getName() + " foi salvo com sucesso. ID do objeto: " + subject.getId() + " - Na data: " + Converte.ToStringDataVisual(subject.getDataRegistro()) + " - " + Converte.DateToStringTimer(subject.getDataRegistro()));
        System.out.println("---");
    }

    public void excluir() {
        System.out.println("Excluindo...");
        SubjectDAO dao = new SubjectDAO();
        dao.excluir(subject);
        System.out.println(subject.getName()+" foi excluido com sucesso.");
        System.out.println("---");
    }

    public List<Subject> buscarName(String str) {
        System.out.println("Buscando por " + str + "...");
        SubjectDAO dao = new SubjectDAO();
        List<Subject> lista = dao.getLikeName(str);
        for(Subject a : lista){
        System.out.println("ID: "+a.getId()+" - Nome: "+a.getName()+" - Texto: "+a.getText()+"CommandFlowName: "+a.getCommandFlowName());
        }
        System.out.println("---");
        return lista;
    }

     public List<Subject> buscarText(String str) {
        System.out.println("Buscando por " + str + "...");
        SubjectDAO dao = new SubjectDAO();
        List<Subject> lista = dao.getLikeTexto(str);
        for(Subject a : lista){
        System.out.println("ID: "+a.getId()+" - Nome: "+a.getName()+" - Texto: "+a.getText()+"CommandFlowName: "+a.getCommandFlowName());
        }
        System.out.println("---");
        return lista;
     }

    public List<Subject> buscarCmdFlowName(String str) {
        System.out.println("Buscando por " + str + "...");
        SubjectDAO dao = new SubjectDAO();
        List<Subject> lista = dao.getByCommandFlowName(str);
        for(Subject a : lista){
        System.out.println("ID: "+a.getId()+" - Nome: "+a.getName()+" - Texto: "+a.getText()+"CommandFlowName: "+a.getCommandFlowName());
        }
        System.out.println("---");
        return lista;
    }

    public List<Subject> showAll(String str) {
        System.out.println("Buscando por " + str + "...");
        SubjectDAO dao = new SubjectDAO();
        List<Subject> lista = dao.getLikeTexto(str);
        for(Subject a : lista){
        System.out.println("ID: "+a.getId()+" - Nome: "+a.getName()+" - Texto: "+a.getText()+"CommandFlowName: "+a.getCommandFlowName());
        }
        System.out.println("---");
        return lista;
    }
}
