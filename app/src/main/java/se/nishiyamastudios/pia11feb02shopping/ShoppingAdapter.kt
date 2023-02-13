package se.nishiyamastudios.pia11feb02shopping

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ShoppingAdapter : RecyclerView.Adapter<ShoppingAdapter.ViewHolder>() {


    lateinit var frag : ShoppingFragment

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val shoppingName: TextView
        val shoppingDelete: ImageView
        val shoppingCheckbox: CheckBox

        init {

            shoppingName = view.findViewById(R.id.shopNameTV)
            shoppingDelete = view.findViewById(R.id.shopDeleteImage)
            shoppingCheckbox = view.findViewById(R.id.shopCheckbox)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // viewType: Int = kan definiera olika typer av rader, produktrad, headerrad etc..

        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentShop = frag.model.shopitems.value!![position]

        if(currentShop.shopamount == null) {
            holder.shoppingName.text = currentShop.shopname
        } else {
            holder.shoppingName.text = currentShop.shopname + " " + currentShop.shopamount!!.toString()
        }

        //TODO: Markera köp/ej köpt

        holder.shoppingDelete.setOnClickListener {
            frag.model.deleteShop(currentShop)
        }

        //ligga och lyssna på om checkboxen är ikryssad eller inte
        holder.shoppingCheckbox.setOnCheckedChangeListener { compoundButton, shopchecked ->
            Log.i("pia11debug", shopchecked.toString())
        }

        /*
        holder.itemView.setOnClickListener {
            // TODO: Gå till läs mer
            frag.model.deleteShop(currentShop)
        }

         */

    }

    override fun getItemCount(): Int {
        //value?.let innebär att detta utförs om value inte är null
        frag.model.shopitems.value?.let {
            return it.size
        }
        //annars, om värdet inte finns, return 0
        return 0
    }

}