package fr.damienc.smash_or_pass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.damienc.smash_or_pass.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showPopup()

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

    private fun showPopup() {
        val popup = `LoginActivity`(this)
        popup.setCancelable(false)
        popup.show()
    }


}