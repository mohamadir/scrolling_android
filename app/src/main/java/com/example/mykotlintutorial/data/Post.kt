package com.example.mykotlintutorial.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


data class Post(
    @SerializedName(value = "hihi")
    val a : Int


)