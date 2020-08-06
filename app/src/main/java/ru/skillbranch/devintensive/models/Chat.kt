package ru.skillbranch.devintensive.models

class Chat(
    val int: String,
    val members: MutableList<User> = mutableListOf(),
    val messages: MutableList<BaseMessage> = mutableListOf()
) {
    //TODO create the chat class
}
