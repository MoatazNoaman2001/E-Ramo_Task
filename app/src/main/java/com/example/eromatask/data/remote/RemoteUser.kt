package com.example.eromatask.data.remote

import com.google.gson.annotations.SerializedName

data class RemoteUser(val id:Int, val name:String ,@SerializedName("username") val userName:String , val email:String)