import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
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

class LoginFragment : Fragment() {

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
                    val response = UserManager.getUser(
                        nameET.text.toString(),
                        passwordET.text.toString()
                    )

                    withContext(Dispatchers.Main) {
                        if (response != null && !response.error) {
                            tv.text = response.message

                            val token = response.data?.token
                            if (token != null) {
                                UserManager.saveToken(requireContext(), token)
                                Log.d(ContentValues.TAG, "token : $token")

                                requireActivity().finish()
                            }
                        } else {
                            tv.text = response?.error.toString();
                            // Gérer l'échec de la connexion ici
                        }
                    }
                }
            }

        return view
    }
}
