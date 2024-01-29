package com.example.eromatask.presetation.PostDeatils

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.eromatask.R
import com.example.eromatask.databinding.FragmentPostDetailsBinding
import com.example.eromatask.domain.repo.FetchResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


private const val TAG = "PostDetailsFragment"
@AndroidEntryPoint
class PostDetailsFragment : Fragment() {
    lateinit var binding: FragmentPostDetailsBinding
    private val viewModel : PostDetailsViewModel by viewModels()
    private var id:Int = 0
    private val ID = "id"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        id = requireArguments().getInt(ID)

        CoroutineScope(lifecycleScope.coroutineContext).launch {
            when (val res = viewModel.getPostDetails(id)) {
                is FetchResult.Failed -> {
                    Log.d(TAG, "onViewCreated: Error${res.Error}")
                    binding.loadingProgress.isVisible = false
                    binding.errorLayout.isVisible = true
                    binding.errorTextView.text = res.Error                }
                is FetchResult.Success -> {
                    binding.loadingProgress.isVisible = false
                    binding.dataLayout.isVisible = true
                    binding.body.text = res.data?.body
                    binding.title.text= "${res.data?.id}. ${res.data?.title}"
                }
            }
        }
    }

    companion object{
        fun getInstance(id:Int) = PostDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(ID , id)
            }
        }
    }
}