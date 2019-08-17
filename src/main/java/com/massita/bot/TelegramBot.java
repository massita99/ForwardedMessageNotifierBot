package com.massita.bot;

import com.google.common.base.Strings;
import com.massita.coreapi.ReturnResultEventEvent;
import com.massita.coreapi.StartEventEvent;
import com.massita.services.commands.GameCommandService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Flag;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.telegram.abilitybots.api.objects.Flag.CALLBACK_QUERY;
import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

@Controller
@Profile("telegram")
public class TelegramBot extends AbilityBot {


    private final GameCommandService gameCommandService;


    public TelegramBot(@Autowired GameCommandService gameCommandService,
                       DefaultBotOptions botOptions,
                       @Value("${telegram.bot.token}") String botToken,
                       @Value("${telegram.bot.username}") String botUsername) {
        super(botToken, botUsername, botOptions);
        this.gameCommandService = gameCommandService;
    }


    @Override
    public int creatorId() {
        return 507144449;
    }

    public Ability receiveMessage() {
        return Ability.builder()
                .name(DEFAULT)
                .flag(Flag.MESSAGE.or(CALLBACK_QUERY))
                .privacy(PUBLIC)
                .locality(ALL)
                .input(0)
                .action(this::handleInputMessage)
                .build();
    }

    public Ability newGame() {
        return Ability.builder()
                .name("newGame")
                .privacy(PUBLIC)
                .locality(ALL)
                .input(0)
                .action(ctx -> gameCommandService.createGame(ctx.chatId().toString()))
                .build();
    }

    private void handleInputMessage(MessageContext ctx) {

        if (ctx.update().hasMessage()) {
            if (ctx.update().getMessage().getText().contains("new")) {
                gameCommandService.createGame(ctx.chatId().toString());
            }
        }
        if (ctx.update().hasCallbackQuery()) {
            gameCommandService.processEvent(
                    ctx.update().getCallbackQuery().getMessage().getChatId().toString(),
                    ctx.update().getCallbackQuery().getData());
            return;
        }
    }


    @EventHandler
    public void on(StartEventEvent event) throws TelegramApiException {
        if (!Strings.isNullOrEmpty(event.getPhoto())) {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(event.getPhoto()).getFile());

            SendPhoto photo = new SendPhoto()
                    .setChatId(Long.parseLong(event.getChatId()))
                    .setPhoto(file);

            sender.sendPhoto(photo);
        }

        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(Long.parseLong(event.getChatId()))
                .setText(event.getDescription())
                .enableMarkdown(true);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        for (String text : event.getActions()) {
            rowsInline.add(List.of(new InlineKeyboardButton().setText(text).setCallbackData(text)));
        }

        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        sender.execute(message);
    }


    @EventHandler
    public void on(ReturnResultEventEvent event) throws TelegramApiException {

        SendMessage statMessage = new SendMessage() // Create a message object object
                .setChatId(Long.parseLong(event.getChatId()))
                .setText(event.getStats())
                .enableMarkdown(true);

        sender.execute(statMessage);
    }


}
