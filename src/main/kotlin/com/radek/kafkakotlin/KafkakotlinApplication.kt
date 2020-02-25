package com.radek.kafkakotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.ZonedDateTime
import java.util.*

val MAX_COUNTRIES: Int? = 1
val MIN_FLAT_AREA: Int? = 1
val MAX_FLAT_AREA: Int? = 50
val MAX_PEOPLE_IN_FLAT: Int? = 5
val MIN_FLATS_NUMBER: Int? = 5
val MAX_FLATS_NUMBER: Int? = 10
val MIN_CITIES_NUMBER: Int? = 1
val MAX_CITIES_NUMBER: Int? = 2

const val PERSISTENCE_LOG = "persistence_log"
val INSTANCE_ID = Base64.getEncoder()
        .encodeToString((ZonedDateTime.now().toString() + UUID.randomUUID().toString()).toByteArray())
@SpringBootApplication
class KafkakotlinApplication {
}

fun main(args: Array<String>) {
    runApplication<KafkakotlinApplication>(*args)
}
