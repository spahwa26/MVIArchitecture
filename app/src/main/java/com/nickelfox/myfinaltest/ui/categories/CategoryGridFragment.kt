package com.nickelfox.myfinaltest.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nickelfox.myfinaltest.data.models.CategoriesModel
import com.nickelfox.myfinaltest.databinding.FragmentCategoryGridBinding
import com.nickelfox.myfinaltest.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryGridFragment : Fragment(), CategoriesRecyclerAdapter.InteractionListener {

    private var _binding: FragmentCategoryGridBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SampleCategoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.userIntent.send(MainIntent.FetchCategories)
        }
        setupObservers()
    }

    private fun setupObservers() {

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is SampleCategoriesViewModel.UIState.Loading -> {
                    binding.progressBar.visibility = it.visibility
                }

                is SampleCategoriesViewModel.UIState.Error -> {
                    binding.root.showSnackBar(it.message)
                }

                else -> {}
            }
        }
//        viewModel.observeCategories().observe(viewLifecycleOwner) {
//            val adapter = CategoriesRecyclerAdapter()
//            adapter.addAll(it)
//            adapter.serInteractionListener(this)
//            binding.rvCategoryList.adapter = adapter
//        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getCategories().collect { categories ->
                    val adapter = CategoriesRecyclerAdapter()
                    adapter.addAll(categories)
                    adapter.serInteractionListener(this@CategoryGridFragment)
                    binding.rvCategoryList.adapter = adapter
                }
            }
        }
    }

    override fun onCategoryClick(category: CategoriesModel) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
