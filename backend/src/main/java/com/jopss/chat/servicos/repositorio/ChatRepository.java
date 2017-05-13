package com.jopss.chat.servicos.repositorio;

import com.jopss.chat.modelos.Chat;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<Chat, Long> {
        
}
