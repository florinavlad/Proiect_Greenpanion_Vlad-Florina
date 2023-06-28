package com.example.greenpanion

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.DynamicDrawableSpan
import android.text.style.ImageSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class RankingFragment : Fragment() {

    private lateinit var rvUsersRanking: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private val users = mutableListOf<User>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ranking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvUsersRanking = view.findViewById(R.id.recyclerViewUsersRanking)
        rvUsersRanking.layoutManager = LinearLayoutManager(requireContext())
        userAdapter = UserAdapter(users)
        rvUsersRanking.adapter = userAdapter

        fetchUserRanking()
    }

    private fun fetchUserRanking() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "http://192.168.43.195:8080/api/v1/auth/ranking"

        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                users.clear()

                for (i in 0 until response.length()) {
                    val jsonObject = response.getJSONObject(i)
                    val user = User(
                        jsonObject.getString("lastName"),
//                        jsonObject.getString("city"),
                        jsonObject.getInt("points")
                    )
//                    Log.d("JSON Response", response.toString())
                    users.add(user)
                }
                updateUI(users)
            },
            { error ->
                Toast.makeText(
                    requireContext(),
                    "Error fetching user ranking: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
        queue.add(request)
    }

        @SuppressLint("NotifyDataSetChanged")
        private fun updateUI(users: List<User>) {
            userAdapter.users = users
            userAdapter.positionPrefix = "Locul"
            userAdapter.notifyDataSetChanged()
        }


    class UserAdapter(var users: List<User>) :
        RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
        var positionPrefix: String = ""
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.ranking_item, parent, false)
            return UserViewHolder(view)
        }

        override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            val user = users[position]
            holder.bind(user, position + 1)        }

        override fun getItemCount(): Int {
            return users.size
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun formatPointsText(context: Context, points: Int): CharSequence {
            val icon: Drawable? = ContextCompat.getDrawable(context, R.drawable.greenpanionicon)
            val iconSize = context.resources.getDimensionPixelSize(R.dimen.icon_size)
            icon?.setBounds(0, 0, iconSize, iconSize)

            val spannableBuilder = SpannableStringBuilder()
            spannableBuilder.append(" ")
            spannableBuilder.setSpan(
                icon?.let { ImageSpan(it, DynamicDrawableSpan.ALIGN_BASELINE) },
                0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            return spannableBuilder
        }

        inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val tvFirstName: TextView = itemView.findViewById(R.id.tv_lastNameRank)
//            private val tvCity: TextView = itemView.findViewById(R.id.tv_cityRank)
            private val tvPoints: TextView = itemView.findViewById(R.id.tv_pointsRank)
            private val tvPosition: TextView = itemView.findViewById(R.id.tv_position)

            fun bind(user: User, position: Int) {
                val prefixedPosition = if (positionPrefix.isNotEmpty()) "$positionPrefix $position" else position.toString()
                tvFirstName.text = user.lastName
//                tvCity.text = user.city
                tvPoints.text = user.points.toString()
                tvPosition.text = prefixedPosition
                val formattedText = formatPointsText(itemView.context, user.points)
                val fullText = SpannableStringBuilder()
                    .append(formattedText)
                    .append(" ")
                    .append(user.points.toString())
                tvPoints.text = fullText

            }
        }
    }
}