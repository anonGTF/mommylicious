package com.mommylicious.mobile.data.model

import android.os.Parcelable
import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.DocumentSnapshot
import com.mommylicious.mobile.base.BaseModel
import com.mommylicious.mobile.utils.getTimeLapse
import com.mommylicious.mobile.utils.orNow
import com.mommylicious.mobile.utils.toDate
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Child(val childId: String,
                 val name: String,
                 val birthDate: String,
                 val gender: String,
                 val weekOfBirth: Int,
                 val imageUrl: String) : BaseModel(childId), Parcelable {

    companion object {
        fun DocumentSnapshot.toChild(): Child? {
            return try {
                val name = getString("name")!!
                val birthDate = getString("birthDate")!!
                val gender = getString("gender")!!
                val weekOfBirth = getLong("weekOfBirth")!!.toInt()
                val imageUrl = getString("imageUrl")!!
                Child(id, name, birthDate, gender, weekOfBirth, imageUrl)
            } catch (e: Exception) {
                Log.e(TAG, ERROR_MESSAGE, e)
                FirebaseCrashlytics.getInstance().log(ERROR_MESSAGE)
                FirebaseCrashlytics.getInstance().setCustomKey(TAG, id)
                FirebaseCrashlytics.getInstance().recordException(e)
                null
            }
        }

        fun Child.getAge() = getTimeLapse(this.birthDate.toDate().orNow())

        const val DEFAULT_PROFILE_PICTURE = "https://firebasestorage.googleapis.com/v0/b/matarak-v2.appspot.com/o/person_placeholder.png?alt=media&token=077106ea-d808-40aa-933e-6023258cfbc5"
        private const val TAG = "User"
        private const val ERROR_MESSAGE = "Error converting child profile"
    }
}
