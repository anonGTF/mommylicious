package com.mommylicious.mobile.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.mommylicious.mobile.data.model.Child
import com.mommylicious.mobile.data.model.Child.Companion.toChild
import com.mommylicious.mobile.data.preferences.Preferences
import com.mommylicious.mobile.utils.safeCallFirebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChildRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val storage: FirebaseStorage,
    private val pref: Preferences
) {

    fun getUserId() = auth.currentUser?.uid ?: ""

    suspend fun add(child: Child) = safeCallFirebase(
        firebaseCall = {
            db.collection(FirebaseConstants.USER_COLLECTION).document(getUserId())
                .collection(FirebaseConstants.CHILD_COLLECTION).add(child).await()
                .get().await()
                .toChild()
        }
    )

    suspend fun getChildren() = safeCallFirebase(
        firebaseCall = { db.collection(FirebaseConstants.USER_COLLECTION).document(getUserId())
            .collection(FirebaseConstants.CHILD_COLLECTION)
            .get().await()
            .mapNotNull { it.toChild() }
        }
    )

    suspend fun getChild(id: String) = safeCallFirebase(
        firebaseCall = { db.collection(FirebaseConstants.USER_COLLECTION).document(getUserId())
            .collection(FirebaseConstants.CHILD_COLLECTION).document(id)
            .get().await().toChild()
        }
    )

    fun saveChildId(id: String) = pref.saveChildId(id)

    fun getChildId(): String = pref.childId.orEmpty()
}