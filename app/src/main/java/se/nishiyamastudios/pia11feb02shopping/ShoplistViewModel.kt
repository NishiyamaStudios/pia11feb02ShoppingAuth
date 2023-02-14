package se.nishiyamastudios.pia11feb02shopping

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import javax.security.auth.callback.Callback

class ShoplistViewModel : ViewModel() {

    //var shopitems = mutableListOf<ShoppingItem>()


    val shopitems: MutableLiveData<List<ShoppingItem>> by lazy {
        MutableLiveData<List<ShoppingItem>>()
    }

    val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    // fun addShopping(addshopname : String, addshopamount : Int, callback: () -> Unit) {
    fun addShopping(addshopname : String, addshopamount : String) {

        if(addshopname == "") {
            errorMessage.value = "Ange text"
            return
        }

        val shopamount = addshopamount.toIntOrNull()
        if(shopamount == null) {
            errorMessage.value = "Skriv en siffra"
            return
        }

        errorMessage.value = ""

        val tempShopitem = ShoppingItem(addshopname, shopamount)

        val database = Firebase.database
        val shopRef = database.getReference("androidshopping").child(Firebase.auth.currentUser!!.uid)
        shopRef.push().setValue(tempShopitem).addOnCompleteListener {
            loadShopping()
        }

        //loadShopping()

    }

    //fun loadShopping(callback: () -> Unit) {
    fun loadShopping() {

        val database = Firebase.database
        val shopRef = database.getReference("androidshopping").child(Firebase.auth.currentUser!!.uid)
        shopRef.get().addOnSuccessListener {
            val shoplist = mutableListOf<ShoppingItem>()
            it.children.forEach {childsnap ->
                val tempShop = childsnap.getValue<ShoppingItem>()!!
                tempShop.fbid = childsnap.key
                shoplist.add(tempShop)
            }
            shopitems.value = shoplist
        }

    }

    //fun deleteShop(delitem : ShoppingItem, callback: () -> Unit) {
    fun deleteShop(delitem : ShoppingItem) {
        val database = Firebase.database
        val shopRef = database.getReference("androidshopping").child(Firebase.auth.currentUser!!.uid)

        shopRef.child(delitem.fbid!!).removeValue().addOnCompleteListener {
            loadShopping()
        }

    }

    //funktion för att spara vårt checkboxvärde till firebase
    fun doneShop(doneitem : ShoppingItem, isdone : Boolean) {
        val database = Firebase.database
        val shopRef = database.getReference("androidshopping").child(Firebase.auth.currentUser!!.uid)

        //spara den i var så att vi kan ändra på den, som ett mellansteg
        var saveitem = doneitem

        saveitem.shopdone = isdone
        shopRef.child(doneitem.fbid!!).setValue(saveitem).addOnCompleteListener {
            //loadShopping() //egentligen onödigt att ladda om allt när vi bara ändrar en sak
        }
    }

}