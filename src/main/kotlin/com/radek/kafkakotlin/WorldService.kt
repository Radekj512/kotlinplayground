package com.radek.kafkakotlin

import com.radek.kafkakotlin.generator.WorldGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WorldService @Autowired constructor(val worldGenerator: WorldGenerator,
                                          val worldRepository: WorldRepository) {

    fun createNew(){
        val save = worldRepository.save(worldGenerator.generateWorld())
        println(save)
    }

}