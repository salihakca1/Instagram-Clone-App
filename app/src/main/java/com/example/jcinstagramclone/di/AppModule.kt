package com.example.jcinstagramclone.di

import com.example.jcinstagramclone.data.repo.AuthDaoRepository
import com.example.jcinstagramclone.data.repo.DatabaseDaoRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabaseDaoRepository(auth: FirebaseAuth, fireStore: FirebaseFirestore, storage: FirebaseStorage): DatabaseDaoRepository{
        return DatabaseDaoRepository(auth,fireStore, storage)
    }

    @Provides
    @Singleton
    fun provideUserDaoRepository(auth: FirebaseAuth, fireStore: FirebaseFirestore): AuthDaoRepository{
        return AuthDaoRepository(auth,fireStore)
    }

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideDatabaseReference(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}