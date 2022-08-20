package uz.behadllc.app_updater_behad.domain.service

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import uz.behadllc.app_updater_behad.domain.model.UpdateModel

interface UpdateService {

    @GET("/getUpdateInfo")
    fun getUpdateInfoAsync(): Deferred<Response<List<UpdateModel>>>

}