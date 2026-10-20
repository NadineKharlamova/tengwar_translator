package com.tengwar.translator.domain

/**
 * Offline demo lexicons.
 *
 * The translator works in two hops, exactly like the app UI shows:
 *
 *   Russian  --ruEn-->  English  --enSin-->  Sindarin
 *   Sindarin --sinEn--> English  --enRu-->   Russian
 *
 * [enRu] and [sinEn] are derived from [ruEn] / [enSin] by inversion. Sindarin words are
 * given in their dictionary (unmutated, singular / stem) form.
 *
 * This is a curated demo vocabulary, not a complete dictionary. Unknown words are passed
 * through unchanged and reported to the UI.
 */
object Lexicon {

    // ---------------------------------------------------------------------
    // Russian -> English
    // ---------------------------------------------------------------------
    val ruEn: Map<String, String> = mapOf(
        // pronouns
        "я" to "i",
        "ты" to "you",
        "вы" to "you",
        "он" to "he",
        "она" to "she",
        "оно" to "it",
        "мы" to "we",
        "они" to "they",
        "мой" to "my",
        "моя" to "my",
        "твой" to "your",
        "наш" to "our",
        "ваш" to "your",
        "их" to "their",
        "этот" to "this",
        "эта" to "this",
        "тот" to "that",
        "себя" to "self",

        // function words
        "и" to "and",
        "но" to "but",
        "или" to "or",
        "не" to "not",
        "да" to "yes",
        "нет" to "no",
        "в" to "in",
        "на" to "on",
        "с" to "with",
        "к" to "to",
        "от" to "from",
        "для" to "for",
        "как" to "how",
        "что" to "what",
        "кто" to "who",
        "где" to "where",
        "когда" to "when",
        "почему" to "why",

        // greetings and polite words
        "привет" to "hello",
        "здравствуй" to "hello",
        "здравствуйте" to "hello",
        "пока" to "bye",
        "прощай" to "farewell",
        "спасибо" to "thank you",
        "благодарю" to "thank you",
        "пожалуйста" to "please",
        "извините" to "sorry",
        "прости" to "sorry",
        "добро" to "good",

        // nature
        "мир" to "world",
        "земля" to "earth",
        "небо" to "sky",
        "солнце" to "sun",
        "луна" to "moon",
        "звезда" to "star",
        "звёзды" to "stars",
        "море" to "sea",
        "вода" to "water",
        "река" to "river",
        "гора" to "mountain",
        "камень" to "stone",
        "лес" to "forest",
        "дерево" to "tree",
        "лист" to "leaf",
        "цветок" to "flower",
        "ветер" to "wind",
        "дождь" to "rain",
        "снег" to "snow",
        "огонь" to "fire",
        "свет" to "light",
        "тьма" to "darkness",
        "тень" to "shadow",
        "ночь" to "night",
        "день" to "day",
        "рассвет" to "dawn",
        "закат" to "dusk",

        // people and places
        "друг" to "friend",
        "друзья" to "friends",
        "король" to "king",
        "королева" to "queen",
        "лорд" to "lord",
        "леди" to "lady",
        "человек" to "man",
        "мужчина" to "man",
        "женщина" to "woman",
        "ребёнок" to "child",
        "ребенок" to "child",
        "дети" to "children",
        "народ" to "people",
        "эльф" to "elf",
        "эльфы" to "elves",
        "гном" to "dwarf",
        "гномы" to "dwarves",
        "орк" to "orc",
        "хоббит" to "hobbit",
        "дом" to "house",
        "город" to "city",
        "башня" to "tower",
        "дверь" to "door",
        "ворота" to "gate",
        "мост" to "bridge",
        "дорога" to "road",
        "путь" to "way",
        "королевство" to "kingdom",

        // objects
        "кольцо" to "ring",
        "меч" to "sword",
        "клинок" to "blade",
        "стрела" to "arrow",
        "лук" to "bow",
        "щит" to "shield",
        "книга" to "book",
        "хлеб" to "bread",
        "слово" to "word",
        "имя" to "name",
        "песня" to "song",
        "сказка" to "tale",
        "история" to "story",

        // body and feelings
        "сердце" to "heart",
        "рука" to "hand",
        "глаз" to "eye",
        "нога" to "foot",
        "голова" to "head",
        "кровь" to "blood",
        "кость" to "bone",
        "смерть" to "death",
        "любовь" to "love",
        "надежда" to "hope",
        "сила" to "power",
        "война" to "war",
        "страх" to "fear",
        "радость" to "joy",

        // verbs (infinitive)
        "быть" to "be",
        "прийти" to "come",
        "идти" to "go",
        "видеть" to "see",
        "слышать" to "hear",
        "говорить" to "speak",
        "сказать" to "say",
        "знать" to "know",
        "любить" to "love",
        "дать" to "give",
        "взять" to "take",
        "найти" to "find",
        "делать" to "make",
        "есть" to "eat",
        "пить" to "drink",
        "спать" to "sleep",
        "жить" to "live",
        "умереть" to "die",
        "помочь" to "help",
        "ждать" to "wait",

        // adjectives
        "хороший" to "good",
        "плохой" to "bad",
        "великий" to "great",
        "большой" to "big",
        "маленький" to "small",
        "белый" to "white",
        "чёрный" to "black",
        "черный" to "black",
        "красный" to "red",
        "синий" to "blue",
        "зелёный" to "green",
        "зеленый" to "green",
        "серый" to "grey",
        "тёмный" to "dark",
        "темный" to "dark",
        "красивый" to "beautiful",
        "сильный" to "strong",
        "быстрый" to "swift",
        "холодный" to "cold",
        "новый" to "new",
        "старый" to "old",
        "высокий" to "high",

        // numerals
        "один" to "one",
        "два" to "two",
        "три" to "three",
        "четыре" to "four",
        "пять" to "five",
        "шесть" to "six",
        "семь" to "seven",
        "восемь" to "eight",
        "девять" to "nine",
        "десять" to "ten",

        // phrases
        "добрый день" to "good day",
        "доброе утро" to "good morning",
        "до свидания" to "goodbye",
        "как дела" to "how are you",
        "я тебя люблю" to "i love you",
        "я люблю тебя" to "i love you",
        "мой друг" to "my friend"
    )

