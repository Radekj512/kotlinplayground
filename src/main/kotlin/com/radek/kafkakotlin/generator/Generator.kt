package com.radek.kafkakotlin.generator

import com.radek.kafkakotlin.*
import com.radek.kafkakotlin.model.*
import java.util.stream.IntStream
import kotlin.random.Random
import kotlin.streams.toList

fun <T> getObj(obj: T): T {
    return obj
}

fun getRandomCountryList(): List<Country> =
        IntStream.rangeClosed(0, MAX_COUNTRIES!!)
                .mapToObj { CountryGenerator().getRandomCountry() }
                .toList()


fun getWorld() = World(getRandomCountryList())
val rnd = Random


class PersonGenerator {
    val firstNames = javaClass::class.java.getResource("/dataSource/first-names.txt").readText().lines()
    val lastNames = javaClass::class.java.getResource("/dataSource/names.txt").readText().lines()
    fun getPerson(): Person = getObj(
            Person(
                    generateFirstName(),
                    generateLastName(),
                    generateAge()
            )
    )

    private fun generateFirstName() = firstNames[rnd.nextInt(0, firstNames.size)]
    private fun generateLastName() = lastNames[rnd.nextInt(0, firstNames.size)]
    private fun generateAge() = rnd.nextInt(0, 100)
}

class FlatGenerator {
    fun getFlat(): Flat = Flat(getRandomArea(), getRandomPeopleList())

    private fun getRandomPeopleList(): List<Person> =
            IntStream.range(1, rnd.nextInt(MAX_PEOPLE_IN_FLAT!!))
                    .mapToObj { PersonGenerator().getPerson() }
                    .toList()

    private fun getRandomArea(): Int = rnd.nextInt(MIN_FLAT_AREA!!, MAX_FLAT_AREA!!)

}

class CityGenerator {

    val cities = javaClass::class.java.getResource("/dataSource/miasta.txt").readText().lines()

    fun getRandomCity(capital: String?) =
            City(capital ?: getCityName(), getListOfFlats())

    private fun getListOfFlats(): List<Flat> =
            IntStream.range(MIN_FLATS_NUMBER!!, MIN_FLATS_NUMBER!! + rnd.nextInt(MAX_FLATS_NUMBER!!))
                    .mapToObj { FlatGenerator().getFlat() }
                    .toList()


    private fun getCityName(): String =
            cities[rnd.nextInt(cities.size)]
}

class CountryGenerator {
    val countriesAndCapitals = javaClass::class.java.getResource("/dataSource/countriesAndCapitals.txt")
            .readText().lines().stream().map { line ->
                line.split(" - ")
                        .let { Pair(it[0], it.getOrNull(1)) }
            }.toList()
    val cities = javaClass::class.java.getResource("/dataSource/miasta.txt")


    fun getRandomCountry(): Country = Country(getNameAndCapital(), getCities())

    private fun getCities(): List<City> =
            IntStream.range(MIN_CITIES_NUMBER!!, MIN_CITIES_NUMBER + rnd.nextInt(MAX_CITIES_NUMBER!!))
                    .mapToObj { CityGenerator().getRandomCity(null) }
                    .toList()


    private fun getNameAndCapital(): Pair<String, City> {
        val randomPair = countriesAndCapitals[rnd.nextInt(countriesAndCapitals.size)]

        fun getCapital(name: String?): City =
                CityGenerator().getRandomCity(name)

        return Pair(randomPair.first, getCapital(randomPair.second))
    }
}

class WorldGenerator {
    fun generateWorld() = getRandomCountryList()

}