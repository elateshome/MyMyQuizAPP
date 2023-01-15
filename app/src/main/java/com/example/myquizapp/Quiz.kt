package com.quizeapp

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

//There are three major components
//in Room  entity,dao,database

//Annotated class that describes a database table when working with Room
@Entity(tableName = "quiz")
data class Quiz(
    @PrimaryKey
    val id: Long,
    val question: String,
    val a: String,
    val b: String,
    val c: String,
    val d: String,
    val answer: String,
    val explanation: String
): Serializable  //Serializable coz need to pass the object through the fragment