package com.massita.bot;

import com.massita.sevices.commands.MessageCommandService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import static com.massita.coreapi.NotifyType.TOMORROW;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Test checks how {@link TelegramBot} handle in messages
 */
@ExtendWith(MockitoExtension.class)
public class TelegramBotTest {

    private static final long CHAT_ID = 1337L;
    private static final Integer MESSAGE_ID = 1;

    private static TelegramBot bot;
    private static MessageCommandService messageCommandService;

    @Mock
    private Update updWithMsg;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Update updWithCallBack;
    @Mock
    private Message msg;

    @BeforeAll
    public static void mainSetUp() {
        // Create bot and mock update
        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
        messageCommandService = mock(MessageCommandService.class);
        bot = new TelegramBot(messageCommandService, botOptions, "test", "test");
    }


    @Test
    public void receiveMessage() {
        User endUser = new User();

        //Plain message mock
        when(msg.getMessageId()).thenReturn(MESSAGE_ID);
        when(updWithMsg.getMessage()).thenReturn(msg);
        when(updWithMsg.hasMessage()).thenReturn(true);

        MessageContext context = MessageContext.newContext(updWithMsg, endUser, CHAT_ID, "123");

        // We consume a context in the lamda declaration, so we pass the context to the action logic
        bot.receiveMessage().action().accept(context);

        // We verify that the sender was called only ONCE
        // The sender here is a mock!
        Mockito.verify(messageCommandService, times(1)).createMessage(anyString(), anyString());
    }

    @Test
    public void receiveCallback() {
        User endUser = new User();
        //Calback Mock
        when(updWithCallBack.hasCallbackQuery()).thenReturn(true);
        when(updWithCallBack.getCallbackQuery().getMessage().getReplyToMessage().getMessageId()).thenReturn(MESSAGE_ID);
        when(updWithCallBack.getCallbackQuery().getData()).thenReturn(TOMORROW.toString());

        MessageContext context = MessageContext.newContext(updWithCallBack, endUser, CHAT_ID, "123");

        bot.receiveMessage().action().accept(context);

        Mockito.verify(messageCommandService, times(1)).scheduleMessage(anyString(), any());
    }
}