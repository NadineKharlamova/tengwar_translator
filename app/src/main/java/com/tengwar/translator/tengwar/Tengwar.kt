package com.tengwar.translator.tengwar

/**
 * Tengwar characters in the ConScript Unicode Registry (CSUR) private use area.
 *
 * The mapping follows the Free Tengwar Font Project mapping
 * (https://freetengwar.sourceforge.net/mapping.html) and is fully supported by the
 * bundled "Alcarin Tengwar" font (SIL OFL 1.1).
 */
object Tengwar {

    // --- Tengwar (consonants) -------------------------------------------------
    const val TINCO = '\uE000'          // t
    const val PARMA = '\uE001'          // p
    const val CALMA = '\uE002'          // ch
    const val QUESSE = '\uE003'         // k / c
    const val ANDO = '\uE004'           // d
    const val UMBAR = '\uE005'          // b
    const val ANGA = '\uE006'           // j
    const val UNGWE = '\uE007'          // g
    const val THUULE = '\uE008'         // th
    const val FORMEN = '\uE009'         // f / ph
    const val HARMA = '\uE00A'          // sh
    const val HWESTA = '\uE00B'         // ch (Scottish loch)
    const val ANTO = '\uE00C'           // dh
    const val AMPA = '\uE00D'           // v
    const val ANCA = '\uE00E'           // zh
    const val UNQUE = '\uE00F'          // gh
    const val NUUMEN = '\uE010'         // n
    const val MALTA = '\uE011'          // m
    const val NOLDO = '\uE012'          // ng
    const val NWALME = '\uE013'         // ngw
    const val OORE = '\uE014'           // r (before consonants)
    const val VALA = '\uE015'           // w / v (archaic)
    const val ANNA = '\uE016'           // y
    const val VILYA = '\uE017'          // w
    const val ROOMEN = '\uE020'         // r
    const val ARDA = '\uE021'           // rh
    const val LAMBE = '\uE022'          // l
    const val ALDA = '\uE023'           // lh
    const val SILME = '\uE024'          // s
    const val SILME_NUQUERNA = '\uE025' // s (alt)
    const val AARE = '\uE026'           // ss / z
    const val AARE_NUQUERNA = '\uE027'  // z (alt)
    const val HYARMEN = '\uE028'        // h
    const val HWESTA_SINDARINWA = '\uE029' // hw
    const val YANTA = '\uE02A'          // yanta carrier (i)
    const val UURE = '\uE02B'           // úrë carrier (u)
    const val LONG_CARRIER = '\uE02C'   // long vowel carrier
    const val HALLA = '\uE02D'          // h (alt)
    const val SHORT_CARRIER = '\uE02E'  // short vowel carrier

    // --- Tehtar (vowel marks) -------------------------------------------------
    const val THREE_DOTS_ABOVE = '\uE040' // a
    const val TWO_DOTS_ABOVE = '\uE042'   // y
    const val AMATIXE = '\uE044'          // i (dot above)
    const val ACUTE = '\uE046'            // e (acute)
    const val RIGHT_CURL = '\uE04A'       // u
    const val LEFT_CURL = '\uE04C'        // o

    // --- Modifying marks ------------------------------------------------------
    const val NASALIZER = '\uE050'
    const val DOUBLER = '\uE051'          // long / doubled consonant
    const val TILDE = '\uE052'

    /** Punctus / punctuation glyphs. */
    const val PUSTA = '\uE060'
    const val DOUBLE_PUSTA = '\uE061'
    const val EXCLAMATION = '\uE065'
    const val QUESTION = '\uE066'

    /** True if [c] belongs to the Tengwar private use area of the CSUR mapping. */
    fun isTengwa(c: Char): Boolean = c.code in 0xE000..0xE0FF

    /** Vowel tehta for a Latin vowel, or null if [v] is not a vowel. */
    fun tehtaFor(v: Char): Char? = when (v) {
        'a' -> THREE_DOTS_ABOVE
        'e' -> ACUTE
        'i' -> AMATIXE
        'o' -> LEFT_CURL
        'u' -> RIGHT_CURL
        'y' -> TWO_DOTS_ABOVE
        else -> null
    }
}
