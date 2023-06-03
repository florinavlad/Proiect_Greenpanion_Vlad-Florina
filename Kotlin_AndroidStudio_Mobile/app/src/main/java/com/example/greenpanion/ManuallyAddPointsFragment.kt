package com.example.greenpanion

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation

class ManuallyAddPointsFragment : Fragment() {

    private lateinit var model: SharedViewModel
    private lateinit var calculatePctBtn: Button
    private lateinit var checkBoxSteel: CheckBox
    private lateinit var checkBoxAluminium: CheckBox
    private lateinit var checkBoxCopper: CheckBox
    private lateinit var checkBoxPET: CheckBox
    private lateinit var checkBoxPS: CheckBox
    private lateinit var checkBoxPVC: CheckBox
    private lateinit var checkBoxPaper: CheckBox
    private lateinit var checkBoxNewspaper: CheckBox
    private lateinit var checkBoxCarton: CheckBox
    private lateinit var petQuantity: EditText
    private lateinit var pvcQuantity: EditText
    private lateinit var psQuantity: EditText
    private lateinit var hartieQuantity: EditText
    private lateinit var ziarQuantity: EditText
    private lateinit var cartonQuantity: EditText
    private lateinit var otelQuantity: EditText
    private lateinit var aluminiuQuantity: EditText
    private lateinit var cupruQuantity: EditText


    private val pointsForPET = 25
    private val pointsForPVC = 40
    private val pointsForPS = 15
    private val pointsForHartie = 10
    private val pointsForZiarRevista = 30
    private val pointsForCartonPunga = 18
    private val pointsForOtel = 40
    private val pointsForAluminiu = 25
    private val pointsForCupru = 50

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

    @SuppressLint("ResourceType", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        calculatePctBtn = view.findViewById(R.id.calculatePct_Btn)

        checkBoxPET = view.findViewById(R.id.checkBox_PET)
        checkBoxPS = view.findViewById(R.id.checkBox_PS)
        checkBoxPVC = view.findViewById(R.id.checkBox_PVC)
        checkBoxPaper = view.findViewById(R.id.checkBox_whitePaper)
        checkBoxNewspaper = view.findViewById(R.id.checkBox_newspaper)
        checkBoxCarton = view.findViewById(R.id.checkBox_carton)
        checkBoxSteel = view.findViewById(R.id.checkBox_steel)
        checkBoxAluminium = view.findViewById(R.id.checkBox_aluminium)
        checkBoxCopper = view.findViewById(R.id.checkBox_copper)
        petQuantity = view.findViewById(R.id.quantityField1)
        pvcQuantity = view.findViewById(R.id.quantityField2)
        psQuantity = view.findViewById(R.id.quantityField3)
        hartieQuantity = view.findViewById(R.id.quantityField4)
        ziarQuantity = view.findViewById(R.id.quantityField5)
        cartonQuantity = view.findViewById(R.id.quantityField6)
        otelQuantity = view.findViewById(R.id.quantityField7)
        aluminiuQuantity = view.findViewById(R.id.quantityField8)
        cupruQuantity = view.findViewById(R.id.quantityField9)

        calculatePctBtn.setOnClickListener {
            val petQuantityValue = petQuantity.text.toString().toIntOrNull() ?: 0
            val pvcQuantityValue = pvcQuantity.text.toString().toIntOrNull() ?: 0
            val psQuantityValue = psQuantity.text.toString().toIntOrNull() ?: 0
            val hartieQuantityValue = hartieQuantity.text.toString().toIntOrNull() ?: 0
            val ziarQuantityValue = ziarQuantity.text.toString().toIntOrNull() ?: 0
            val cartonQuantityValue = cartonQuantity.text.toString().toIntOrNull() ?: 0
            val otelQuantityValue = otelQuantity.text.toString().toIntOrNull() ?: 0
            val aluminiuQuantityValue = aluminiuQuantity.text.toString().toIntOrNull() ?: 0
            val cupruQuantityValue = cupruQuantity.text.toString().toIntOrNull() ?: 0

            var totalPoints = 0

            if (checkBoxPET.isChecked) {
                totalPoints += petQuantityValue * pointsForPET
            }
            if (checkBoxPVC.isChecked) {
                totalPoints += pvcQuantityValue * pointsForPVC
            }
            if (checkBoxPS.isChecked) {
                totalPoints += psQuantityValue * pointsForPS
            }
            if (checkBoxPaper.isChecked) {
                totalPoints += hartieQuantityValue * pointsForHartie
            }
            if (checkBoxNewspaper.isChecked) {
                totalPoints += ziarQuantityValue * pointsForZiarRevista
            }
            if (checkBoxCarton.isChecked) {
                totalPoints += cartonQuantityValue * pointsForCartonPunga
            }
            if (checkBoxSteel.isChecked) {
                totalPoints += otelQuantityValue * pointsForOtel
            }
            if (checkBoxAluminium.isChecked) {
                totalPoints += aluminiuQuantityValue * pointsForAluminiu
            }
            if (checkBoxCopper.isChecked) {
                totalPoints += cupruQuantityValue * pointsForCupru
            }
            Navigation.findNavController(view)
                .navigate(R.id.action_manuallyAddPointsFragment_to_calculatePointsFragment)
            model.calculateTotalPoints(totalPoints)

            viewLifecycleOwner.lifecycle.addObserver(object : LifecycleEventObserver {
                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                    if (event == Lifecycle.Event.ON_RESUME) {
                        checkBoxPET.isChecked = false
                        checkBoxPS.isChecked = false
                        checkBoxPVC.isChecked = false
                        checkBoxPaper.isChecked = false
                        checkBoxNewspaper.isChecked = false
                        checkBoxCarton.isChecked = false
                        checkBoxSteel.isChecked = false
                        checkBoxAluminium.isChecked = false
                        checkBoxCopper.isChecked = false
                        petQuantity.setText("")
                        pvcQuantity.setText("")
                        psQuantity.setText("")
                        hartieQuantity.setText("")
                        ziarQuantity.setText("")
                        cartonQuantity.setText("")
                        otelQuantity.setText("")
                        aluminiuQuantity.setText("")
                        cupruQuantity.setText("")
                    }
                }
            })
        }

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
            showPopup(hintPopup, hintView, checkBoxPaper, offset, R.string.description_hartieAlba)
        }
        checkBoxSteel.setOnClickListener {
            showPopup(hintPopup, hintView, checkBoxSteel, offset, R.string.description_otel)
        }
        checkBoxAluminium.setOnClickListener {
            showPopup(hintPopup, hintView, checkBoxAluminium, offset, R.string.description_aluminiu)
        }
        checkBoxCopper.setOnClickListener {
            showPopup(hintPopup, hintView, checkBoxCopper, offset, R.string.description_cupru)
        }

    }

    private fun showPopup(
        popup: PopupWindow,
        contentView: View,
        anchorView: View,
        offset: Int,
        descriptionResId: Int
    ) {
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