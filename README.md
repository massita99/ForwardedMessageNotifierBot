# ForwardedMessageNotifierBot
The Telegram Bot based on Event Sourcing architecture that notify about all messaged that user sent it

### Quick start

`mvn spring-boot:run -Dtelegram.proxy.enabled=false -Dtelegram.bot.username=<your bot name from BotFather> -Dtelegram.bot.token=<yout token from BotFather> -Dmongo.embedded=provided`

All parameter list:
* `telegram.proxy.enabled` - enable proxy if Telegram blocked on your server
* `telegram.proxy.host` - if proxy enabled then proxy host
* `telegram.proxy.port` - if proxy enabled then proxy port
* `telegram.proxy.type` - if proxy enabled then proxy type ex: HTTP/SOCKS4/SOCKS5
* `telegram.bot.username` - name of your bot. should get from @BotFather
* `telegram.bot.token` - token of your bot. should get from @BotFather
* `mongo.embedded` - *provided* if you prefer use embedded mongo/*test* if you had mongo server

### Current main bot use case:
1. User send or forward message to the bot
2. Bot ask user to schedule notification by reply on received message
3. After user scheduled notification bot send schedule confirmation
4. Bot notify about message at notification date as replied message 

### Used Technologies
* https://axoniq.io - CQRS Event Sourcing framework as base
* https://github.com/rubenlagus/TelegramBots - TelegramBot java library to interact with bot
* https://spring.io/projects/spring-boot - stand-alone App container
* https://www.mongodb.com - document-based database
* https://junit.org/junit5/ - main test framework
* https://github.com/michaelmosmann/embedmongo.flapdoodle.de - embedded MongoDB server for testing
* https://github.com/awaitility/awaitility - for asynchronous testing
* https://maven.apache.org - build automation tool

 
