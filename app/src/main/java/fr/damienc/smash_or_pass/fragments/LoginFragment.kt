package fr.damienc.smash_or_pass.fragments

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import fr.damienc.smash_or_pass.R
import fr.damienc.smash_or_pass.models.UserManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val nameET: EditText = view.findViewById(R.id.activity_login_et_name)
        val passwordET: EditText = view.findViewById(R.id.activity_login_et_password)
        val tv: TextView = view.findViewById(R.id.output)

        view.findViewById<Button>(R.id.activity_login_btn_create)
            .setOnClickListener {
                GlobalScope.launch(Dispatchers.IO) {
                    val text = UserManager.getUser(
                        nameET.text.toString(),
                        passwordET.text.toString()
                    ).toString()

                    withContext(Dispatchers.Main) {
                        tv.text = text
                    }
                }
            }

        return view
    }
}
