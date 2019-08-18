package com.massita.coreapi

import com.massita.coreapi.game.Resource
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateGameCommand(@TargetAggregateIdentifier val chatId: String)
data class StartEventCommand(@TargetAggregateIdentifier val chatId: String, val description: String, val actions: Map<String, String>, val photo: String)
data class ProcessEventCommand(@TargetAggregateIdentifier val chatId: String, val answerText: String)
data class UpdateStatsCommand(@TargetAggregateIdentifier val chatId: String, val stats: Map<Resource, Int>)
data class ResetStatsCommand(@TargetAggregateIdentifier val chatId: String)
data class ReturnResultEventCommand(@TargetAggregateIdentifier val chatId: String, val resultText: String, val stats: String)

data class CreateGameEvent(val chatId: String)
data class StartEventEvent(val chatId: String, val description: String, val actions: Map<String, String>, val photo: String)
data class ProcessEventEvent(val chatId: String, val answerText: String)
data class UpdateStatsEvent(val chatId: String, val stats: Map<Resource, Int>)
data class ResetStatsEvent(val chatId: String)
data class ReturnResultEventEvent(val chatId: String, val resultText: String, val stats: String)


data class PathQuery(val chatId: String)
data class PlayerQuery(val chatId: String)


