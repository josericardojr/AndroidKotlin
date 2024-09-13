package com.josericardojr.networkimageview.datamodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Hits (

  @SerialName("id"              ) var id              : Int?    = null,
  @SerialName("pageURL"         ) var pageURL         : String? = null,
  @SerialName("type"            ) var type            : String? = null,
  @SerialName("tags"            ) var tags            : String? = null,
  @SerialName("previewURL"      ) var previewURL      : String? = null,
  @SerialName("previewWidth"    ) var previewWidth    : Int?    = null,
  @SerialName("previewHeight"   ) var previewHeight   : Int?    = null,
  @SerialName("webformatURL"    ) var webformatURL    : String? = null,
  @SerialName("webformatWidth"  ) var webformatWidth  : Int?    = null,
  @SerialName("webformatHeight" ) var webformatHeight : Int?    = null,
  @SerialName("largeImageURL"   ) var largeImageURL   : String? = null,
  @SerialName("imageWidth"      ) var imageWidth      : Int?    = null,
  @SerialName("imageHeight"     ) var imageHeight     : Int?    = null,
  @SerialName("imageSize"       ) var imageSize       : Int?    = null,
  @SerialName("views"           ) var views           : Int?    = null,
  @SerialName("downloads"       ) var downloads       : Int?    = null,
  @SerialName("collections"     ) var collections     : Int?    = null,
  @SerialName("likes"           ) var likes           : Int?    = null,
  @SerialName("comments"        ) var comments        : Int?    = null,
  @SerialName("user_id"         ) var userId          : Int?    = null,
  @SerialName("user"            ) var user            : String? = null,
  @SerialName("userImageURL"    ) var userImageURL    : String? = null

)