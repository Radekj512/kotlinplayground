package com.radek.kafkakotlin.generator

import com.github.javafaker.Faker
import com.radek.kafkakotlin.*
import com.radek.kafkakotlin.model.*
import org.apache.kafka.common.metrics.stats.Count
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.stream.IntStream
import kotlin.random.Random
import kotlin.streams.toList


@Component
class Util(private val personRepository: PersonRepository,
           private val flatRepository: FlatRepository,
           private val cityRepository: CityRepository,
           private val countryRepository: CountryRepository,
           private val worldRepository: WorldRepository) {

    fun getObj(obj: Any): Any {
        return when (obj) {
            is Person -> this.personRepository.save(obj)
            is Flat -> flatRepository.save(obj)
            is City -> cityRepository.save(obj)
            is Country -> countryRepository.save(obj)
            is World -> worldRepository.save(obj)
            else -> throw IllegalArgumentException("Wrong object");
        }
    }

    fun getRandomCountryList(): List<Country> =
            IntStream.rangeClosed(0, MAX_COUNTRIES!!)
                    .mapToObj { CountryGenerator(this).getRandomCountry() }
                    .toList()

}

val rnd = Random
val gen: Faker = Faker()

@Component
class PersonGenerator(private val util: Util) {


    fun getPerson(): Person = util.getObj(
            Person(null,
                    generateFirstName(),
                    generateLastName(),
                    generateAge())
    ) as Person

    private fun generateFirstName() = gen.name().firstName()
    private fun generateLastName() = gen.name().lastName()
    private fun generateAge() = rnd.nextInt(0, 100)
}

@Component
class FlatGenerator(private val util: Util) {
    fun getFlat(): Flat = util.getObj(
            Flat(null,
                    getRandomArea(),
                    getRandomPeopleList())
    ) as Flat

    private fun getRandomPeopleList(): List<Person> =
            IntStream.range(0, rnd.nextInt(MAX_PEOPLE_IN_FLAT!!) + 1)
                    .mapToObj { PersonGenerator(util).getPerson() }
                    .toList()

    private fun getRandomArea(): Int = rnd.nextInt(MIN_FLAT_AREA!!, MAX_FLAT_AREA!!)

}

@Component
class CityGenerator(private val util: Util) {


    fun getRandomCity() = util.getObj(
            City(null,
                    getCityName(),
                    getListOfFlats())
    ) as City

    fun getRandomCapital() = util.getObj(
            City(null,
                    getCapital(),
                    getListOfFlats())
    ) as City

    private fun getListOfFlats(): List<Flat> =
            IntStream.range(0, rnd.nextInt(MAX_FLATS_NUMBER!!) + MIN_FLATS_NUMBER!!)
                    .mapToObj { FlatGenerator(util).getFlat() }
                    .toList()

    private fun getCapital() = gen.country().capital()

    private fun getCityName(): String = gen.address().city()
}

@Component
class CountryGenerator(private val util: Util) {

    fun getRandomCountry(): Country = util.getObj(
            Country(null,
                    gen.country().name(),
                    CityGenerator(util).getRandomCapital(),
                    getCities())
    ) as Country

    private fun getCities(): List<City> =
            IntStream.range(0, rnd.nextInt(MAX_CITIES_NUMBER!!) + MIN_CITIES_NUMBER!!)
                    .mapToObj { CityGenerator(util).getRandomCity() }
                    .toList()
}

@Component
class WorldGenerator(private val util: Util) {

    fun generateWorld() = util.getObj(
            World(null,
                    util.getRandomCountryList())
    ) as World
}