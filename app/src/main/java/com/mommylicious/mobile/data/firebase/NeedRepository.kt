package com.mommylicious.mobile.data.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.mommylicious.mobile.data.model.Need.Companion.toNeed
import com.mommylicious.mobile.utils.safeCallFirebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NeedRepository @Inject constructor(
    private val db: FirebaseFirestore,
) {

    suspend fun getNeeds() = safeCallFirebase(
        firebaseCall = { db.collection(FirebaseConstants.NEED_COLLECTION)
            .get().await().mapNotNull { it.toNeed() } }
    )

}