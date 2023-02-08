package se.nishiyamastudios.pia11feb02shopping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import se.nishiyamastudios.pia11feb02shopping.databinding.FragmentShoppingBinding

class ShoppingFragment : Fragment() {

    var _binding : FragmentShoppingBinding? = null //_namn signalerar att vi inte pratar direkt med den.
    val binding get() = _binding!! //get() = packa upp den, ge mig _binding som null, slipper använda _binding!! överallt.

    var shopadapter = ShoppingAdapter()

    val model by viewModels<ShoplistViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //utan binding
        //return inflater.inflate(R.layout.fragment_shopping, container, false)

        shopadapter.frag = this

        _binding = FragmentShoppingBinding.inflate(inflater, container, false)
        val view = binding.root //root = botten av vyn, ex. constraintlayouten
        return view //botten av vyn returneras
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null //tömmer kopplingen när vyn förstörs, för att städa upp.
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.shoppingRV.adapter = shopadapter
        binding.shoppingRV.layoutManager = LinearLayoutManager(requireContext())


        /*
        view.findViewById<Button>(R.id.logoutButton).setOnClickListener {
            Firebase.auth.signOut()
        }

         */

        binding.logoutButton.setOnClickListener {
            Firebase.auth.signOut()
        }

        binding.addShoppingButton.setOnClickListener {
            val addshopname = binding.shoppingNameET.text.toString()
            val addshopamount = binding.shoppingAmountET.text.toString()

            val amount = addshopamount.toIntOrNull() //har den inget värde blir det null
            if( amount == null) {
                //visa felmeddelande
            } else {
                model.addShopping(addshopname, amount) {
                    shopadapter.notifyDataSetChanged()
                }
            }

        }

        model.loadShopping() {
            shopadapter.notifyDataSetChanged() //Genom detta så körs koden när vi vet att fragmenten är färdig.
        }
    }

}