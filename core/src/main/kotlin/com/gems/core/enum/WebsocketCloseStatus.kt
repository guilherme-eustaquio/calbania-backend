package com.gems.core.enum

enum class WebsocketCloseStatus(val code : Int) {
    CLOSE_NORMAL(1000),
    CLOSE_GOING_AWAY(1001),
    CLOSE_PROTOCOL_ERROR(1002),
    CLOSE_UNSUPPORTED(1003),
    CLOSE_NO_STATUS(1005),
    CLOSE_ABNORMAL(1006),
    CLOSE_UNSUPPORTED_DATA(1007)
}