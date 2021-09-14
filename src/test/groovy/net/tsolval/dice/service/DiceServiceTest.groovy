package net.tsolval.dice.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DiceServiceTest {

    @Autowired
    DiceService diceService

    @Test
    void testRollBasicDie() {
        assert diceService.roll("6d6").every {it in 1..6}
    }
}
