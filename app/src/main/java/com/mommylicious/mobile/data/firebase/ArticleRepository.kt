package com.mommylicious.mobile.data.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.mommylicious.mobile.data.model.Article.Companion.toArticle
import com.mommylicious.mobile.utils.safeCallFirebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val db: FirebaseFirestore,
) {

    suspend fun getArticles() = safeCallFirebase(
        firebaseCall = { db.collection(FirebaseConstants.ARTICLE_COLLECTION)
            .get().await().mapNotNull { it.toArticle() } }
    )

}