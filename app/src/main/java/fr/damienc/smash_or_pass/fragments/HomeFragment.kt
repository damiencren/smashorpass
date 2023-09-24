package fr.damienc.smash_or_pass.fragments


import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.damienc.smash_or_pass.R
import androidx.lifecycle.lifecycleScope
import fr.damienc.smash_or_pass.adapters.CategoryAdapter
import fr.damienc.smash_or_pass.models.Category
import fr.damienc.smash_or_pass.models.SmashListData
import fr.damienc.smash_or_pass.utils.SmashListManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    val categoryList = ArrayList<Category>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val smashListList = ArrayList<SmashListData>()


        GlobalScope.launch(Dispatchers.IO) {
            val response = SmashListManager.getSmashList(
                ""
            )

            withContext(Dispatchers.Main) {
                if (response != null && !response.error) {
                    Log.d(ContentValues.TAG,response.message)
                    val smashListData = response.data
                    if (smashListData != null) {
                        for (smashList in smashListData) {
                            smashListList.add(smashList)
                        }
                    }
                } else {
                    Log.d(ContentValues.TAG,"Échec de la connexion")
                    // Gérer l'échec de la connexion ici
                }
            }
        }


        categoryList.add(Category("Popular", smashListList))
        categoryList.add(Category("Popular2", smashListList))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val horizontalScrollingView = view.findViewById<RecyclerView>(R.id.fragment_home_rv)




        horizontalScrollingView.adapter = CategoryAdapter(categoryList)
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
        }
    }



}