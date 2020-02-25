package com.radek.kafkakotlin

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
@KafkaListener(topics = ["worlds"], containerFactory = "kafkaListenerContainerFactory")
class WorldKafkaListener{

    val logger: Logger = LoggerFactory.getLogger("KAFKA_LISTENER_LOGGER")


    @KafkaHandler
    fun consumeWorld(event: WorldPersistenceEvent){
        logger.info("Event received")
        logger.info("Paylod: ${event.payload}")
        //repo save
    }
}