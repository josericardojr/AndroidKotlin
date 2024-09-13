package com.josericardojr.networkimageview.datamodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PixabayResult (

  @SerialName("total"     ) var total     : Int?            = null,
  @SerialName("totalHits" ) var totalHits : Int?            = null,
  @SerialName("hits"      ) var hits      : ArrayList<Hits> = arrayListOf()

)