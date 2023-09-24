package fr.damienc.smash_or_pass.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import fr.damienc.smash_or_pass.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        val nameET: EditText = view.findViewById(R.id.activity_login_et_name)
        val mailET: EditText = view.findViewById(R.id.activity_login_et_mail)
        val passwordET: EditText = view.findViewById(R.id.activity_login_et_password)
        val tv: TextView = view.findViewById(R.id.output)

        view.findViewById<Button>(R.id.activity_login_btn_create)
            .setOnClickListener {
                GlobalScope.launch(Dispatchers.IO) {
                    val text = UserManager.createUser(
                        nameET.text.toString(),
                        mailET.text.toString(),
                        passwordET.text.toString()
                    )

                    withContext(Dispatchers.Main) {
                        tv.text = text.toString()
                    }
                }
            }

        return view
    }
}
