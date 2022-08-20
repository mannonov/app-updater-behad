package uz.behadllc.appupdater

import android.app.Application
import uz.behadllc.app_updater_behad.BehadUpdater

class AppUpdatedApplication : Application() {

    private val BASE_URL = "https://618786e6057b9b00177f99e9.mockapi.io/uzbekquotes/v1/"

    override fun onCreate() {
        super.onCreate()
        BehadUpdater.initUpdater(BASE_URL)
    }

}