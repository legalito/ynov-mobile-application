package com.example.projet_dev_mobile.ui.park

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projet_dev_mobile.adapter.ParkAdapter
import com.example.projet_dev_mobile.adapter.ParkApi
import com.example.projet_dev_mobile.api.RetrofitHelper
import com.example.projet_dev_mobile.databinding.FragmentParkBinding
import com.example.projet_dev_mobile.model.allParks
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ParkFragment : Fragment() {

    private var _binding: FragmentParkBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val parkViewModel =
            ViewModelProvider(this)[ParkViewModel::class.java]

        _binding = FragmentParkBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val recyclerViewPark = binding.recyclerViewPark

        parkViewModel.parks.observe(viewLifecycleOwner) {
            recyclerViewPark.adapter = ParkAdapter(it, requireContext())
            recyclerViewPark.layoutManager = LinearLayoutManager(requireContext())

            allParks = it
        }

        val parkApi = RetrofitHelper().getInstance().create(ParkApi::class.java)
        GlobalScope.launch {
            val result = parkApi.getParks()
            Log.d("Park",result.body().toString())
            parkViewModel.parks.postValue(result.body())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}