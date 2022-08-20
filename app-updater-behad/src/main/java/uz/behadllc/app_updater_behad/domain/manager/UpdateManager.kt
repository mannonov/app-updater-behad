package uz.behadllc.app_updater_behad.domain.manager

import uz.behadllc.app_updater_behad.core.LoaderCallback
import uz.behadllc.app_updater_behad.domain.model.UpdateResponse

interface UpdateManager {

    fun getUpdateInfo(callback: LoaderCallback<UpdateResponse>)

    fun navigateToGooglePlay()

}