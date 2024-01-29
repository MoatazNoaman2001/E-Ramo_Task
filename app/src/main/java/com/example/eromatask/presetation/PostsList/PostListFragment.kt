package com.example.eromatask.presetation.PostsList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.example.eromatask.R
import com.example.eromatask.databinding.FragmentPostListBinding
import com.example.eromatask.domain.repo.FetchResult
import com.example.eromatask.presetation.PostDeatils.PostDetailsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "PostListFragment"

@AndroidEntryPoint
class PostListFragment : Fragment(), OnItemClicked {
    lateinit var binding: FragmentPostListBinding
    private val postListViewModel: PostListViewModelImpl by viewModels()
    lateinit var adapter: PostListAdapter
    lateinit var controller: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PostListAdapter(this)
        binding.postRecycleView.adapter = adapter
        controller = Navigation.findNavController(requireView())

        CoroutineScope(lifecycleScope.coroutineContext).launch {
            when (val res = postListViewModel.postList()) {
                is FetchResult.Failed -> {
                    binding.loadingProgress.isVisible = false
                    binding.errorLayout.isVisible = true
                    binding.errorTextView.text = res.Error
                    Log.d(TAG, "onViewCreated: Error : ${res.Error}")
                }

                is FetchResult.Success -> {
                    binding.loadingProgress.isVisible = false
                    binding.postRecycleView.isVisible = true
                    res.data?.forEachIndexed { index, post ->
                        Log.d(
                            TAG,
                            "onViewCreated:$index: ${post.toString()}"
                        )
                    }
                    adapter.submitList(res.data)
                }
            }

        }
    }

    override fun onClick(id: Int) {
        controller.navigate(
            R.id.action_postListFragment_to_postDetailsFragment, PostDetailsFragment.getInstance(id).requireArguments()
        )
    }
}