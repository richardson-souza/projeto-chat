package com.jopss.chat.web.util;

import com.jopss.chat.excecoes.ChatException;
import com.jopss.chat.servicos.ParametrosSistema;
import com.jopss.chat.servicos.security.SegurancaServico;
import com.jopss.chat.util.NumbersUtils;
import com.jopss.chat.web.form.Resposta;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.PropertyValuesEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * Superclasse de todo Controlador do sistema.
 */
public abstract class ChatAppController {
	
	protected Logger log = Logger.getLogger(this.getClass());
	
        @Autowired
        protected ParametrosSistema parametrosSistema;
        
        @Autowired
        protected SegurancaServico segurancaServico;
        
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(BigDecimal.class, new PropertyValuesEditor() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(NumbersUtils.convertStringToBigDecimal(text));
			}
			@Override
			public String getAsText() {
				return NumbersUtils.convertBigDecimalToStringFormat((BigDecimal) getValue());
			}
		});
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}
        
	@ExceptionHandler(value = { Exception.class })
	public Resposta handleConflict(Exception e, HttpServletResponse response) {
		Resposta resposta = new Resposta();
		if (e == null) {
                        resposta.addErro("NullPointerException interno...", response);
		} 
                else if (e instanceof TransactionSystemException){
                        log.error(e);
                        resposta.addErros((TransactionSystemException)e, response);
                }
                else if (e instanceof ChatException){
                        log.error(e);
                        resposta.addValidacao(e.getMessage(), response);
                }
                else {
                        log.error(e);
                        resposta.addErroGenerico(e, response);
		}
		return resposta;
	}
        
}
