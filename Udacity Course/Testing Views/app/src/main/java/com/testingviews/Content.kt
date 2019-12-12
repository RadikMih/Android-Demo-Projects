package com.testingviews

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Content(
    @JsonProperty("baseline")
    val baseline: String? = null,
    @JsonProperty("country")
    val country: String? = null,
    @JsonProperty("description")
    val description: String? = null,
    @JsonProperty("language")
    val language: String? = null,
    @JsonProperty("logo")
    val logo: String? = null,
    @JsonProperty("name")
    val name: String? = null,
    @JsonProperty("permalink")
    val permalink: String? = null,
    @JsonProperty("smallLogo")
    val smallLogo: String? = null,

    @JsonProperty("timezone")
    val timezone: String? = null,

    @JsonProperty("web")
    val web: String? = null,

    @JsonProperty("duration")
    val duration: Long = 0,

    val published: Date? = null
) : Parcelable