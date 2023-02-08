package se.nishiyamastudios.pia11feb02shopping

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import javax.security.auth.callback.Callback

class ShoplistViewModel : ViewModel() {

    var shopitems = mutableListOf<ShoppingItem>()

    fun addShopping(addshopname : String, callback: () -> Unit) {

        val tempShopitem = ShoppingItem(addshopname)

        val database = Firebase.database
        val shopRef = database.getReference("androidshopping").child(Firebase.auth.currentUser!!.uid)
        shopRef.push().setValue(tempShopitem).addOnCompleteListener {
            loadShopping() {
                callback()
            }
        }

        //loadShopping()

    }
    fun loadShopping(callback: () -> Unit) {

        val database = Firebase.database
        val shopRef = database.getReference("androidshopping").child(Firebase.auth.currentUser!!.uid)
        shopRef.get().addOnSuccessListener {
            val shoplist = mutableListOf<ShoppingItem>()
            it.children.forEach {childsnap ->
                shoplist.add(childsnap.getValue<ShoppingItem>()!!)
            }
            shopitems = shoplist
            callback() //Kör kodbiten som vi fick med oss in som en parameter

        }

    }

}