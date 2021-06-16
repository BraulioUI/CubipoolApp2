package pe.edu.upc.myapplication.ui.join

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import pe.edu.upc.myapplication.databinding.FragmentOfferDetailBinding
import pe.edu.upc.myapplication.viewmodel.JoinViewModel

class OfferDetailFragment : Fragment(){

    private var _binding:FragmentOfferDetailBinding? = null
    private val binding get() = _binding!!
    private val args:OfferDetailFragmentArgs by navArgs()
    private val viewModel: JoinViewModel by viewModels()



    private var offerId = "-"
    private var isAppleTv = false
    private var isBoard = false

    private var code = "-"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOfferDetailBinding.inflate(inflater,container,false)

        binding.offerDetailBoardContainer.visibility = View.GONE
        binding.offerDetailAppleContainer.visibility = View.GONE



        setupView()
        setupObservers()
        onClickButtons()

        return binding.root
    }

    private fun setupObservers() {
        viewModel.createJoinOffer.observe(viewLifecycleOwner,{offer->
            Log.i("OFFER: ",offer.code)
            Log.i("OFFER: ",offer.appleTv.toString())
            Log.i("OFFER: ",offer.board.toString())
            Log.i("OFFER: ",offer.offerId.toString())

            val action = OfferDetailFragmentDirections.actionOfferDetailFragmentToHomeActivity3()

            NavHostFragment.findNavController(this).navigate(action)

        })
    }

    private fun onClickButtons() {
        binding.cbAppleTVOfferDetail.setOnClickListener {
            viewModel.appleTv.value = binding.cbAppleTVOfferDetail.isChecked
        }

        binding.cbBoardOfferDetail.setOnClickListener {
            viewModel._board.value = binding.cbBoardOfferDetail.isChecked
        }

        binding.btnJoinOffer.setOnClickListener {
            viewModel.joinOffer(code,offerId.toInt())
        }
    }

    private fun setupView() {
        offerId = args.id
        isAppleTv = args.appleTV
        isBoard = args.board

        val sharedPreferences = activity?.getSharedPreferences("db_local",0)
        code = sharedPreferences?.getString("code","u202120211")!!



        if (isAppleTv){
            binding.offerDetailAppleContainer.visibility = View.VISIBLE
        }
        if (isBoard){
            binding.offerDetailBoardContainer.visibility = View.VISIBLE
        }

        if (viewModel.appleTv.value != null){
            binding.cbAppleTVOfferDetail.isChecked = viewModel.appleTv.value!!
        }

        if (viewModel._board.value != null){
            binding.cbBoardOfferDetail.isChecked = viewModel._board.value!!
        }

    }

}