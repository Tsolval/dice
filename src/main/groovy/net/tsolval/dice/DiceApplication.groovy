package net.tsolval.dice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class DiceApplication {
    static void main(String[] args) {
        SpringApplication.run(DiceApplication.class, args)
    }

    @Bean
    Random getRandom() {
        new Random()
    }
}
