package uz.behadllc.app_updater_behad

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.behadllc.app_updater_behad.core.UpdaterConstants
import uz.behadllc.app_updater_behad.data.manager.UpdateManagerImpl
import uz.behadllc.app_updater_behad.domain.manager.UpdateManager
import uz.behadllc.app_updater_behad.domain.service.UpdateService
import uz.behadllc.app_updater_behad.domain.use_case.GetUpdateInfo
import uz.behadllc.app_updater_behad.domain.use_case.UseCases

class BehadUpdater(base_url: String) {

    @JvmField
    var manager: UpdateManager? = null

    private var updateService: UpdateService? = null

    private var useCases: UseCases? = null

    init {
        updateService = getRetrofit(base_url).create(UpdateService::class.java)
        useCases = UseCases(GetUpdateInfo(service = updateService ?: getRetrofit(base_url).create(UpdateService::class.java)))
        manager = createManager(useCases = useCases)
    }

    companion object {

        private var instance: BehadUpdater? = null

        fun initUpdater(base_url: String): BehadUpdater? {
            if (instance == null) {
                instance = BehadUpdater(base_url)
            }
            return instance
        }

        fun getUpdater(): BehadUpdater? {
            if (instance == null) {
                throw Throwable(UpdaterConstants.UPDATER_ERROR_TEXT)
            }
            return instance
        }

    }


    private fun getRetrofit(base_url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    private fun createManager(useCases: UseCases?): UpdateManager {
        if (useCases != null) {
            return UpdateManagerImpl(useCases)
        } else {
            throw Throwable(UpdaterConstants.USE_CASES_ERROR_TEXT)
        }
    }

    fun calculateIsForceUpdate(localVersion: Int, minVersion: Int, currentVersion: Int): Int {
        Log.d("sdass", "calculateIsForceUpdate: localVersion: $localVersion minVersion: $minVersion currentVersion: $currentVersion")
        if (localVersion <= minVersion) {
            return UpdaterConstants.FORCE_UPDATE
        }
        if (localVersion == currentVersion) {
            return UpdaterConstants.VERSIONS_ARE_EQUALS
        }
        if (localVersion in minVersion..currentVersion) {
            return UpdaterConstants.NOT_FORCE_UPDATE
        }
        return UpdaterConstants.VERSIONS_ARE_EQUALS
    }


}