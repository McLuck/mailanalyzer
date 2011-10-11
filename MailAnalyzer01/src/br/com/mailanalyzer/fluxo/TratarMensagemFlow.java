package br.com.mailanalyzer.fluxo;

import br.com.mailanalyzer.commands.CommandFluxo;
import br.com.mailanalyzer.commands.MensagemTratadaCommand;
import br.com.mailanalyzer.compose.FiltroConvertMinusculo;
import br.com.mailanalyzer.compose.FiltroCorrigirOrtografia;
import br.com.mailanalyzer.compose.FiltroHtml;
import br.com.mailanalyzer.compose.FiltroSubstituirCaracter;
import br.com.mailanalyzer.compose.FiltroSubstituirGirias;
import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.main.Base;
import br.com.mailanalyzer.main.Config;

/**
 *
 * 
 * @author Lobos
 */
public class TratarMensagemFlow extends Fluxo {

    public static final String TAG = "Fluxo de Tratar Mensagem";

    @Override
    public CommandFluxo getCommandFluxo() {
        return new MensagemTratadaCommand();
    }

    public TratarMensagemFlow(Message m) {
        super(Base.TRATAR_MENSAGEM_FLOW, new InterfaceComposeFlow[]{
                    new FiltroHtml(),
                    new FiltroConvertMinusculo(),
                    new FiltroSubstituirGirias(),
                    new FiltroSubstituirCaracter(),
                    new FiltroCorrigirOrtografia()
                });
        firstObject = m;
        if(!Config.isNivelLogBaixo()){
            L.d(TAG, this, "Passou pelo Fluxo de Tratar Mensagem");
        }
    }
}
