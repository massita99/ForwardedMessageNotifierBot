package com.massita.sevices.commands;

import com.massita.coreapi.NotifyType;

import java.util.concurrent.Future;


/**
 * {@link MessageCommandService} delcare method to work with All available Commands
 * of Notifier
 */
public interface MessageCommandService {

    /** Method send command to create Message*/
    Future<String> createMessage(String messageId, String chatId);

    /** Method scheduled message for notify*/
    Future<Void> scheduleMessage(String messageId, NotifyType time);

    /** Method notify user by controller about scheduled message*/
    Future<Void> notifyAboutMessage(String messageId);
}
