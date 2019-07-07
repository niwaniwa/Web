package com.github.niwaniwa.Web.database.mongodb


import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.Document
import com.mongodb.client.FindIterable
import java.util.ArrayList



class MongoDataBaseCollection {

    private var manager : MongoDataBaseManager
    private var database : MongoDatabase
    private var collection : MongoCollection<Document>

    constructor(manager : MongoDataBaseManager, database : MongoDatabase, collection : String){
        this.manager = manager
        this.database = database
        this.collection = database.getCollection(collection)
    }

    fun update(document : Document, document2 : Document) {
        collection.updateOne(document, document2)
    }

    fun insert(bson : Document){
        collection.insertOne(bson)
    }

    fun find(bson : Document) : FindIterable<Document> {
        return collection.find(bson)
    }

    fun findOne(bson: Document) : Document? {
        var doc = this.find(bson).first()
        return doc;
    }

    operator fun contains(obj: Any): Boolean {
        while (this.getDocuments().iterator().hasNext()) {
            val doc = this.getDocuments().iterator().next()
            for (entry in doc.entries) {
                if (entry.value == obj) {
                    return true
                }
            }
        }
        return false
    }

    fun getDocuments(): FindIterable<Document> {
        return collection.find()
    }

    operator fun get(key: String): Any? {
        while (this.getDocuments().iterator().hasNext()) {
            val doc = this.getDocuments().iterator().next()
            return doc[key]
        }
        return null
    }

    operator fun get(source: Document, key: String): Any? {
        val cursor = this.find(source).iterator()
        if (cursor.hasNext()) {
            val doc = cursor.next()
            return doc[key]
        }
        return null
    }

    operator fun get(key: String, length: Int): List<Any> {
        val size = if (length != 0) length else Integer.MAX_VALUE
        val values = ArrayList<Any>()
        while (this.getDocuments().iterator().hasNext()) {
            if (values.size == size) {
                break
            }
            val doc = this.getDocuments().iterator().next()
            val obj = doc[key]
            if (obj != null) {
                values.add(obj)
            }
        }
        return values
    }

    fun getString(key: String): String {
        return get(key).toString()
    }

    fun getString(source: Document, key: String): String {
        return if (this[key] != null) this[key].toString() else ""
    }

    fun getBoolean(key: String): Boolean {
        return getBoolean(getString(key))
    }

    fun getBoolean(source: Document, key: String): Boolean {
        return getBoolean(getString(source, key))
    }

}