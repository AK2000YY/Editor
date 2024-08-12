package com.example.editor.repository

import android.util.Log
import androidx.datastore.core.DataStore
import com.example.editor.UserPreferences
import com.example.editor.data.ProtoDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class UserProtoRepository @Inject constructor(
    private val dataStore: DataStore<UserPreferences>
) {

    //read...
    val getUserProto : Flow<ProtoDataStore> = dataStore.data.map {
        ProtoDataStore(
            username = it.username,
            userId = it.id,
            questionsSolved = it.qId,
            topicSolved = it.tId
        )
    }.catch {exception ->
        if (exception is IOException) {
            Log.e("proto ", "Error reading sort order preferences.", exception)
        } else {
            throw exception
        }
    }


    //write..
    suspend fun updateUserProto(protoDataStore : ProtoDataStore){
        dataStore.updateData {
            it.toBuilder()
                .setId(protoDataStore.userId!!)
                .setUsername(protoDataStore.username)
                .setQId(protoDataStore.questionsSolved!!)
                .setTId(protoDataStore.topicSolved!!)
                .build()
        }
    }
}