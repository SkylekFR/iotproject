package com.example.iotproject.model

import com.google.firebase.database.IgnoreExtraProperties
import java.util.*

@IgnoreExtraProperties
data class Wine(
    var name: String? = null,
    var creator: String? = null,
    var date: Date? = null,
    var type: String? = null
    )
