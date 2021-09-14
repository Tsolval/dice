package net.tsolval.dice.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DiceService {
    @Autowired
    Random random

    def roll(String dieString) {
        def die = dieString.split('d')
        def num = die[0] as int
        def sides = die[1] as int
        [1..num].collect {random.nextInt(sides)+1}
    }
}
