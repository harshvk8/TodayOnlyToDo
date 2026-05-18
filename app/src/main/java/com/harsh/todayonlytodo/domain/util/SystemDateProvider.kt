package com.harsh.todayonlytodo.domain.util

import java.time.LocalDate

class SystemDateProvider : DateProvider {

    override fun today(): String {
        return LocalDate.now().toString()
    }
}