/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mailanalyzer.analise2;

import br.com.mailanalyzer.analise2.adapter.RaizAdapter;
import br.com.mailanalyzer.commands.SubjectFoundCommand;
import br.com.mailanalyzer.commands.SubjectNotFoundCommand;
import br.com.mailanalyzer.dao.RaizDAO;
import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.domain.RaizDomain;
import br.com.mailanalyzer.domain.Receiver;
import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.main.Base;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * 
 * @author McLuck
 */
public class GerenciamentoAnalisador {

	private static List<Raiz> matrizes;

	/**
	 * Matrizes de assuntos ativos no sistema
	 * 
	 * @return
	 */
	public static List<Raiz> getMatrizes() {
		return matrizes;
	}

	public static void load() {
		RaizDAO rdao = new RaizDAO();
		List<RaizDomain> rs = rdao.obterTodos();
		matrizes = new ArrayList<Raiz>();
		for (RaizDomain rd : rs) {
			RaizAdapter rAdapt = new RaizAdapter(rd);
			matrizes.add(rAdapt.getRaiz());
		}
		rdao.close();
	}

	public static class Analisador {

		public final String TAG = Analisador.class.getName();
		private List<Integer> pesos;
		private Receiver receiver;
		private Raiz r;

		public Receiver getReceiver() {
			return receiver;
		}

		public void setReceiver(Receiver r) {
			this.receiver = r;
		}

		/**
		 * Analisa a mensagem e retorna o resultado mais provavel baseado nos
		 * assuntos cadastrados nas raizes do analisador.
		 * 
		 * @param msg
		 *            Objeto de Message nao nulo
		 * @return Um objeto de Raiz, caso encontre o assunto ou null caso nao
		 *         encontre
		 */
		public Raiz analisar(Message msg) {
			List<Integer> pesos = getPesos(msg.getMensagem());
			int posicao = posicaoComMaiorPossibilidade(pesos);
			if (posicao >= 0) {
				if (Raiz.VALIDAR_DIFERENCA_MINIMA) {
					if ((primeiro - segundo) >= Raiz.DIFERENCA_MINIMA) {
						if (Raiz.VALIDAR_RELEVANCIA_MINIMA
								&& (primeiro >= Raiz.RELEVANCIA_MINIMA)) {
							r = matrizes.get(posicao);
						} else {
							r = null;
							L.i(TAG,
									this,
									"Mensagem foi encontrada, mas nao atendeu requisito de relevancia minima configurado em Raiz. Relevancia da mensagem: "
											.concat(String
													.valueOf(primeiro)
													.concat(" valor para relevancia minima: ".concat(String
															.valueOf(Raiz.RELEVANCIA_MINIMA))))
											.concat(" \nO assunto mais provavel foi: "
													.concat(matrizes
															.get(posicao)
															.getAssunto()
															.getName())));
						}
					} else {
						L.i(TAG,
								this,
								"Mensagem foi encontrada, mas nao atendeu diferenca minima configurado em Raiz. Diferenca entre as relevancias das mensagens: "
										.concat(String
												.valueOf(primeiro - segundo)
												.concat(" valor para diferenca minima: ".concat(String
														.valueOf(Raiz.DIFERENCA_MINIMA))))
										.concat(" \nO assunto mais provavel foi: "
												.concat(matrizes.get(posicao)
														.getAssunto().getName())));
						r = null;
					}
				}
			} else {
				r = null;
			}
			return r;
		}

		/**
		 * Executa o comando do assunto especificado na raiz encontrada ou o
		 * comando de assunto nao encontrado.<br>
		 * Caso ja tenha sido processada, a mensagem nao sera analisada
		 * novamente. Para re-analisar a mensagem, use a funcao 'analisar' <br>
		 * Caso ainda nao tenha sido processada, a mensagem sera analisada
		 * antes.
		 * 
		 * @param msg
		 *            Objeto de Message nao nulo.
		 */
		public void run(Message msg) {
			if (r == null) {
				analisar(msg);
			}

			// Comando nao encontrado
			if (r == null) {
				SubjectNotFoundCommand sCommand = getNotFoundCommand();
				sCommand.setParameters(new Hashtable());
				sCommand.getParameters().put("msg", msg);
				sCommand.getParameters().put("receiver",
						receiver != null ? receiver : "");
				sCommand.run();
			} else {
				SubjectFoundCommand sCommand = getSubjectFound();
				if (sCommand == null) {
					L.e(TAG,
							this,
							"ERRO NA INSTANCIAÇÃO - Executando comando padrão de Assunto Não Encontrado. "
									+ "Assunto foi encontrado, mas houve erro"
									+ "ao instanciar o Command do assunto. Verificar Log do Command cadastrado: "
									+ r.getAssunto().getCommandFlowName(), null);

					SubjectNotFoundCommand sCommand2 = getNotFoundCommand();
					sCommand2.setParameters(new Hashtable());
					sCommand2.getParameters().put("msg", msg);
					sCommand2.getParameters().put("receiver", receiver);
					sCommand2.getParameters().put("assunto", r.getAssunto());
					sCommand2.run();
				} else {
					try {
						sCommand.setParameters(new Hashtable());
						sCommand.getParameters().put("msg", msg);
						sCommand.getParameters().put("receiver", receiver);
						sCommand.getParameters().put("assunto", r.getAssunto());
						sCommand.run();
					} catch (Exception e) {
						L.e("Analisador",
								this,
								"Erro ao executar comando de assunto encontrado.",
								e);
					}
					try {
						r.autoAprender(msg.getMensagem());
					} catch (Exception e) {
						L.e("Analisador", this,
								"Erro ao aprender com mensagem recebida", e);
					}
				}
			}
		}

		private List<Integer> getPesos(String comparador) {
			// Monta resultados
			pesos = new ArrayList<Integer>();
			if (matrizes == null) {
				load();
			}
			for (Raiz rz : matrizes) {
				pesos.add(rz.getRelevancia(comparador));
			}
			return (pesos);
		}

		int primeiro = 0, segundo = 0;

		private int posicaoComMaiorPossibilidade(List<Integer> results) {
			int maior = 0;
			int posicao = -1;
			for (int i = 0; i < results.size(); i++) {
				if (results.get(i) > maior) {
					posicao = i;
					segundo = maior;
					maior = results.get(i);
					primeiro = maior;
				}
			}
			return posicao;
		}

		/**
		 * Devolve instancia de Command padrão para assunto não encontrado
		 * 
		 * @return Um objeto de SubjectNotFoundCommand
		 */
		private SubjectNotFoundCommand getNotFoundCommand() {
			return Base.NOT_FOUND_COMMAND;
		}

		/**
		 * Instancia o Command definido pelo assunto com reflection
		 * 
		 * @return Um objeto de SubjectFoundCommand
		 */
		private SubjectFoundCommand getSubjectFound() {
			SubjectFoundCommand sCommand = null;
			try {
				Class classDefinition = Class.forName(r.getAssunto()
						.getCommandFlowName());
				sCommand = (SubjectFoundCommand) classDefinition.newInstance();
			} catch (InstantiationException e) {
				L.e(TAG, this,
						"Erro ao instanciar a classe especificada no assunto.",
						e);
			} catch (IllegalAccessException e) {
				L.e(TAG, this,
						"Erro no acesso da classe especificada no assunto.", e);
			} catch (ClassNotFoundException e) {
				L.e(TAG,
						this,
						"A classe especificada no assunto não existe ou não está disponível no local informado.",
						e);
			}
			return sCommand;
		}
	}
}
