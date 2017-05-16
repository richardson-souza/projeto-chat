Sistema para exemplo de chat em Java com Websocket e Angular.

# chat-backend

Feito com:
+ Java Spring Rest retornando JSon.
+ Autenticacao com OAuth2 (Token).
+ Mensageria com Spring.
+ JPA e Spring Data.
+ Migration com FlyWay.


# chat-frontend

Feito com:
+ AngularJS.
+ Bootstrap.
+ SockJS para Websocket. A vatangem de utilizar esta lib ao invés do Websocket nativo, é a compatibilidade com vários navegadores, e a automatização da troca para Long Pooling caso o WS retornar erro.

Configurar o ubuntu:

+ sudo apt-get install npm
+ sudo apt-get install nodejs-legacy

Instalar dependencias transientes:

+ sudo npm install -g node
+ sudo npm install -g grunt-cli

Na raiz do projeto:

+ Instalar dependencias do projeto: npm install
+ Rodar o servidor node: grunt
+ Acessar: http://localhost:9000/

Proxy com NPM:
+ npm config set proxy http://proxy.company.com:8080
+ npm config set https-proxy http://proxy.company.com:8080
