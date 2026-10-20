package com.tengwar.translator.domain

/** Translation direction of the app. */
enum class Direction {
    /** Russian -> English -> Sindarin. */
    RU_TO_SINDARIN,

    /** Sindarin -> English -> Russian. */
    SINDARIN_TO_RUSSIAN;

    val reversed: Direction
        get() = if (this == RU_TO_SINDARIN) SINDARIN_TO_RUSSIAN else RU_TO_SINDARIN
}
