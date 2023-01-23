package com.abdallah_abdelazim.asteroidradar.ui.main

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.abdallah_abdelazim.asteroidradar.R
import com.abdallah_abdelazim.asteroidradar.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()

    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var asteroidsAdapter: AsteroidsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        setupAsteroidsRv()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collectLatest { uiState ->
                    renderUiState(uiState)
                }
        }

    }

    private fun setupAsteroidsRv() {
        asteroidsAdapter = AsteroidsAdapter { asteroid ->
            MainFragmentDirections.actionMainFragmentToDetailFragment(asteroid)
        }
        binding.rvAsteroid.setHasFixedSize(true)
        binding.rvAsteroid.adapter = asteroidsAdapter
    }

    private fun renderUiState(uiState: MainUiState) = with(uiState) {
        binding.uiState = uiState
        asteroidsAdapter.asteroidUiModels = asteroids
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_week_asteroid -> {
                // TODO
                true
            }
            R.id.action_today_asteroid -> {
                // TODO
                true
            }
            R.id.action_saved_asteroid -> {
                // TODO
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
