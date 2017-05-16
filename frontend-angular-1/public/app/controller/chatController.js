(function () {
        'use strict';

        appMain.controller("ChatController", function ($scope, $routeParams, Chat, Usuario, Seguranca) {
                var ctrl = this;
                limparTela(ctrl);

                ctrl.enviarMensagem = function(){
                        Chat.enviarMensagem( ctrl.form.mensagem );
                }

                function atualizacaoChat(data){
                        console.debug(" --> atualizacaoChat");

                        var tipo = data.tipoMensagem;
                        if(tipo == 'MESSAGE'){
                                //obj: {"dataMensagem":1494957490573,"mensagem":"texto da mensagem enviada","tipoMensagem":"MESSAGE","nomeUsuario":"Visitante :(","idUsuario":"2","situacaoUsuario":"ATIVO"}
                                data.mesmoUsuario = data.idUsuario == Seguranca.getLogado().id;
                                ctrl.mensagensChat = ctrl.mensagensChat.filter(item => item.dataMensagem !== data.dataMensagem)
                                ctrl.mensagensChat.push(data);
                                $scope.$apply();
                        }else if(tipo == 'OTHER'){

                        }
                }

                function atualizacaoUsuario(data){
                        console.debug(" --> atualizacaoUsuario");

                        var tipo = data.tipoMensagem;
                        if(tipo == 'SUBSCRIBE'){
                                //obj: {"dataMensagem":1494957510445, "mensagem":null,"tipoMensagem":"SUBSCRIBE","nomeUsuario":"Administrador :)","idUsuario":"1","situacaoUsuario":"ATIVO"}
                                ctrl.usuarios = ctrl.usuarios.filter(item => item.idUsuario !== data.idUsuario)
                                ctrl.usuarios.push(data);
                                $scope.$apply();
                        }
                }

                ctrl.init = function () {
                        ctrl.usuarioId = $routeParams.usuarioId;
                        Chat.registrar( atualizacaoChat, atualizacaoUsuario );
                };
                ctrl.init();
                limparMensagens(ctrl);
        });

        function limparTela(ctrl) {
                ctrl.usuario = {};
                ctrl.form = {};
                ctrl.usuarios = [];
                ctrl.mensagensChat = [];
        }
})();