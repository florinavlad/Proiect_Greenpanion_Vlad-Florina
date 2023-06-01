package com.example.greenpanion

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.PopupWindow
import android.widget.TextView

class ManuallyAddPointsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manually_add_points, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val checkBoxPET = view.findViewById<CheckBox>(R.id.checkBox_PET)
        val checkBoxPS = view.findViewById<CheckBox>(R.id.checkBox_PS)
        val checkBoxPVC = view.findViewById<CheckBox>(R.id.checkBox_PVC)
        val checkBoxPaper = view.findViewById<CheckBox>(R.id.checkBox_whitePaper)
        val checkBoxSteel = view.findViewById<CheckBox>(R.id.checkBox_steel)
        val checkBoxAluminium = view.findViewById<CheckBox>(R.id.checkBox_aluminium)
        val checkBoxCopper = view.findViewById<CheckBox>(R.id.checkBox_copper)

        val hintPopup = PopupWindow(requireContext())
        val hintView = layoutInflater.inflate(R.layout.popup_hint, null)
        hintPopup.contentView = hintView
        hintPopup.width = ViewGroup.LayoutParams.WRAP_CONTENT
        hintPopup.height = ViewGroup.LayoutParams.WRAP_CONTENT

        val offset = resources.getDimensionPixelOffset(R.dimen.hint_offset)

        checkBoxPET.setOnClickListener {
            showPopup(hintPopup, hintView, checkBoxPET, offset, R.string.description_PET)
        }

        checkBoxPS.setOnClickListener {
            showPopup(hintPopup, hintView, checkBoxPS, offset, R.string.description_PS)
        }

        checkBoxPVC.setOnClickListener {
            showPopup(hintPopup, hintView, checkBoxPVC, offset, R.string.description_PVC)
        }
        checkBoxPaper.setOnClickListener {
            showPopup(hintPopup, hintView, checkBoxPVC, offset, R.string.description_hartieAlba)
        }
        checkBoxSteel.setOnClickListener {
            showPopup(hintPopup, hintView, checkBoxPVC, offset, R.string.description_otel)
        }
        checkBoxAluminium.setOnClickListener {
            showPopup(hintPopup, hintView, checkBoxPVC, offset, R.string.description_aluminiu)
        }
        checkBoxCopper.setOnClickListener {
            showPopup(hintPopup, hintView, checkBoxPVC, offset, R.string.description_cupru)
        }

    }

    private fun showPopup(popup: PopupWindow, contentView: View, anchorView: View, offset: Int, descriptionResId: Int) {
        val hintText = contentView.findViewById<TextView>(R.id.popupText)
        hintText.text = getString(descriptionResId)

        anchorView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val anchorHeight = anchorView.measuredHeight
        val y = anchorView.y - anchorHeight - offset
        popup.showAtLocation(anchorView, Gravity.START or Gravity.TOP, 0, y.toInt())

        contentView.postDelayed({
            popup.dismiss()
        }, 3000)
    }
}