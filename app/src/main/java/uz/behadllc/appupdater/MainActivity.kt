package uz.behadllc.appupdater

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import uz.behadllc.app_updater_behad.BehadUpdater
import uz.behadllc.app_updater_behad.core.LoaderCallback
import uz.behadllc.app_updater_behad.domain.model.UpdateResponse
import uz.behadllc.app_updater_behad.presentation.bottomsheet.UpdateBottomSheet

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val updater = BehadUpdater.getUpdater()

        val bottomSheet = UpdateBottomSheet.Companion.Builder().also {
            it.updaterFragmentManager = supportFragmentManager
            it.tag = "updateBottomSheet"
            it.imageResource = R.drawable.ic_launcher_foreground
            it.imageUrl = "https://play-lh.googleusercontent.com/GeGlCic2YQ8q9A_0-33Jm9nYpgpUfsKS8LeA-VaEkgsB_r4b9s9nMJqhcPxJdACjkA=w3840-h2160-rw"
            it.title = "Test Title"
            it.description = "Test Description"
            it.firstButtonText = "No Thanks"
            it.secondButtonText = "Update Now"
        }

        updater?.manager?.getUpdateInfo(callback = object : LoaderCallback<UpdateResponse> {
            override fun onComplete(obj: UpdateResponse) {
                Log.d("sdass", "onComplete: $obj")
                runOnUiThread {
                    bottomSheet.isForceUpdate = updater.calculateIsForceUpdate(BuildConfig.VERSION_CODE, obj.response?.minVersion ?: 0, obj.response?.currentVersion ?: 0)
                    bottomSheet.show()
                }
            }
        })
    }
}