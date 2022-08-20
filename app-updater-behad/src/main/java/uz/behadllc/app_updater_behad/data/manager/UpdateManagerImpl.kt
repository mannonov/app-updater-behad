package uz.behadllc.app_updater_behad.data.manager

import uz.behadllc.app_updater_behad.core.Coroutines
import uz.behadllc.app_updater_behad.core.LoaderCallback
import uz.behadllc.app_updater_behad.domain.manager.UpdateManager
import uz.behadllc.app_updater_behad.domain.model.UpdateResponse
import uz.behadllc.app_updater_behad.domain.use_case.UseCases

class UpdateManagerImpl(private val userCases: UseCases) : UpdateManager {

    override fun getUpdateInfo(callback: LoaderCallback<UpdateResponse>) {
        Coroutines.io {
            callback.onComplete(userCases.getUpdateInfo.invoke())
        }
    }

    override fun navigateToGooglePlay() {
        TODO("Not yet implemented")
    }

}