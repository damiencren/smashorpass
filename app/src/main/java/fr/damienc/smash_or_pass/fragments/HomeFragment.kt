package fr.damienc.smash_or_pass.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.damienc.smash_or_pass.MainActivity
import fr.damienc.smash_or_pass.R
import fr.damienc.smash_or_pass.adapters.CategoryAdapter
import fr.damienc.smash_or_pass.models.Category

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val horizontalScrollingView = view.findViewById<RecyclerView>(R.id.fragment_home_rv)

        val categoryList = ArrayList<Category>()

        categoryList.add(Category("Popular"))
        categoryList.add(Category("Popular"))
        categoryList.add(Category("Popular"))
        categoryList.add(Category("Popular"))


        horizontalScrollingView.adapter = CategoryAdapter(categoryList)
        return view




    }
}