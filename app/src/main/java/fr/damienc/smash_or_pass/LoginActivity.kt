package fr.damienc.smash_or_pass

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayout
import fr.damienc.smash_or_pass.fragments.LoginFragment
import fr.damienc.smash_or_pass.fragments.RegisterFragment

class LoginActivity(context: Context, private val fragmentManager: FragmentManager) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tabLayout = findViewById<TabLayout>(R.id.tableLayout)

        tabLayout.addTab(tabLayout.newTab().setText("Login"))
        tabLayout.addTab(tabLayout.newTab().setText("Register"))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position

                when (position) {
                    0 -> {
                        val loginFragment = LoginFragment()
                        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
                        transaction.replace(R.id.loginContainer, loginFragment)
                        transaction.commit()
                    }
                    1 -> {
                        val registerFragment = RegisterFragment()
                        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
                        transaction.replace(R.id.loginContainer, registerFragment)
                        transaction.commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // Ne pas effectuer d'action supplémentaire lorsqu'un onglet est désélectionné
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // Ne pas effectuer d'action supplémentaire lorsqu'un onglet est à nouveau sélectionné
            }
        })
    }
}
