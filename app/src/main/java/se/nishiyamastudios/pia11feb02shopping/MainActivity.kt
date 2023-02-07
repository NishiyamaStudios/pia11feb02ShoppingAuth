package se.nishiyamastudios.pia11feb02shopping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Firebase.auth.addAuthStateListener {

            if(Firebase.auth.currentUser != null) {
                supportFragmentManager.commit {
                    replace(R.id.mainFragCon, ShoppingFragment())
                }
            } else {
                supportFragmentManager.commit {
                    replace(R.id.mainFragCon, LoginFragment())
                }

            }

        }
    }
}