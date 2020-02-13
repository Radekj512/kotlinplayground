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

@Value("\${max.country.count}")
val MAX_COUNTRIES: Int? = 3

@Value("\${min.flat.area}")
val MIN_FLAT_AREA: Int? = 10

@Value("\${max.flat.area}")
val MAX_FLAT_AREA: Int? = 50

@Value("\${max.people.in.flat}")
val MAX_PEOPLE_IN_FLAT: Int? = 6

@Value("\${min.flats.in.city}")
val MIN_FLATS_NUMBER: Int? = 10

@Value("\${max.flats.in.city}")
val MAX_FLATS_NUMBER: Int? = 15

@Value("\${min.cities.number}")
val MIN_CITIES_NUMBER: Int? = 5

@Value("\${max.cities.number}")
val MAX_CITIES_NUMBER: Int? = 10

@Value("\${countries.count}")
val NUMBER_OF_COUNTRIES: Int? = 5


fun main(args: Array<String>) {

    val gson = Gson()

    val text = gson.toJson(WorldGenerator().generateWorld())
    File("world.json").printWriter().use { out ->
        out.println(text)

    }

//    print(text)
//    file.writeText(text)

    //runApplication<KafkakotlinApplication>(*args)
}
