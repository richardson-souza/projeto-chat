package com.jopss.chat.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ConnectionFactory {

    @PersistenceContext(unitName = "main_persistenceUnit")
    protected EntityManager emWeb;
    
    public EntityManager getEmMain() {
        return emWeb;
    }
    
}