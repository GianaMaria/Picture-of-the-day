package com.example.pictureoftheday.ui.fragments

import android.content.Intent
import android.graphics.Color.WHITE
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BulletSpan
import android.text.style.ForegroundColorSpan
import android.view.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.example.pictureoftheday.R
import com.example.pictureoftheday.ui.activity.ApiActivity
import com.example.pictureoftheday.ui.activity.MainActivity
import com.example.pictureoftheday.util.enlargingThePicture
import com.example.pictureoftheday.util.toast
import com.example.pictureoftheday.viewModel.LiveDataOfTheDayData
import com.example.pictureoftheday.viewModel.PictureOfTheDayViewModel
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheet_layout.*
import kotlinx.android.synthetic.main.fragment_earth_photo.*
import kotlinx.android.synthetic.main.main_fragment.*

class PictureOfTheDayFragment : Fragment() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProviders.of(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData()
            .observe(viewLifecycleOwner, { renderData(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
        input_layout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${input_edit_text.text.toString()}")
            })
        }
        setBottomAppBar(view)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> toast("Favourite")

            R.id.app_bar_settings -> activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, SettingsFragment())?.addToBackStack(null)?.commit()

            android.R.id.home -> {
                activity?.let {
                    BottomNavigationDrawerFragment().show(it.supportFragmentManager, "tag")
                }
            }
            R.id.app_bar_api -> activity?.let {
                startActivity(Intent(it, ApiActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun renderData(data: LiveDataOfTheDayData) {
        when (data) {
            is LiveDataOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                val description = serverResponseData.explanation
                val title = serverResponseData.title
                if (url.isNullOrEmpty()) {
                    //showError("??????????????????, ?????? ???????????? ????????????")
                    toast("Link is empty")
                } else {
                    //showSuccess()
                    image_view.load(url) {
                        lifecycle(this@PictureOfTheDayFragment)
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.ic_no_photo_vector)
                    }

                    enlargingThePicture(image_view, main)

                    if (description == null || title == null) {
                        toast("???? ?????????????? ???????????????? ??????")
                    } else {
                        val leadingMargin = 16
                        val spannableDescription = SpannableString(description)
                        spannableDescription.setSpan(
                            ForegroundColorSpan(WHITE),
                            0,
                            description.length,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )

                        spannableDescription.setSpan(
                            BulletSpan(leadingMargin), 0, description.length,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )

                        val spannableTitle = SpannableString(title)
                        spannableTitle.setSpan(
                            ForegroundColorSpan(resources.getColor(R.color.PLight)),
                            0, title.length,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )

                        bottom_sheet_description_header.text = title
                        bottom_sheet_description.text = description
                    }
                }
            }
            is LiveDataOfTheDayData.Loading -> {
                //showLoading()
            }
            is LiveDataOfTheDayData.Error -> {
                //showError(data.error.message)
                toast(data.error.message)
            }
        }
    }


    private fun setBottomAppBar(view: View) {
        val context = activity as MainActivity
        context.setSupportActionBar(view.findViewById(R.id.bottom_app_bar))
        setHasOptionsMenu(true)
        fab.setOnClickListener {
            if (isMain) {
                isMain = false
                bottom_app_bar.navigationIcon = null
                bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_back_fab))
                bottom_app_bar.replaceMenu(R.menu.menu_bottom_bar_other_screen)

            } else {
                isMain = true
                bottom_app_bar.navigationIcon =
                    ContextCompat.getDrawable(context, R.drawable.ic_hamburger_menu_bottom_bar)
                bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_plus_fab))
                bottom_app_bar.replaceMenu(R.menu.menu_bottom_bar)
            }
        }
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }


    companion object {
        fun newInstance() = PictureOfTheDayFragment()
        private var isMain = true
    }
}
