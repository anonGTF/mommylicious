package com.mommylicious.mobile.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.mommylicious.mobile.data.model.Record
import com.mommylicious.mobile.data.model.Record.Companion.toRecord
import com.mommylicious.mobile.data.preferences.Preferences
import com.mommylicious.mobile.utils.safeCallFirebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RecordRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val storage: FirebaseStorage,
    private val pref: Preferences
) {

    fun getUserId() = auth.currentUser?.uid ?: ""

    suspend fun add(record: Record) = safeCallFirebase(
        firebaseCall = {
            db.collection(FirebaseConstants.USER_COLLECTION).document(getUserId())
                .collection(FirebaseConstants.CHILD_COLLECTION).document(pref.childId.orEmpty())
                .collection(FirebaseConstants.RECORD_COLLECTION).add(record).await()
                .get().await()
                .toRecord()
        }
    )

    suspend fun getRecords() = safeCallFirebase(
        firebaseCall = {
            db.collection(FirebaseConstants.USER_COLLECTION).document(getUserId())
                .collection(FirebaseConstants.CHILD_COLLECTION).document(pref.childId.orEmpty())
                .collection(FirebaseConstants.RECORD_COLLECTION)
                .get().await()
                .mapNotNull { it.toRecord() }
        }
    )

    suspend fun getLatestRecord() = safeCallFirebase(
        firebaseCall = {
            db.collection(FirebaseConstants.USER_COLLECTION).document(getUserId())
                .collection(FirebaseConstants.CHILD_COLLECTION).document(pref.childId.orEmpty())
                .collection(FirebaseConstants.RECORD_COLLECTION).orderBy("date", Query.Direction.DESCENDING)
                .limit(1).get().await()
                .mapNotNull { it.toRecord() }
        }
    )
}