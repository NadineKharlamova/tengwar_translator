package com.tengwar.translator.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class DirectionTest {

    @Test
    fun reversedFlipsDirection() {
        assertEquals(Direction.SINDARIN_TO_RUSSIAN, Direction.RU_TO_SINDARIN.reversed)
        assertEquals(Direction.RU_TO_SINDARIN, Direction.SINDARIN_TO_RUSSIAN.reversed)
    }
}
