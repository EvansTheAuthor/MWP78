package com.example.mwp78

import android.text.format.DateUtils

object TimeUtils {
    fun formatTimeAgo(timestamp: Long): String {
        return DateUtils.getRelativeTimeSpanString(
            timestamp,
            System.currentTimeMillis(),
            DateUtils.MINUTE_IN_MILLIS,
            DateUtils.FORMAT_ABBREV_RELATIVE
        ).toString()
    }
}