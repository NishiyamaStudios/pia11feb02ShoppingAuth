package se.nishiyamastudios.pia11feb02shopping

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ShoppingAdapter : RecyclerView.Adapter<ShoppingAdapter.ViewHolder>() {


    lateinit var frag : ShoppingFragment

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val shoppingName: TextView

        init {

            shoppingName = view.findViewById(R.id.shopNameTV)

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

        holder.itemView.setOnClickListener {
            frag.model.deleteShop(currentShop)
        }

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