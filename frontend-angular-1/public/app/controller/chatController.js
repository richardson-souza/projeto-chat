(function () {
        'use strict';

        appMain.controller("ChatController", function ($routeParams, Chat, Usuario) {
                var ctrl = this;
                limparTela(ctrl);

                ctrl.enviarMensagem = function(){
                        Chat.enviarMensagem( ctrl.form.mensagem );
                }

                ctrl.init = function () {
                        ctrl.usuarioId = $routeParams.usuarioId;
                        console.log("usu "+ctrl.usuarioId)
                };
                ctrl.init();
                limparMensagens(ctrl);
        });

        function limparTela(ctrl) {
                ctrl.usuario = {};
                ctrl.form = {};
        }
})();