package com.massita.coreapi

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDateTime

data class CreateMessageCommand(@TargetAggregateIdentifier val messageId: String, val chatId: String)
data class ToScheduleMessageCommand(@TargetAggregateIdentifier val messageId: String)
data class ScheduleMessageCommand(@TargetAggregateIdentifier val messageId: String, val time: NotifyType)
data class NotifyAboutMessageCommand(@TargetAggregateIdentifier val messageId: String)

data class MessageCreatedEvent(val messageId: String, val chatId: String)
data class MessageSentToScheduleEvent(val messageId: String, val chatId: String)
data class MessageScheduledEvent(val messageId: String, val chatId: String, val time: NotifyType)
data class MessageAboutNotifiedEvent(val messageId: String, val chatId: String)

data class DatePeriodMessagesQuery(val start:LocalDateTime, val finish:LocalDateTime)
