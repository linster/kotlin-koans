package i_introduction._12_Extensions_On_Collections

import util.TODO
import util.doc12
import java.util.*

fun todoTask12(): Nothing = TODO(
    """
        Task 12.
        Kotlin standard library contains lots of extension functions that make the work with collections more convenient.
        Rewrite the previous example once more using an extension function 'sortedDescending'.

        Kotlin code can be easily mixed with Java code.
        Thus in Kotlin we don't introduce our own collections, but use standard Java ones (slightly improved).
        Read about read-only and mutable views on Java collections.
    """,
    documentation = doc12()
)

fun List<Int>.sortedDecending(): List<Int> {
    Collections.sort(this, { x, y ->
        if ( x == y) {return@sort 0 }
        if ( x > y) { return@sort -1 }
        if ( x < y) { return@sort 1 }
        return@sort 0
    })
    return this
}

fun task12(): List<Int> {
    return arrayListOf(1, 5, 2).sortedDecending()
}

