package com.example.projet_dev_mobile.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projet_dev_mobile.databinding.FragmentHomeBinding
import com.example.projet_dev_mobile.adapter.StationAdapter
import com.example.projet_dev_mobile.adapter.StationApi
import com.example.projet_dev_mobile.api.RetrofitHelper
import com.example.projet_dev_mobile.model.allStations
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val recyclerViewStation = binding.recyclerViewStation
        val progressBarStations = binding.progressBarStations

        homeViewModel.stations.observe(viewLifecycleOwner) {
            recyclerViewStation.adapter = StationAdapter(it, requireContext())
            recyclerViewStation.layoutManager = LinearLayoutManager(requireContext())
            progressBarStations.visibility = View.GONE

            allStations=it
        }

        val stationApi = RetrofitHelper().getInstance().create(StationApi::class.java)
        GlobalScope.launch {
            val result = stationApi.getStations()
            Log.d("home",result.body().toString())
            homeViewModel.stations.postValue(result.body())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}