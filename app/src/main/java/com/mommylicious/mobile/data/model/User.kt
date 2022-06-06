package com.mommylicious.mobile.data.model

import android.os.Parcelable
import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val userId: String,
    val name: String,
    val nik: String,
    val imageUrl: String) : Parcelable {

        companion object {
            fun DocumentSnapshot.toUser(): User? {
                return try {
                    val name = getString("name")!!
                    val nik = getString("nik")!!
                    val imageUrl = getString("imageUrl")!!
                    User(id, name, nik, imageUrl)
                } catch (e: Exception) {
                    Log.e(TAG, ERROR_MESSAGE, e)
                    FirebaseCrashlytics.getInstance().log(ERROR_MESSAGE)
                    FirebaseCrashlytics.getInstance().setCustomKey(TAG, id)
                    FirebaseCrashlytics.getInstance().recordException(e)
                    null
                }
            }

            fun getDefaultUser(name: String, nik: String) = hashMapOf(
                "name" to name,
                "imageUrl" to DEFAULT_PROFILE_PICTURE,
                "nik" to nik
            )

            const val DEFAULT_PROFILE_PICTURE = "https://firebasestorage.googleapis.com/v0/b/matarak-v2.appspot.com/o/person_placeholder.png?alt=media&token=077106ea-d808-40aa-933e-6023258cfbc5"
            private const val TAG = "User"
            private const val ERROR_MESSAGE = "Error converting user profile"
        }
    }
