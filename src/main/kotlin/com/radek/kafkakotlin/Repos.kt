package com.radek.kafkakotlin

import com.radek.kafkakotlin.model.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository: JpaRepository<Person, Long>

@Repository
interface FlatRepository: JpaRepository<Flat, Long>

@Repository
interface CityRepository: JpaRepository<City, Long>

@Repository
interface CountryRepository: JpaRepository<Country, Long>

@Repository
interface WorldRepository: JpaRepository<World, Long>