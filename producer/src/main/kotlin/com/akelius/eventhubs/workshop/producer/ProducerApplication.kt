package com.akelius.eventhubs.workshop.producer

import com.fasterxml.jackson.databind.ObjectMapper
import mu.KLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

@SpringBootApplication
@EnableScheduling
class ProducerApplication

fun main(args: Array<String>) {
    runApplication<ProducerApplication>(*args)
}

@Component
class NewInvestmentSource(
        private val kafkaTemplate: KafkaTemplate<String, String>,
        private val objectMapper: ObjectMapper
) {
    companion object : KLogging()

    @Scheduled(fixedDelay = 30000)
    fun sendNewInvestmentEvent() {
        logger.info { "Sending new investment event..." }

        val newInvestmentEvent =
                NewInvestmentEvent(UUID.randomUUID(), "ELECTRICAL")
        val sendResult =
                kafkaTemplate.sendDefault(objectMapper.writeValueAsString(newInvestmentEvent))

        sendResult.addCallback({ result -> logger.info { "success" } },
                { ex -> logger.info { "failure" } })
    }
}

data class NewInvestmentEvent(
        val eventId: UUID,
        val type: String
)