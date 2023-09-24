package fr.damienc.smash_or_pass.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.damienc.smash_or_pass.R
import fr.damienc.smash_or_pass.fragments.HomeFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreference =  getSharedPreferences("USER_DATA",MODE_PRIVATE)

        if (!sharedPreference.contains("token")) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val token  = sharedPreference.getString("token","unknown")


        val BottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        BottomNavigationView.setOnNavigationItemSelectedListener(object :BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                return when (item.itemId) {
                    R.id.home -> {
                        loadFragment(HomeFragment())
                        true
                    }
                    R.id.smashlist -> {
                        true
                    }
                    R.id.profile -> {
                        true
                    }
                    else -> false
                }
            }
        })

        loadFragment(HomeFragment())
    }
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_main_fcv, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }



}