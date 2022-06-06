package com.mommylicious.mobile.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.mommylicious.mobile.data.firebase.ChildRepository
import com.mommylicious.mobile.data.firebase.RecordRepository
import com.mommylicious.mobile.data.firebase.UserRepository
import com.mommylicious.mobile.data.preferences.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseCrashlytics(): FirebaseCrashlytics = FirebaseCrashlytics.getInstance()

    @Provides
    @Singleton
    fun providePreferences(): Preferences = Preferences.instance

    @Provides
    @Singleton
    fun provideUserRepository(
        auth: FirebaseAuth,
        db: FirebaseFirestore,
        storage: FirebaseStorage
    ): UserRepository = UserRepository(auth, db, storage)

    @Provides
    @Singleton
    fun provideChildRepository(
        auth: FirebaseAuth,
        db: FirebaseFirestore,
        storage: FirebaseStorage,
        pref: Preferences
    ): ChildRepository = ChildRepository(auth, db, storage, pref)

    @Provides
    @Singleton
    fun provideRecordRepository(
        auth: FirebaseAuth,
        db: FirebaseFirestore,
        storage: FirebaseStorage,
        pref: Preferences
    ): RecordRepository = RecordRepository(auth, db, storage, pref)
}