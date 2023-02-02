package se.nishiyamastudios.pia11feb02shopping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var isLoggedin = true

        if(isLoggedin) {
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