    // ---------------------------------------------------------------------
    // English -> Sindarin
    // ---------------------------------------------------------------------
    val enSin: Map<String, String> = mapOf(
        // pronouns
        "i" to "im",
        "you" to "le",
        "he" to "e",
        "she" to "e",
        "it" to "sa",
        "we" to "me",
        "they" to "te",
        "my" to "nin",
        "your" to "lin",
        "our" to "min",
        "their" to "tin",
        "this" to "sen",
        "that" to "san",
        "self" to "im",

        // function words
        "and" to "a",
        "but" to "dan",
        "or" to "egor",
        "not" to "baw",
        "yes" to "ma",
        "no" to "baw",
        "in" to "mi",
        "on" to "or",
        "with" to "na",
        "to" to "na",
        "from" to "o",
        "for" to "an",
        "how" to "man",
        "what" to "man",
        "who" to "man",
        "where" to "mas",
        "when" to "ir",
        "why" to "amman",

        // greetings (Sindarin has one standard greeting)
        "hello" to "mae govannen",
        "hi" to "mae govannen",
        "good day" to "mae govannen",
        "good morning" to "mae govannen",
        "how are you" to "mae govannen",
        "thank you" to "hannon le",
        "please" to "le hannon",
        "sorry" to "im harn",
        "bye" to "navaer",
        "goodbye" to "navaer",
        "farewell" to "navaer",
        "good" to "maer",

        // nature
        "world" to "amar",
        "earth" to "ambar",
        "sky" to "menel",
        "sun" to "anor",
        "moon" to "ithil",
        "star" to "elen",
        "stars" to "elin",
        "sea" to "aear",
        "water" to "nen",
        "river" to "sir",
        "mountain" to "orod",
        "stone" to "gond",
        "forest" to "taur",
        "tree" to "galadh",
        "leaf" to "lass",
        "flower" to "loth",
        "wind" to "sul",
        "rain" to "ross",
        "snow" to "loss",
        "fire" to "naur",
        "light" to "galad",
        "darkness" to "morn",
        "shadow" to "dae",
        "night" to "du",
        "day" to "aur",
        "dawn" to "minuial",
        "dusk" to "aduial",

        // people and places
        "friend" to "mellon",
        "friends" to "mellyn",
        "king" to "aran",
        "queen" to "bereth",
        "lord" to "heru",
        "lady" to "hiril",
        "man" to "adan",
        "woman" to "bess",
        "child" to "hen",
        "children" to "hin",
        "people" to "gwaith",
        "elf" to "edhel",
        "elves" to "edhil",
        "dwarf" to "naug",
        "dwarves" to "naugrim",
        "orc" to "orch",
        "hobbit" to "perian",
        "house" to "bar",
        "city" to "ost",
        "tower" to "barad",
        "door" to "fenn",
        "gate" to "annon",
        "bridge" to "iant",
        "road" to "men",
        "way" to "men",
        "kingdom" to "ardh",

        // objects
        "ring" to "corf",
        "sword" to "magol",
        "blade" to "crist",
        "arrow" to "pilin",
        "bow" to "cu",
        "shield" to "thand",
        "book" to "parf",
        "bread" to "bas",
        "word" to "peth",
        "name" to "ess",
        "song" to "lind",
        "tale" to "narn",
        "story" to "narn",

        // body and feelings
        "heart" to "gur",
        "hand" to "cam",
        "eye" to "hen",
        "foot" to "tal",
        "head" to "dol",
        "blood" to "sereg",
        "bone" to "ach",
        "death" to "gurth",
        "love" to "meleth",
        "hope" to "estel",
        "power" to "tul",
        "war" to "auth",
        "fear" to "gost",
        "joy" to "gell",

        // verbs (stems)
        "be" to "na",
        "come" to "tol",
        "go" to "men",
        "see" to "cen",
        "hear" to "lasto",
        "speak" to "ped",
        "say" to "ped",
        "know" to "ista",
        "give" to "anna",
        "take" to "nimm",
        "find" to "hevia",
        "make" to "gwama",
        "eat" to "mad",
        "drink" to "sogo",
        "sleep" to "huno",
        "live" to "cuina",
        "die" to "gwanna",
        "help" to "rain",
        "wait" to "daro",

        // adjectives
        "bad" to "ogol",
        "great" to "beleg",
        "big" to "beleg",
        "small" to "pin",
        "white" to "nim",
        "black" to "morn",
        "red" to "caran",
        "blue" to "luin",
        "green" to "laeg",
        "grey" to "mith",
        "dark" to "morn",
        "beautiful" to "bain",
        "strong" to "bell",
        "swift" to "lagor",
        "cold" to "ring",
        "new" to "cuin",
        "old" to "iaur",
        "high" to "orn",

        // numerals
        "one" to "min",
        "two" to "tad",
        "three" to "neledh",
        "four" to "canad",
        "five" to "leben",
        "six" to "eneg",
        "seven" to "odo",
        "eight" to "tolodh",
        "nine" to "neder",
        "ten" to "pae",

        // phrases
        "my friend" to "mellon nin",
        "i love you" to "le melin"
    )

    /** English -> Russian, derived by inverting [ruEn]. */
    val enRu: Map<String, String> = invert(ruEn)

    /** Sindarin -> English, derived by inverting [enSin]. */
    val sinEn: Map<String, String> = invert(enSin)

    private fun invert(source: Map<String, String>): Map<String, String> {
        val result = LinkedHashMap<String, String>()
        for ((from, to) in source) {
            result.putIfAbsent(to, from)
        }
        return result
    }

    /** Longest phrase length (in words) present in the dictionaries. */
    val maxPhraseWords: Int = (ruEn.keys + enSin.keys)
        .maxOfOrNull { it.split(' ').size } ?: 1
}
