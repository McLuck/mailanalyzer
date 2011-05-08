package br.com.mailanalyzer.dao.actions;

import br.com.mailanalyzer.dao.TermVariationDAO;
import br.com.mailanalyzer.domain.TermVariation;
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
public class ActionTermVariation {

    private TermVariation variation;

    /**
     * @return the variation
     */
    public TermVariation getVariation(TermVariation variat) {
        TermVariationDAO dao = new TermVariationDAO();
        this.variation = variat;
        variation = dao.obter(variation.getId());

        System.out.println("----- Recuperando pela ID: " + variation.getId());
        System.out.println("Nome: " + variation.getName());
        System.out.println("Alterador: " + variation.getReplacer());
        System.out.println("Gírias: " + variation.getVariations());
        System.out.println("-----");
        return variation;
    }

    /**
     * @param variation the variation to set
     */
    public void setVariation(TermVariation variation) {
        this.variation = variation;
    }

    public void Salvar() {
        System.out.println("Cadastrando...");
        TermVariationDAO dao = new TermVariationDAO();
        variation.setDataAlteracao(new java.util.Date().getTime());
        dao.salvar(variation);

        System.out.println(variation.getName() + " foi salvo com sucesso. ID do objeto: " + variation.getId() + " - Na data: " + Converte.ToStringDataVisual(variation.getDataRegistro()) + " - " + Converte.DateToStringTimer(variation.getDataRegistro()));
        System.out.println("---");
    }

    public void alterar() {
        System.out.println("Alterando...");
        TermVariationDAO dao = new TermVariationDAO();
        dao.atualizar(variation);
        System.out.println(variation.getName() + " foi salvo com sucesso. ID do objeto: " + variation.getId() + " - Na data: " + Converte.ToStringDataVisual(variation.getDataRegistro()) + " - " + Converte.DateToStringTimer(variation.getDataRegistro()));
        System.out.println("---");
    }

    public void excluir() {
        System.out.println("Excluindo...");
        TermVariationDAO dao = new TermVariationDAO();
        dao.excluir(variation);
        System.out.println(variation.getName() + " foi excluido com sucesso.");
        System.out.println("---");
    }

    public List<TermVariation> buscarName(String str) {
        System.out.println("Buscando por " + str + "...");
        TermVariationDAO dao = new TermVariationDAO();
        List<TermVariation> lista = dao.getLikeName(str);
        for (TermVariation a : lista) {
            System.out.println("ID: " + a.getId() + " - Nome: " + a.getName() + " - Alterador: " + a.getReplacer() + " - Gírias: " + a.getVariations());
        }
        System.out.println("---");
        return lista;
    }

    public List<TermVariation> buscarReplacers(String str) {
        System.out.println("Buscando por " + str + "...");
        TermVariationDAO dao = new TermVariationDAO();
        List<TermVariation> lista = dao.getLikeReplacers(str);
        for (TermVariation a : lista) {
            System.out.println("ID: " + a.getId() + " - Nome: " + a.getName() + " - Alterador: " + a.getReplacer() + " - Gírias: " + a.getVariations());
        }
        System.out.println("---");
        return lista;
    }

    public List<TermVariation> buscarVariations(String str) {
        System.out.println("Buscando por " + str + "...");
        TermVariationDAO dao = new TermVariationDAO();
        List<TermVariation> lista = dao.getLikeVariations(str);
        for (TermVariation a : lista) {
            System.out.println("ID: " + a.getId() + " - Nome: " + a.getName() + " - Alterador: " + a.getReplacer() + " - Gírias: " + a.getVariations());
        }
        System.out.println("---");
        return lista;
    }

    public List<TermVariation> showAll() {
        System.out.println("Exibir tudo ...");
        TermVariationDAO dao = new TermVariationDAO();
        List<TermVariation> lista = dao.obterTodos();
        for (TermVariation a : lista) {
            System.out.println("ID: " + a.getId() + " - Nome: " + a.getName() + " - Alterador: " + a.getReplacer() + " - Gírias: " + a.getVariations());
        }
        System.out.println("---");
        return lista;
    }
}
