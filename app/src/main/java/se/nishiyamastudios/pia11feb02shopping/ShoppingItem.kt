package se.nishiyamastudios.pia11feb02shopping

data class ShoppingItem(val shopname: String? = null, val shopamount : Int? = null, var shopdone : Boolean? = null) {

    var fbid : String? = null //genom att lägga den här så kan den vara var i stället för val och värdet kan ändras.

}