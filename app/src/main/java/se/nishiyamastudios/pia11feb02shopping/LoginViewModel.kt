package se.nishiyamastudios.pia11feb02shopping

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel() {

    //live data till felmeddelande som vi kan lyssna på i LoginFragment
    val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun login(email : String, password : String) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //Toast.makeText(requireContext(), "Login ok.", Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    //Toast.makeText(requireContext(), "Login failed.", Toast.LENGTH_SHORT).show()
                    errorMessage.value = "Fel inloggning"
                }
            }
    }

    fun register(email : String, password : String) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //Toast.makeText(requireContext(), "Register ok.", Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    //Toast.makeText(requireContext(), "Register failed.", Toast.LENGTH_SHORT).show()
                    errorMessage.value = "Fel registrering"
                }
            }
    }


}