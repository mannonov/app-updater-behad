package uz.behadllc.app_updater_behad.domain.model

import com.google.gson.annotations.SerializedName

data class UpdateModel(
    @SerializedName("current_version")
    var currentVersion: Int? = null,
    @SerializedName("min_version")
    var minVersion: Int? = null
)