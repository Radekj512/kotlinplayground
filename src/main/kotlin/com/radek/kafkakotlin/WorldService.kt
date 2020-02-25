package com.radek.kafkakotlin

import com.radek.kafkakotlin.generator.WorldGenerator
import com.radek.kafkakotlin.commons.messages.KafkaMessage
import com.radek.kafkakotlin.model.World
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class WorldService(private val worldGenerator: WorldGenerator,
                   private val worldRepository: WorldRepository,
                   private val kafkaTemplate: KafkaTemplate<String, KafkaMessage>) {

    val logger: Logger = LoggerFactory.getLogger("WorldServiceLogger")

    fun createNew() {
        val save = worldRepository.save(worldGenerator.generateWorld())
        val event: WorldPersistenceEvent = WorldPersistenceEvent("user", save, "newWorld")
        kafkaTemplate.send("worlds",event)
        logger.info("World with id = ${save.id} has been created")
    }

    fun getAll(): MutableList<World> {
        logger.info("Downloading all worlds")
        return worldRepository.findAll();
    }

}