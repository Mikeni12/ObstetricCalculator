package mx.mikeni.calculator.core

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlinx.datetime.until

fun today() = Clock.System.todayIn(TimeZone.currentSystemDefault())

fun convertMillisToDate(millis: Long) = Instant.fromEpochMilliseconds(millis)
        .toLocalDateTime(TimeZone.UTC)
        .toInstant(TimeZone.currentSystemDefault())
        .toLocalDateTime(TimeZone.currentSystemDefault())

fun LocalDate.birthDate() = plus(DateTimeUnit.WEEK.days, DateTimeUnit.DAY)
        .minus(3, DateTimeUnit.MONTH)
        .plus(ONE, DateTimeUnit.YEAR)

fun LocalDate.daysTillNow() = until(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date, DateTimeUnit.DAY)

fun Int.toWeeks() = div(DateTimeUnit.WEEK.days)

fun Int.toDays() = rem(DateTimeUnit.WEEK.days)

@OptIn(FormatStringsInDatetimeFormats::class)
val localDateTimeFormat = LocalDateTime.Format { byUnicodePattern(DATE_PATTERN) }

@OptIn(FormatStringsInDatetimeFormats::class)
val localDateFormat = LocalDate.Format { byUnicodePattern(DATE_PATTERN) }

private const val DATE_PATTERN = "dd/MM/uu"
