package se.nishiyamastudios.pia11feb02shopping

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import javax.security.auth.callback.Callback

class ShoplistViewModel : ViewModel() {

    var shopitems = mutableListOf<ShoppingItem>()

    fun addShopping(addshopname : String, addshopamount : Int, callback: () -> Unit) {

        val tempShopitem = ShoppingItem(addshopname, addshopamount)

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
                val tempShop = childsnap.getValue<ShoppingItem>()!!
                tempShop.fbid = childsnap.key
                shoplist.add(tempShop)
            }
            shopitems = shoplist
            callback() //Kör kodbiten som vi fick med oss in som en parameter

        }

    }

    fun deleteShop(delitem : ShoppingItem, callback: () -> Unit) {
        val database = Firebase.database
        val shopRef = database.getReference("androidshopping").child(Firebase.auth.currentUser!!.uid)

        shopRef.child(delitem.fbid!!).removeValue().addOnCompleteListener {
            loadShopping() {
                callback()
            }
        }


    }

}