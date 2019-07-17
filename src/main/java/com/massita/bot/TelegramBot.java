package com.massita.bot;

import com.massita.coreapi.MessageAboutNotifiedEvent;
import com.massita.coreapi.MessageSentToScheduleEvent;
import com.massita.coreapi.NotifyType;
import com.massita.sevices.commands.MessageCommandService;
import lombok.Setter;
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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

import static com.massita.coreapi.NotifyType.*;
import static org.telegram.abilitybots.api.objects.Flag.CALLBACK_QUERY;
import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

@Controller
@Profile("telegram")
public class TelegramBot extends AbilityBot implements MessageSender {

    @Autowired
    @Setter
    private MessageCommandService messageCommandService;


    public TelegramBot(DefaultBotOptions botOptions,
                       @Value("${telegram.bot.token}") String botToken,
                       @Value("telegram.bot.username") String botUsername) {
        super(botToken, botUsername, botOptions);
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
                .action(ctx -> handleInputMessage(ctx))
                .build();
    }

    private void handleInputMessage(MessageContext ctx) {
        if (ctx.update().hasMessage()) {
            messageCommandService.createMessage(
                    ctx.update().getMessage().getMessageId().toString(),
                    ctx.chatId().toString());
            return;
        }
        if (ctx.update().hasCallbackQuery()) {
            messageCommandService.scheduleMessage(
                    ctx.update().getCallbackQuery().getMessage().getReplyToMessage().getMessageId().toString(),
                    NotifyType.valueOf(ctx.update().getCallbackQuery().getData()))
                    ;
            return;
        }
    }


    @Override
    public void on(MessageSentToScheduleEvent event) throws TelegramApiException {
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(Long.parseLong(event.getChatId()))
                .setReplyToMessageId(Integer.valueOf(event.getMessageId()))
                .setText("Please schedule notification")
                .enableMarkdown(true);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> firstRowInline = new ArrayList<>();
        firstRowInline.add(new InlineKeyboardButton().setText(NEVER.toString()).setCallbackData(NEVER.toString()));
        firstRowInline.add(new InlineKeyboardButton().setText(TOMORROW.toString()).setCallbackData(TOMORROW.toString()));

        List<InlineKeyboardButton> secondRowInline = new ArrayList<>();
        secondRowInline.add(new InlineKeyboardButton().setText(NEXT_WEEK.toString()).setCallbackData(NEXT_WEEK.toString()));
        secondRowInline.add(new InlineKeyboardButton().setText(NEXT_MONTH.toString()).setCallbackData(NEXT_MONTH.toString()));

        // Set the keyboard to the markup
        rowsInline.add(firstRowInline);
        rowsInline.add(secondRowInline);
        // Add it to the message
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        sender.execute(message);
    }

    @Override
    public void on(MessageAboutNotifiedEvent event) throws TelegramApiException {
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(Long.parseLong(event.getChatId()))
                .setReplyToMessageId(Integer.valueOf(event.getMessageId()))
                .setText("Kindly notify you")
                .enableMarkdown(true);
        sender.execute(message);
    }

}
