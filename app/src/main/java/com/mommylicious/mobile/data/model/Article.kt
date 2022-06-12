package com.mommylicious.mobile.data.model

import android.os.Parcelable
import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.DocumentSnapshot
import com.mommylicious.mobile.base.BaseModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    val articleId: String,
    val title: String,
    val link: String,
    val image: String
) : BaseModel(articleId), Parcelable {
    companion object {
        fun DocumentSnapshot.toArticle(): Article? {
            return try {
                val title = getString("title")!!
                val link = getString("link")!!
                val image = getString("image")!!
                Article(id, title, link, image)
            } catch (e: Exception) {
                Log.e(TAG, ERROR_MESSAGE, e)
                FirebaseCrashlytics.getInstance().log(ERROR_MESSAGE)
                FirebaseCrashlytics.getInstance().setCustomKey(TAG, id)
                FirebaseCrashlytics.getInstance().recordException(e)
                null
            }
        }

        private const val TAG = "Article"
        private const val ERROR_MESSAGE = "Error converting article data"
    }
}