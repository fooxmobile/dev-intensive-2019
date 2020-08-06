package ru.skillbranch.devintensive.models

import java.util.*

abstract class BaseMessage (val id: String, val from: User?,
val chat: Chat, val isIncoming: Boolean = false, val date: Date = Date()) {

abstract fun formatMessage() : String

    companion object AbstractFactory {
        private var lastId: Int = -1

        fun makeMessage(
            from: User?,
            chat: Chat,
            isIncoming: Boolean = false,
            date: Date = Date(),
            payload: Any?,
            type: String
        ): BaseMessage {
            lastId++
            return when (type) {
                "image" -> ImageMessage("$lastId", from, chat, isIncoming, date, payload as String)
                else -> TextMessage("$lastId", from, chat, isIncoming, date, payload as String)
            }

        }
    }
}
