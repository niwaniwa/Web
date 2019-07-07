package com.github.niwaniwa.Web.database.mongodb


import com.github.niwaniwa.Web.database.DataBase
import com.mongodb.MongoClientSettings
import com.mongodb.ServerAddress
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import com.mongodb.connection.ClusterSettings
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository


@Configurable
@Repository
class MongoDataBaseManager {

    val name : String = "MongoDB"

    val mongoClient : MongoClient

    @Value("\${database.mongodb.database}")
    val databasename : String = "SpringbootSample"

    @Value("\${database.mongodb.host}")
    val host: String = "localhost"

    @Value("\${database.mongodb.port}")
    val port: String = "27017"

    @Value("\${database.mongodb.username}")
    private val username: String = "user"

    @Value("\${database.mongodb.password}")
    private val password: String = "password"

    constructor (){
        mongoClient = MongoClients.create(MongoClientSettings.builder().applyToClusterSettings{ builder : ClusterSettings.Builder ->
            builder.hosts(mutableListOf(ServerAddress(host, port.toInt())))
        }.build())
    }

    fun getDatabase(name : String) : MongoDatabase {
        println(name)
        return mongoClient.getDatabase(name)
    }


}