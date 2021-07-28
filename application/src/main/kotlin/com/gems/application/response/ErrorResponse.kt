package com.gems.application.response

data class ErrorResponse(var message : String, var status : Int = 500)