package com.mommylicious.mobile.data.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.mommylicious.mobile.data.model.Menu.Companion.toMenu
import com.mommylicious.mobile.utils.safeCallFirebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MenuRepository @Inject constructor(
    private val db: FirebaseFirestore,
) {

    suspend fun getMenus() = safeCallFirebase(
        firebaseCall = { db.collection(FirebaseConstants.MENU_COLLECTION)
            .get().await().mapNotNull { it.toMenu() } }
    )

    suspend fun getMenuByType(type: String) = safeCallFirebase(
        firebaseCall = { db.collection(FirebaseConstants.MENU_COLLECTION)
            .whereEqualTo("type", type).get().await().mapNotNull { it.toMenu() } }
    )

}