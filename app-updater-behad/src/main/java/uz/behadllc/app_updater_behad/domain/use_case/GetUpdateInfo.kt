package uz.behadllc.app_updater_behad.domain.use_case

import uz.behadllc.app_updater_behad.domain.model.UpdateResponse
import uz.behadllc.app_updater_behad.domain.service.UpdateService

class GetUpdateInfo(private val service: UpdateService) {

    suspend fun invoke(): UpdateResponse {
        return try {
            with(service.getUpdateInfoAsync().await()) {
                if (isSuccessful) {
                    UpdateResponse(response = body()?.get(0), error = null, exception = null)
                } else {
                    UpdateResponse(response = null, error = errorBody()?.string().toString(), exception = null)
                }
            }
        } catch (e: Exception) {
            UpdateResponse(response = null, error = null, exception = e)
        }
    }

}