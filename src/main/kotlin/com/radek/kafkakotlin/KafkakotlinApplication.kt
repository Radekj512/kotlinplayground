package com.radek.kafkakotlin

import com.google.gson.Gson
import com.radek.kafkakotlin.generator.PersonGenerator
import com.radek.kafkakotlin.generator.WorldGenerator
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.util.ResourceUtils
import java.io.File

@SpringBootApplication
class KafkakotlinApplication


val MAX_COUNTRIES: Int? = 1
val MIN_FLAT_AREA: Int? = 1
val MAX_FLAT_AREA: Int? = 50
val MAX_PEOPLE_IN_FLAT: Int? = 5
val MIN_FLATS_NUMBER: Int? = 5
val MAX_FLATS_NUMBER: Int? = 10
val MIN_CITIES_NUMBER: Int? = 1
val MAX_CITIES_NUMBER: Int? = 2



fun main(args: Array<String>) {

    val gson = Gson()

    val text = gson.toJson(WorldGenerator().generateWorld())
    File("world.json").printWriter().use { out ->
        out.println(text)

    }

    //runApplication<KafkakotlinApplication>(*args)
}
