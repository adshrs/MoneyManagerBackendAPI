package com.example.moneymanagerbackend.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document
data class Category(
    @Id
    val id: ObjectId = ObjectId(),
    @Field(name="name")
    @Indexed(unique = true)
    val name: String,
    @Field(name="color")
    val color: String,
    val userId: ObjectId
)