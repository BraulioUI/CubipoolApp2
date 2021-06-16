package pe.edu.upc.myapplication.ui.join

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.upc.myapplication.data.remote.offer.CreateOfferResponse
import pe.edu.upc.myapplication.databinding.FragmentJoinCubiclesBinding
import pe.edu.upc.myapplication.viewmodel.JoinViewModel

class JoinCubiclesFragment:Fragment(),JoinCubiclesAdapter.CubicleItemListener {

    private var _binding:FragmentJoinCubiclesBinding?=null
    private val binding get() = _binding!!
    private val viewModel:JoinViewModel by viewModels()

    private lateinit var adapter: JoinCubiclesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentJoinCubiclesBinding.inflate(inflater,container,false)

        viewModel.initJoinCubiclesOffer()
        setupRecyclerView()
        setupObservers()

        return binding.root
    }

    private fun setupObservers() {
        viewModel.joinCubiclesOffer.observe(viewLifecycleOwner,{cubicles->
            if (cubicles!=null){
                Log.i("Join: ",cubicles[0].cubicleName)
                adapter.setItems(cubicles as ArrayList<CreateOfferResponse>)
            }else{
                Log.i("Join: ","No hay OFERTAS")
                adapter.setItems(ArrayList())
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = JoinCubiclesAdapter(binding.root.context,this)
        binding.rvOffersJoin.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvOffersJoin.adapter = adapter
    }

    override fun onClickedCubicle(cubicle: CreateOfferResponse) {
        val action = JoinCubiclesFragmentDirections.actionJoinCubiclesFragmentToOfferDetailFragment(
            cubicle.offerId.toString(),cubicle.appleTv,cubicle.board)

        NavHostFragment.findNavController(this).navigate(action)
    }

}