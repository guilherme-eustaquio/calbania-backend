package com.gems.core.domain

data class Command(
    var id : String?,
    var timestamp : String?,
    var type : Long?,
    var action : Long
)