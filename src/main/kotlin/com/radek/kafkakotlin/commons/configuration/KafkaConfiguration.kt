package com.radek.kafkakotlin.commons.configuration

import com.radek.kafkakotlin.INSTANCE_ID
import com.radek.kafkakotlin.commons.messages.KafkaMessage
import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaConfiguration {
    @Value(value = "\${kafka.bootstrapAddress}")
    private val bootstrapAddress: String? = null

    @Value(value = "\${kafka.deserializer.trustedpackages}")
    private val TRUSTED_PACKGES: String? = null

    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        val configs: MutableMap<String, Any?> = HashMap()
        configs[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        return KafkaAdmin(configs)
    }

    @Bean
    fun topic(): NewTopic {
        return NewTopic("worlds", 1, 1.toShort())
    }

    @Bean
    fun producerFactory(): ProducerFactory<String, KafkaMessage>{
        val configProps: MutableMap<String, Any> = java.util.HashMap()
        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress!!
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return DefaultKafkaProducerFactory<String, KafkaMessage>(configProps)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, KafkaMessage>{
        return KafkaTemplate(producerFactory())
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String?, KafkaMessage?>? {
        val props: MutableMap<String, Any> = java.util.HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress!!
        props[ConsumerConfig.GROUP_ID_CONFIG] = INSTANCE_ID
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        props[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = false
        props[ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG] = "100"
        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        props[ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG] = "15000"
        props[JsonDeserializer.VALUE_DEFAULT_TYPE] = KafkaMessage::class.java
        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    @DependsOn("topic")
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, KafkaMessage>? {
        val factory = ConcurrentKafkaListenerContainerFactory<String, KafkaMessage>()
        factory.consumerFactory = consumerFactory()
        factory.setConcurrency(1)
        return factory
    }
}

