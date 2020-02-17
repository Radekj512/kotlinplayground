package com.radek.kafkakotlin

import com.google.gson.Gson
import com.radek.kafkakotlin.generator.WorldGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.io.File

@Component
data class Boostrap @Autowired constructor(val worldGenerator: WorldGenerator) {

    @Bean
    fun createWorld() {
        val gson = Gson()

        val text = gson.toJson(worldGenerator.generateWorld())
        File("world.json").printWriter().use { out ->
            out.println(text)
        }
    }
}