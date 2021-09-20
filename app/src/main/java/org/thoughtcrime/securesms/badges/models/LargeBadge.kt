package org.thoughtcrime.securesms.badges.models

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.thoughtcrime.securesms.R
import org.thoughtcrime.securesms.mms.GlideApp
import org.thoughtcrime.securesms.util.MappingAdapter
import org.thoughtcrime.securesms.util.MappingModel
import org.thoughtcrime.securesms.util.MappingViewHolder

data class LargeBadge(
  val badge: Badge
) {

  class Model(val largeBadge: LargeBadge, val shortName: String) : MappingModel<Model> {
    override fun areItemsTheSame(newItem: Model): Boolean {
      return newItem.largeBadge.badge.id == largeBadge.badge.id
    }

    override fun areContentsTheSame(newItem: Model): Boolean {
      return newItem.largeBadge == largeBadge && newItem.shortName == shortName
    }
  }

  class ViewHolder(itemView: View) : MappingViewHolder<Model>(itemView) {

    private val badge: ImageView = itemView.findViewById(R.id.badge)
    private val name: TextView = itemView.findViewById(R.id.name)
    private val description: TextView = itemView.findViewById(R.id.description)

    override fun bind(model: Model) {
      GlideApp.with(badge)
        .load(model.largeBadge.badge)
        .into(badge)

      name.text = model.largeBadge.badge.name
      description.text = model.largeBadge.badge.resolveDescription(model.shortName)
    }
  }

  companion object {
    fun register(mappingAdapter: MappingAdapter) {
      mappingAdapter.registerFactory(Model::class.java, MappingAdapter.LayoutFactory({ ViewHolder(it) }, R.layout.view_badge_bottom_sheet_dialog_fragment_page))
    }
  }
}
