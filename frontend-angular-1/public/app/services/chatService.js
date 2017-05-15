appMain.service("Chat", ["Restangular", "CONST", "Seguranca", function (Restangular, CONST, Seguranca) {
        var socket = new SockJS(CONST.url + "api/endpoint");
        var stompClient = Stomp.over(socket);
        var objHeader = {token: Seguranca.getToken()};

        stompClient.connect(objHeader, function (frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/api/subscribe/canal/chat', objHeader, function (data) {
                        console.log("Recebido: "+data);
                });
        });

        this.disconnect = function(){
                if (stompClient != null) {
                        stompClient.disconnect();
                }
                console.log("Disconnected");
        }

        this.enviarMensagem = function(mensagem){
            stompClient.send("/api/client/canal/chat", objHeader, JSON.stringify({
		        message: mensagem
	      	}) );
        }

	}
]);