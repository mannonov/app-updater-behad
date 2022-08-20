package uz.behadllc.app_updater_behad.domain.model

import java.lang.Exception

data class UpdateResponse(
    var response: UpdateModel? = null,
    var error: String? = null,
    var exception: Exception? = null
)