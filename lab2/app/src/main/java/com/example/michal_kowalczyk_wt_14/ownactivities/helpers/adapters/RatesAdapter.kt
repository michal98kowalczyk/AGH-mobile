package com.example.michal_kowalczyk_wt_14.ownactivities.helpers.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blongho.country_data.World
import com.example.michal_kowalczyk_wt_14.ownactivities.DetailsActivity
import com.example.michal_kowalczyk_wt_14.ownactivities.helpers.Messages
import com.example.michal_kowalczyk_wt_14.R
import com.example.michal_kowalczyk_wt_14.ownactivities.helpers.entities.Rate

class RatesAdapter(
        private val dataSet: List<Rate>,
        private val context: Context) :
    RecyclerView.Adapter<RatesAdapter.ViewHolder>() {

    val currencies = World.getAllCurrencies()

    val popularCurrencies = hashMapOf(
        "EUR" to R.drawable.eu,
        "USD" to R.drawable.us,
        "HKD" to R.drawable.hk

    )
    val weirdCurrencies = hashMapOf(

        "INR" to "IN",
        "BYN" to "BLR",
        "ZWL" to "ZWE",
        "GIP" to "GIB",
         "XPF" to "CH",
        "GIP" to "GI",
        "GHS" to "GH",
        "STN" to "ST",
        "IDR" to "MCO",

    )

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val flag: ImageView
        val code: TextView
        val rate: TextView
        val isGrown: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            flag = view.findViewById(R.id.imgFlag)
            code = view.findViewById(R.id.tvCode)
            rate = view.findViewById(R.id.tvRate)
            isGrown = view.findViewById(R.id.imgDownOrUp)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.rate_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val rate = dataSet[position]

        val flag = when {

            popularCurrencies.containsKey(rate.code) -> popularCurrencies[rate.code] ?: 0
            weirdCurrencies.containsKey(rate.code) -> World.getFlagOf(World.getCountryFrom(weirdCurrencies[rate.code] ?: "").id)
            else -> {
                val currencyMeta = currencies.find { curr -> curr.code.equals(rate.code) }
                World.getFlagOf(World.getCountryFrom(currencyMeta?.country ?: "").id)
            }
        }

        val imgArrow = when(rate.isGrown) {
            true -> R.drawable.arrow_up
            false -> R.drawable.arrow_down
        }

        viewHolder.flag.setImageResource(flag)
        viewHolder.code.text = rate.code
        viewHolder.rate.text = "%.4f".format(rate.mid) + "PLN"
        viewHolder.isGrown.setImageResource(imgArrow)

        viewHolder.itemView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java).apply {
                putExtra(Messages.DISPLAYED_CURRENCY_CODE, rate.code)
                putExtra(Messages.DISPLAYED_CURRENCY_NAME, rate.currency)
                putExtra(Messages.DISPLAYED_CURRENCY_TABLE, rate.table)
            }
            if (rate.code !== "") {
                context.startActivity(intent)
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}