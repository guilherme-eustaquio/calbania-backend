package com.gems.core.enum

enum class MessageType (val type : Long) {
    SEND_MESSAGE_FROM_AIRSHIP(1),
    SEND_MESSAGE_FROM_CALBANIA(2),
    CONFIRM_MESSAGE_FROM_AIRSHIP(3),
    BROADCAST_TO_CALBANIA_CLIENTS(4)
}