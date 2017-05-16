appMain.controller("AppController", function ($location, $rootScope, Seguranca, Chat) {
        var ctrl = this;
        
        ctrl.selecionarUsuario = function(id){
                $location.path("/chat/").search({usuarioId: id});
        }

        ctrl.init = function () {
                $location.path("/chat/");
        };

        ctrl.logout = function(){
                Chat.desconectar();
        	Seguranca.logout();
        }
        
        ctrl.init();
});

