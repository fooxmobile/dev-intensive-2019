package ru.skillbranch.devintensive.utils

import java.util.*

object Utils {
    private val transliterationMap = mapOf<Char, String>(
        'а' to "a", 'б' to "b", 'в' to "v", 'г' to "g", 'д' to "d", 'е' to "e",
        'ё' to "e", 'ж' to "zh", 'з' to "z", 'и' to "i", 'й' to "i", 'к' to "k",
        'л' to "l", 'м' to "m", 'н' to "n", 'о' to "o", 'п' to "p", 'р' to "r",
        'с' to "s", 'т' to "t", 'у' to "u", 'ф' to "f", 'х' to "h", 'ц' to "c",
        'ч' to "ch", 'ш' to "sh", 'щ' to "sh", 'ъ' to "", 'ы' to "i", 'ь' to "",
        'э' to "e", 'ю' to "yu", 'я' to "ya"
    )
    fun parseFullName( fullName: String?): Pair <String?, String?> {
        val parts = fullName?.split(" ")
        val firstName: String? = if (parts?.getOrNull(0).isNullOrEmpty()) null else parts?.get(0)
        val lastName: String? = if (parts?.getOrNull(1).isNullOrEmpty()) null else parts?.get(1)

        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if (firstName.isNullOrBlank() && lastName.isNullOrBlank()) return null
        return buildString {
            if (!firstName.isNullOrBlank()) append(firstName[0].toUpperCase())
            if (!lastName.isNullOrBlank()) append(lastName[0].toUpperCase())
        }
    }

    fun transliteration(payload: String?, divider: String = " "): String? {

        val parts = payload?.split(" ")
        if (parts.isNullOrEmpty()) return null

        return buildString {
            for (word in parts) {
                for (c in word) {
                    if (transliterationMap.containsKey(c.toLowerCase()))  {
                        if (c.isUpperCase())
                            append(transliterationMap.getValue(c.toLowerCase()).toUpperCase(Locale("en")))
                        else
                            append(transliterationMap.getValue(c))
                         }
                    else append(c)
                }
            if (parts.indexOf(word) != parts.lastIndex) append(divider)
            }

        }
    }

}