appMain.service("Usuario", ["Restangular", "CONST", function (Restangular, CONST) {

                this.buscarLogado = function () {
                        return Restangular.one(CONST.url + "api/usuario/logado" +getParamCache()).get();
                }
        }
]);
