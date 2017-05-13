appMain.controller("AppController", function ($location, $rootScope, Seguranca) {
        var ctrl = this;
        
        ctrl.selecionarUsuario = function(id){
                $location.path("/chat/").search({usuarioId: id});
        }

        ctrl.init = function () {
                $location.path("/chat/");
        };

        ctrl.logout = function(){
        	Seguranca.logout();
        }
        
        ctrl.init();
});

