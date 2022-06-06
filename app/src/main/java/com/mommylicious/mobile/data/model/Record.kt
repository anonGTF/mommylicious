package com.mommylicious.mobile.data.model

import android.os.Parcelable
import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Record(val recorddId: String,
                  val height: Double,
                  val weight: Double,
                  val head: Double,
                  val date: Date) : Parcelable {

    companion object {
        fun DocumentSnapshot.toRecord(): Record? {
            return try {
                val height = getDouble("height")!!
                val weight = getDouble("weight")!!
                val head = getDouble("head")!!
                val date = getDate("date")!!
                Record(id, height, weight, head, date)
            } catch (e: Exception) {
                Log.e(TAG, ERROR_MESSAGE, e)
                FirebaseCrashlytics.getInstance().log(ERROR_MESSAGE)
                FirebaseCrashlytics.getInstance().setCustomKey(TAG, id)
                FirebaseCrashlytics.getInstance().recordException(e)
                null
            }
        }

        private const val TAG = "Record"
        private const val ERROR_MESSAGE = "Error converting record data"
    }
}