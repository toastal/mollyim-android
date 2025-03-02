package org.thoughtcrime.securesms.components.settings.app.help

import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.thoughtcrime.securesms.BuildConfig
import org.thoughtcrime.securesms.R
import org.thoughtcrime.securesms.components.settings.DSLConfiguration
import org.thoughtcrime.securesms.components.settings.DSLSettingsFragment
import org.thoughtcrime.securesms.components.settings.DSLSettingsText
import org.thoughtcrime.securesms.components.settings.configure
import org.thoughtcrime.securesms.util.FeatureFlags
import org.thoughtcrime.securesms.util.adapter.mapping.MappingAdapter
import org.thoughtcrime.securesms.util.navigation.safeNavigate

class HelpSettingsFragment : DSLSettingsFragment(R.string.preferences__help) {

  private val viewModel: HelpSettingsViewModel by viewModels()

  override fun bindAdapter(adapter: MappingAdapter) {
    viewModel.state.observe(viewLifecycleOwner) { state ->
      adapter.submitList(getConfiguration(state).toMappingModelList())
    }
  }

  private fun getConfiguration(state: HelpSettingsState): DSLConfiguration {
    return configure {
      externalLinkPref(
        title = DSLSettingsText.from(R.string.HelpSettingsFragment__molly_im_website),
        linkId = R.string.website_url
      )

      externalLinkPref(
        title = DSLSettingsText.from(R.string.HelpSettingsFragment__support_center),
        linkId = R.string.support_center_url
      )

      clickPref(
        title = DSLSettingsText.from(R.string.HelpSettingsFragment__contact_us),
        onClick = {
          Navigation.findNavController(requireView()).safeNavigate(R.id.action_helpSettingsFragment_to_helpFragment)
        }
      )

      dividerPref()

      textPref(
        title = DSLSettingsText.from(R.string.HelpSettingsFragment__version),
        summary = DSLSettingsText.from(BuildConfig.VERSION_NAME)
      )

      if (FeatureFlags.selfUpdater()) {
        switchPref(
          title = DSLSettingsText.from(R.string.preferences__autoupdate_molly),
          summary = DSLSettingsText.from(R.string.preferences__periodically_check_for_new_releases_and_ask_to_install_them),
          isChecked = state.updateApkEnabled,
          onClick = {
            viewModel.setUpdateApkEnabled(!state.updateApkEnabled)
          }
        )

        switchPref(
          title = DSLSettingsText.from(R.string.preferences__include_beta_updates),
          summary = DSLSettingsText.from(R.string.preferences__beta_versions_are_intended_for_testing_purposes_and_may_contain_bugs),
          isChecked = state.includeBetaEnabled,
          isEnabled = state.updateApkEnabled,
          onClick = {
            viewModel.setIncludeBetaEnabled(!state.includeBetaEnabled)
          }
        )
      }

      switchPref(
        title = DSLSettingsText.from(R.string.preferences__enable_debug_log),
        isChecked = state.logEnabled,
        onClick = {
          if (state.logEnabled) {
            MaterialAlertDialogBuilder(requireContext())
              .setMessage(R.string.HelpSettingsFragment_disable_and_delete_debug_log)
              .setPositiveButton(android.R.string.ok) { dialog, _ ->
                viewModel.setLogEnabled(false)
                dialog.dismiss()
              }
              .setNegativeButton(android.R.string.cancel, null)
              .show()
          } else {
            viewModel.setLogEnabled(true)
          }
        }
      )

      clickPref(
        title = DSLSettingsText.from(R.string.HelpSettingsFragment__debug_log),
        isEnabled = state.logEnabled,
        onClick = {
          Navigation.findNavController(requireView()).safeNavigate(R.id.action_helpSettingsFragment_to_submitDebugLogActivity)
        }
      )

      externalLinkPref(
        title = DSLSettingsText.from(R.string.HelpSettingsFragment__terms_amp_privacy_policy),
        linkId = R.string.terms_and_privacy_policy_url
      )

      textPref(
        summary = DSLSettingsText.from(
          StringBuilder().apply {
            append(getString(R.string.HelpFragment__copyright_signal_messenger))
            append("\n")
            append(getString(R.string.HelpFragment__licenced_under_the_gplv3))
          }
        )
      )
    }
  }
}
