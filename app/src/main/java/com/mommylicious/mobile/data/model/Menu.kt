package com.mommylicious.mobile.data.model

import android.os.Parcelable
import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.DocumentSnapshot
import com.mommylicious.mobile.base.BaseModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Menu(
    val menuId: String,
    val name: String,
    val timeSpent: String,
    val type: String,
    val image: String,
    val calories: Double
) : BaseModel(menuId), Parcelable {
    companion object {
        fun DocumentSnapshot.toMenu(): Menu? {
            return try {
                val name = getString("name")!!
                val timeSpent = getString("timeSpent")!!
                val type = getString("type")!!
                val image = getString("image")!!
                val calories = getDouble("calories")!!
                Menu(id, name, timeSpent, type, image, calories)
            } catch (e: Exception) {
                Log.e(TAG, ERROR_MESSAGE, e)
                FirebaseCrashlytics.getInstance().log(ERROR_MESSAGE)
                FirebaseCrashlytics.getInstance().setCustomKey(TAG, id)
                FirebaseCrashlytics.getInstance().recordException(e)
                null
            }
        }

        private const val TAG = "Menu"
        private const val ERROR_MESSAGE = "Error converting menu data"
    }
}
