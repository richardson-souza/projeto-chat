package com.jopss.chat.servicos.security;

import com.jopss.chat.modelos.SegurancaAPI;
import com.jopss.chat.util.DateUtilsApostas;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.LinkedMultiValueMap;

public class SegurancaAPIThreadLocal {
        private static Logger log = Logger.getLogger(SegurancaAPIThreadLocal.class);
	private static final Map<String, SegurancaAPI> cache = new ConcurrentHashMap<String, SegurancaAPI>();

        static{
                expire();
        }
        
	public static void add(SegurancaAPI segurancaAPI) {
                String token = segurancaAPI.getToken();
		remove(token);
		cache.put(token, segurancaAPI);
	}

        public static void remove(String token) {
		cache.remove( token );
	}
        
	public static SegurancaAPI getSegurancaAPI(MessageHeaders messageHeaders) {
		return cache.get( getTokenWS(messageHeaders) );
	}
        
        public static SegurancaAPI getSegurancaAPI(HttpServletRequest req) {
		return cache.get( getTokenHTTP(req) );
	}
        
        private static String getTokenWS(MessageHeaders messageHeaders){
                LinkedMultiValueMap lista = (LinkedMultiValueMap) messageHeaders.get("nativeHeaders");
                String token = (String) lista.getFirst("token");
                return token!=null ? token.trim() : token;
        }
        
        private static String getTokenHTTP(HttpServletRequest request) {
                try {
                        OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(request, ParameterStyle.HEADER);
                        String token = oauthRequest.getAccessToken();
                        return token!=null ? token.trim() : token;
                } catch (OAuthSystemException | OAuthProblemException ex) {
                        throw new RuntimeException(ex);
                }
        }
        
        private static void expire(){
                Runnable cleaner = new Runnable() {
                        @Override
                        public void run() {
                                for(SegurancaAPI s : cache.values()){
                                        if(DateUtilsApostas.getDiferencaHoras(s.getExpiracaoToken(), new Date()) > 1){
                                                remove(s.getToken());
                                        }
                                }
                        }
                };
                log.info("ScheduledThreadPool: remover cache apos 1 hora de inutilizacao do token.");
                Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(cleaner, 0, 1, TimeUnit.HOURS);
        }
}