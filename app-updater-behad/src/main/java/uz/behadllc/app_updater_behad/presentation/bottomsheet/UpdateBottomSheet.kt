package uz.behadllc.app_updater_behad.presentation.bottomsheet

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.behadllc.app_updater_behad.R
import uz.behadllc.app_updater_behad.core.UpdaterConstants
import uz.behadllc.app_updater_behad.databinding.UpdateBottomLayoutBinding

class UpdateBottomSheet : BottomSheetDialogFragment() {

    var bottomSheetTag: String = "UpdateBottomSheet"
    var imageResource: Int? = null
    var imageUrl: String? = null
    var title: String? = null
    var description: String? = null
    var firstButtonText: String? = null
    var secondButtonText: String? = null
    var isForceUpdate: Boolean = false

    private var _binding: UpdateBottomLayoutBinding? = null
    private val binding: UpdateBottomLayoutBinding? get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = UpdateBottomLayoutBinding.inflate(layoutInflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            arguments?.let {
                setArgumentData(it)
            }
        } else {
            dismiss()
        }
        when (context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                setDarkTheme()
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                setLightTheme()
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                setLightTheme()
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setLightTheme() {
        binding?.root?.setBackgroundDrawable(requireActivity().getDrawable(R.drawable.rounded_background_light))
        binding?.firstBtnContainer?.setBackgroundDrawable(requireActivity().getDrawable(R.drawable.black_button_gradient_rounded))
        binding?.secondBtnContainer?.setBackgroundDrawable(requireActivity().getDrawable(R.drawable.black_button_gradient_rounded))
        binding?.firstBtnText?.setTextColor(requireActivity().resources.getColor(R.color.white))
        binding?.secondBtnText?.setTextColor(requireActivity().resources.getColor(R.color.white))
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setDarkTheme() {
        binding?.root?.setBackgroundDrawable(requireActivity().getDrawable(R.drawable.rounded_background_dark))
        binding?.firstBtnContainer?.setBackgroundDrawable(requireActivity().getDrawable(R.drawable.white_button_gradient_rounded))
        binding?.secondBtnContainer?.setBackgroundDrawable(requireActivity().getDrawable(R.drawable.white_button_gradient_rounded))
        binding?.firstBtnText?.setTextColor(requireActivity().resources.getColor(R.color.black))
        binding?.secondBtnText?.setTextColor(requireActivity().resources.getColor(R.color.black))
    }

    private fun setArgumentData(bundle: Bundle) {
        bottomSheetTag = bundle.getString(UpdaterConstants.BOTTOM_SHEET_TAG, bottomSheetTag)
        imageResource = bundle.getInt(UpdaterConstants.IMAGE_RESOURCE)
        imageUrl = bundle.getString(UpdaterConstants.IMAGE_URL)
        title = bundle.getString(UpdaterConstants.TITLE)
        description = bundle.getString(UpdaterConstants.DESCRIPTION)
        firstButtonText = bundle.getString(UpdaterConstants.FIRST_BUTTON_TEXT)
        secondButtonText = bundle.getString(UpdaterConstants.SECOND_BUTTON_TEXT)
        isForceUpdate = bundle.getBoolean(UpdaterConstants.IS_FORCE_UPDATE)
        setUpUI()
    }

    private fun setUpUI() {
        if (imageResource != null) {
            binding?.imgImage?.setImageResource(imageResource ?: 0)
        } else {
            binding?.imgImage?.visibility = View.GONE
        }
        if (title != null) {
            binding?.tvTitle?.text = title.toString()
        } else {
            binding?.tvTitle?.visibility = View.GONE
        }
        if (description != null) {
            binding?.tvDescription?.text = description.toString()
        } else {
            binding?.tvDescription?.visibility = View.GONE
        }
        if (firstButtonText != null) {
            binding?.firstBtnText?.text = firstButtonText.toString()
        } else {
            binding?.firstBtnContainer?.visibility = View.GONE
        }
        if (secondButtonText != null) {
            binding?.secondBtnText?.text = secondButtonText.toString()
        } else {
            binding?.secondBtnContainer?.visibility = View.GONE
        }
        if (isForceUpdate) {
            binding?.firstBtnContainer?.visibility = View.GONE
        }
    }

    override fun getTheme(): Int = R.style.RoundedBaseTipBottomSheetDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)

    fun show(fragmentManager: FragmentManager) {
        this.show(fragmentManager, bottomSheetTag)
    }

    companion object {

        fun newInstance(bundle: Bundle) = UpdateBottomSheet().apply {
            arguments = bundle
        }

        class Builder {

            lateinit var fragmentManager: FragmentManager
            var tag: String = "UpdateBottomSheet"
            var imageResource: Int? = null
            var imageUrl: String? = null
            var title: String? = null
            var description: String? = null
            var firstButtonText: String? = null
            var secondButtonText: String? = null
            var isForceUpdate: Int = UpdaterConstants.VERSIONS_ARE_EQUALS

            private var bundle: Bundle = Bundle()

            fun show() {

                Log.d("sdass", "show: $isForceUpdate")

                when (isForceUpdate) {
                    UpdaterConstants.FORCE_UPDATE -> {
                        bundle.putBoolean(UpdaterConstants.IS_FORCE_UPDATE, true)
                    }
                    UpdaterConstants.NOT_FORCE_UPDATE -> {
                        bundle.putBoolean(UpdaterConstants.IS_FORCE_UPDATE, false)
                    }
                    else -> {
                        return
                    }
                }

                bundle.putString(UpdaterConstants.BOTTOM_SHEET_TAG, tag)

                if (imageResource != null) {
                    bundle.putInt(UpdaterConstants.IMAGE_RESOURCE, imageResource ?: 0)
                }

                if (imageUrl != null) {
                    bundle.putString(UpdaterConstants.IMAGE_URL, imageUrl ?: "https://play-lh.googleusercontent.com/GeGlCic2YQ8q9A_0-33Jm9nYpgpUfsKS8LeA-VaEkgsB_r4b9s9nMJqhcPxJdACjkA=w3840-h2160-rw")
                }

                if (title != null) {
                    bundle.putString(UpdaterConstants.TITLE, title)
                }

                if (description != null) {
                    bundle.putString(UpdaterConstants.DESCRIPTION, description)
                }

                if (firstButtonText != null) {
                    bundle.putString(UpdaterConstants.FIRST_BUTTON_TEXT, firstButtonText)
                }

                if (secondButtonText != null) {
                    bundle.putString(UpdaterConstants.SECOND_BUTTON_TEXT, secondButtonText)
                }

                newInstance(bundle).show(fragmentManager)

            }

        }
    }

}