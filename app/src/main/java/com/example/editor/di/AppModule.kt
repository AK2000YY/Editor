package com.example.editor.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.example.editor.FirebaseSignInWithEmailAndPasswordApp
import com.example.editor.UserPreferences
import com.example.editor.core.Constants.BASE_URL
import com.example.editor.repository.QuestionRepository
import com.example.editor.repository.UserRepository
import com.example.editor.network.UsersService
import com.example.editor.proto.UserSerializer
import com.example.editor.repository.CodeRepository
import com.example.editor.repository.ExampleRepository
import com.example.editor.repository.QuestionSolvedRepository
import com.example.editor.repository.TopicRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule{

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): FirebaseSignInWithEmailAndPasswordApp {
        return app as FirebaseSignInWithEmailAndPasswordApp
    }

    @Singleton
    @Provides
    fun provideService() : UsersService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsersService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        usersService: UsersService,
    ) = UserRepository(
        usersService
    )

    @Singleton
    @Provides
    fun provideCodeRepository(
        usersService: UsersService
    ) = CodeRepository(
        usersService
    )

    @Singleton
    @Provides
    fun provideQuestionRepository(
        usersService: UsersService,
    ) : QuestionRepository = QuestionRepository(
        usersService
    )

    @Singleton
    @Provides
    fun provideTopicRepository(
        usersService: UsersService
    ) = TopicRepository(
        usersService
    )

    @Singleton
    @Provides
    fun provideExampleRepository(
        usersService: UsersService
    ) = ExampleRepository(
        usersService
    )

    @Singleton
    @Provides
    fun provideQuestionSolved(
        usersService: UsersService
    ) = QuestionSolvedRepository(
        usersService
    )

    @Singleton
    @Provides
    fun provideUserPreferences(
        @ApplicationContext context: Context
    ):DataStore<UserPreferences>{
        return DataStoreFactory.create(
            produceFile = {
                context.dataStoreFile("UserPreferences")
            },
            serializer = UserSerializer
        )
    }

}
