package com.mommylicious.mobile.data.model

import android.os.Parcelable
import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.DocumentSnapshot
import com.mommylicious.mobile.base.BaseModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Need(
    val needId: String,
    val icon: String,
    val name: String,
    val amount: String
) : BaseModel(needId), Parcelable {
    companion object {
        fun DocumentSnapshot.toNeed(): Need? {
            return try {
                val icon = getString("icon")!!
                val name = getString("name")!!
                val amount = getString("amount")!!
                Need(id, icon, name, amount)
            } catch (e: Exception) {
                Log.e(TAG, ERROR_MESSAGE, e)
                FirebaseCrashlytics.getInstance().log(ERROR_MESSAGE)
                FirebaseCrashlytics.getInstance().setCustomKey(TAG, id)
                FirebaseCrashlytics.getInstance().recordException(e)
                null
            }
        }

        private const val TAG = "Need"
        private const val ERROR_MESSAGE = "Error converting need data"
    }
}
