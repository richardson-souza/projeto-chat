appMain.service("Chat", ["Restangular", "CONST", "Seguranca", function (Restangular, CONST, Seguranca) {

        var socket1;
        var socket2;
        var stompClient1;
        var stompClient2;
        var objHeader;

        this.registrar = function(callbackChat, callbackUsuario){
            socket1 = new SockJS(CONST.url + "api/endpoint");
            socket2 = new SockJS(CONST.url + "api/endpoint");
            stompClient1 = Stomp.over(socket1);
            stompClient2 = Stomp.over(socket2);

            objHeader = {token:Seguranca.getToken()};

            stompClient1.connect(objHeader, function (frame) {
                    stompClient1.subscribe('/subscribe/canal/usuario', function (data) {
                        callbackUsuario(JSON.parse(data.body));
                    }, objHeader);
            });

            stompClient2.connect(objHeader, function (frame) {
                    stompClient2.subscribe('/subscribe/canal/chat', function (data) {
                        callbackChat(JSON.parse(data.body));
                    }, objHeader);
            });
        }

        this.desconectar = function(){
                if (stompClient1 != null) {
                        stompClient1.disconnect();
                }
                if (stompClient2 != null) {
                        stompClient2.disconnect();
                }
                console.log("Disconnected");
        }

        this.enviarMensagem = function(mensagem){
            stompClient2.send("/client/chat/enviar", objHeader, JSON.stringify({ 'mensagem': mensagem}) );
        }

	}
]);