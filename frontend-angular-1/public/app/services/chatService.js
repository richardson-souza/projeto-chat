appMain.service("Chat", ["Restangular", "CONST", "Seguranca", function (Restangular, CONST, Seguranca) {

        var socket;
        var stompClient;
        var objHeader;

        this.init = function(){
            socket = new SockJS(CONST.url + "api/endpoint");
            stompClient = Stomp.over(socket);
            objHeader = {token:Seguranca.getToken()};

            stompClient.connect(objHeader, function (frame) {
                    console.log('Connected: ' + frame);
                    stompClient.subscribe('/subscribe/canal/chat', function (data) {
                            console.log("Recebido: "+data);
                    }, objHeader);
            });
        }

        this.disconnect = function(){
                if (stompClient != null) {
                        stompClient.disconnect();
                }
                console.log("Disconnected");
        }

        this.enviarMensagem = function(mensagem){
            stompClient.send("/client/mensagem/enviar", objHeader, JSON.stringify({ 'mensagem': mensagem}) );
        }

	}
]);