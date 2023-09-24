package fr.damienc.smash_or_pass.activities

import LoginFragment
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import fr.damienc.smash_or_pass.R
import fr.damienc.smash_or_pass.fragments.RegisterFragment

class LoginActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        loadFragment(LoginFragment())

        val tabLayout = findViewById<TabLayout>(R.id.tableLayout)

        tabLayout.addTab(tabLayout.newTab().setText("Login"))
        tabLayout.addTab(tabLayout.newTab().setText("Register"))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position

                when (position) {
                    0 -> {
                        loadFragment(LoginFragment())
                    }
                    1 -> {
                        loadFragment(RegisterFragment())
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.loginContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
