INSERT INTO permissao(id, dataatualizacao, datacriacao, nome, papel, descricao, permissao_pai_id)
    VALUES (1, '2015-08-18', null, 'Permissão Geral.', 'ROLE_GERAL', 'Permissão geral da aplicação. Todos os perfis devem ter ao menos essa permissão.', null);

INSERT INTO permissao(id, dataatualizacao, datacriacao, nome, papel, descricao, permissao_pai_id)
    VALUES (2, '2015-08-18', null, 'Permissão de Visitante.', 'ROLE_VISITANTE', 'Usuário sem permissão para gerenciar uma aposta.', null);

INSERT INTO permissao(id, dataatualizacao, datacriacao, nome, papel, descricao, permissao_pai_id)
    VALUES (3, '2015-08-18', null, 'Permissão de Administrador.', 'ROLE_ADMIN', 'Usuário com permissão geral.', null);


INSERT INTO perfil(id, dataatualizacao, datacriacao, nome, descricao)
    VALUES (1, '2015-08-18', null, 'Perfil Administrador', 'Perfil com permissão geral.');

INSERT INTO perfil(id, dataatualizacao, datacriacao, nome, descricao)
    VALUES (2, '2015-08-18', null, 'Perfil Visitante', 'Perfil sem permissão para gerenciar uma aposta.');


INSERT INTO perfil_permissao(perfil_id, permissoes_id) VALUES (1, 1);
INSERT INTO perfil_permissao(perfil_id, permissoes_id) VALUES (1, 3);

INSERT INTO perfil_permissao(perfil_id, permissoes_id) VALUES (2, 1);
INSERT INTO perfil_permissao(perfil_id, permissoes_id) VALUES (2, 2);


INSERT INTO usuario(id, datacriacao, dataatualizacao, nome, login, senha, perfil_id)
    VALUES (1, '2015-07-28', null, 'Administrador :)', 'admin', '21232f297a57a5a743894a0e4a801fc3', 1);

INSERT INTO usuario(id, datacriacao, dataatualizacao, nome, login, senha, perfil_id)
    VALUES (2, '2015-07-28', null, 'Visitante :(', 'visitante', 'cab5a031f5506b862b7487f987edbd68', 2);


INSERT INTO hibernate_sequences( sequence_name, sequence_next_hi_value) VALUES ('Perfil',3);
INSERT INTO hibernate_sequences( sequence_name, sequence_next_hi_value) VALUES ('Permissao',4);
INSERT INTO hibernate_sequences( sequence_name, sequence_next_hi_value) VALUES ('Usuario',3);
