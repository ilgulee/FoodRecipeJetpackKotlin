package ilgulee.com.foodrecipejetpackkotlin.screens


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ilgulee.com.foodrecipejetpackkotlin.R
import ilgulee.com.foodrecipejetpackkotlin.databinding.RecipelistFragmentBinding

/**
 * A simple [Fragment] subclass.
 */
class RecipeListFragment : Fragment() {
    private val recipeListViewModel by lazy {
        ViewModelProviders.of(this).get(RecipeListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: RecipelistFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.recipelist_fragment, container, false)
        binding.recipeListViewModel = recipeListViewModel
        binding.setLifecycleOwner(this)
        val adapter = RecipeListAdapter()
        binding.recipeList.adapter = adapter
        recipeListViewModel.recipes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        return binding.root
    }


}
