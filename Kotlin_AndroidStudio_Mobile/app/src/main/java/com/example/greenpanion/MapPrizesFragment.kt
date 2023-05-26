package com.example.greenpanion

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Layout
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapPrizesFragment : Fragment() {

    private lateinit var etUserLocation: EditText
    private lateinit var directionBtn: Button
    private lateinit var centerSpinner: Spinner
    private lateinit var centerAdapter: ArrayAdapter<CharSequence>
    private lateinit var selectedCenter: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map_prizes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardView = view.findViewById<CardView>(R.id.cardView)
        val cardView1 = view.findViewById<CardView>(R.id.cardView1)
        val cardView2 = view.findViewById<CardView>(R.id.cardView2)
        val cardView3 = view.findViewById<CardView>(R.id.cardView3)
        val hiddenViewPrize1 = view.findViewById<LinearLayout>(R.id.hiddenView)
        val hiddenViewPrize2 = view.findViewById<LinearLayout>(R.id.hiddenView1)
        val hiddenViewPrize3 = view.findViewById<LinearLayout>(R.id.hiddenView2)
        val hiddenViewPrize4 = view.findViewById<LinearLayout>(R.id.hiddenView3)
        val prize1Btn = view.findViewById<ImageButton>(R.id.down_btn)
        val prize2Btn = view.findViewById<ImageButton>(R.id.down_btn1)
        val prize3Btn = view.findViewById<ImageButton>(R.id.down_btn2)
        val prize4Btn = view.findViewById<ImageButton>(R.id.down_btn3)

        prize1Btn.setOnClickListener {
            if (hiddenViewPrize1.visibility == View.VISIBLE) {

                TransitionManager.beginDelayedTransition(cardView, AutoTransition());
                hiddenViewPrize1.visibility = View.GONE;
                prize1Btn.setImageResource(R.drawable.baseline_expand_circle_down_24);
            } else {
                TransitionManager.beginDelayedTransition(cardView, AutoTransition());
                hiddenViewPrize1.visibility = View.VISIBLE;
                prize1Btn.setImageResource(R.drawable.baseline_expand_circle_down_24);
            }
        }
        prize2Btn.setOnClickListener {
            if (hiddenViewPrize2.visibility == View.VISIBLE) {

                TransitionManager.beginDelayedTransition(cardView1, AutoTransition());
                hiddenViewPrize2.visibility = View.GONE;
                prize2Btn.setImageResource(R.drawable.baseline_expand_circle_down_24);
            } else {
                TransitionManager.beginDelayedTransition(cardView1, AutoTransition());
                hiddenViewPrize2.visibility = View.VISIBLE;
                prize2Btn.setImageResource(R.drawable.baseline_expand_circle_down_24);
            }
        }

        prize3Btn.setOnClickListener {
            if (hiddenViewPrize3.visibility == View.VISIBLE) {

                TransitionManager.beginDelayedTransition(cardView2, AutoTransition());
                hiddenViewPrize3.visibility = View.GONE;
                prize3Btn.setImageResource(R.drawable.baseline_expand_circle_down_24);
            } else {
                TransitionManager.beginDelayedTransition(cardView2, AutoTransition());
                hiddenViewPrize3.visibility = View.VISIBLE;
                prize3Btn.setImageResource(R.drawable.baseline_expand_circle_down_24);
            }
        }
        prize4Btn.setOnClickListener {
            if (hiddenViewPrize4.visibility == View.VISIBLE) {

                TransitionManager.beginDelayedTransition(cardView3, AutoTransition());
                hiddenViewPrize4.visibility = View.GONE;
                prize4Btn.setImageResource(R.drawable.baseline_expand_circle_down_24);
            } else {
                TransitionManager.beginDelayedTransition(cardView3, AutoTransition());
                hiddenViewPrize4.visibility = View.VISIBLE;
                prize4Btn.setImageResource(R.drawable.baseline_expand_circle_down_24);
            }
        }

        centerSpinner = view.findViewById(R.id.centers_spinner)
        centerAdapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.array_of_centers,
            R.layout.spinner_centers_layout
        )
        centerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        centerSpinner.adapter = centerAdapter

        centerSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                selectedCenter = centerSpinner.selectedItem.toString()
                if (parent?.id == R.id.centers_spinner) {

                    when (selectedCenter) {
                        "Centre de reciclare" -> centerAdapter = ArrayAdapter.createFromResource(
                            requireContext(),
                            R.array.array_of_centers,
                            R.layout.spinner_layout
                        )

                        else -> {
                            // code for handling other cases
                        }
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        etUserLocation = view.findViewById(R.id.user_location)
        directionBtn = view.findViewById(R.id.direction_Btn)

        directionBtn.setOnClickListener() {
            val userLocation = etUserLocation.text.toString()
            val centerSpin = centerSpinner.selectedItem.toString()

            if (userLocation.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Locația ta trebuie completată",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (selectedCenter == "Centre de reciclare") {
                Toast.makeText(requireContext(), "Selectează centrul dorit", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val uri = Uri.parse("https://www.google.com/maps/dir/$userLocation/$centerSpin")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.`package` = "com.google.android.apps.maps"
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }
        val gMap =
            (childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment).getMapAsync { googleMap ->
                googleMap.setOnMapLoadedCallback {
                    val romaniaLatLng = LatLng(45.9432, 24.9668)
                    val cameraUpdate = CameraUpdateFactory.newLatLngZoom(romaniaLatLng, 5.8f)
                    googleMap.moveCamera(cameraUpdate)

                    val cjObs = LatLng(46.756278, 23.593285)
                    googleMap.addMarker(
                        MarkerOptions().position(cjObs).title("Cluj-Napoca, Observatorului 34")
                    )

                    val cjHuedin = LatLng(46.87204, 23.03935)
                    googleMap.addMarker(
                        MarkerOptions().position(cjHuedin).title("Huedin, Bicălatu")
                    )

                    val bnBistrita = LatLng(47.135364, 24.483851)
                    googleMap.addMarker(
                        MarkerOptions().position(bnBistrita).title("Bistrița-Năsăud, Bistrița")
                    )

                    val tmTarguMures = LatLng(46.533241, 24.548864)
                    googleMap.addMarker(
                        MarkerOptions().position(tmTarguMures).title("Târgu-Mureș, Târgu-Mureș")
                    )
                    val bhOradea = LatLng(47.03381, 21.976009)
                    googleMap.addMarker(
                        MarkerOptions().position(bhOradea).title("Bihor, Oradea")
                    )
                }
            }

    }
}