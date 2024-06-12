package com.example.tasker.data.models

import java.sql.Date
import java.util.UUID

data class Tasks (
    var title: String,
    var description: String,
    var status: Status,
    var icon: String,
    var dueDate: Date,
    var userId: UUID
)