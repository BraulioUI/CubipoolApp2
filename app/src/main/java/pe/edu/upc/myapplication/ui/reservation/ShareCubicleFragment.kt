package pe.edu.upc.myapplication.ui.reservation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import pe.edu.upc.myapplication.R
import pe.edu.upc.myapplication.databinding.FragmentShareCubicleBinding
import pe.edu.upc.myapplication.viewmodel.ShareCubicleViewModel

class ShareCubicleFragment : Fragment(){

    private lateinit var seatsAvailableAdapter:ArrayAdapter<Int>

    private var _binding:FragmentShareCubicleBinding? = null
    private val binding get() = _binding!!

    private val args: ShareCubicleFragmentArgs by navArgs()
    private val viewModel: ShareCubicleViewModel by viewModels()

    private var seatsAvailable: MutableList<Int> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentShareCubicleBinding.inflate(inflater,container,false)

        setupView()
        viewModel.initSeatsAvailable()
        initSelectSeats()
        setupObservers()

        onClickButtons()

        return binding.root
    }

    private fun setupView(){
        val offerId = args.offerId
        val _reservationId = args.reservationId

        viewModel.reservationId.value = _reservationId?.toInt()!!

        if (viewModel.appleTv.value == null||viewModel.board.value == null){
            viewModel.appleTv.value = false
            viewModel.board.value = false
        }

        if (offerId != null){
            viewModel.isEdit.value = true
            binding.btnDeleteOffer.visibility = View.VISIBLE
            viewModel.offerId.value = offerId.toInt()

            viewModel.getOfferDetail()
            //setOffer()
        }else{
            viewModel.isEdit.value = false
        }
    }

    private fun setOffer() {
        binding.cbAppleTVShare.isChecked = viewModel.appleTv.value!!
        binding.cbBoardShared.isChecked = viewModel.board.value!!
        //binding.asientosCompartir.setSelection(viewModel.seat.value!! -1)
    }

    private fun setupObservers(){
        viewModel._seatsAvailable.observe(viewLifecycleOwner, { seats ->
            seatsAvailableAdapter=
                ArrayAdapter(binding.root.context, R.layout.support_simple_spinner_dropdown_item,seats)

            seatsAvailable = seats

            binding.asientosCompartir.adapter = seatsAvailableAdapter
        })

        viewModel.appleTv.observe(viewLifecycleOwner,{apple ->
            binding.cbAppleTVShare.isChecked = apple
        })

        viewModel.board.observe(viewLifecycleOwner,{board->
            binding.cbBoardShared.isChecked = board
        })

        viewModel.seat.observe(viewLifecycleOwner,{seat->
            binding.asientosCompartir.setSelection(seat-1)
        })

        viewModel.isOfferDeleted.observe(viewLifecycleOwner,{delete->
            if (delete == true){
                val action = ShareCubicleFragmentDirections
                    .actionShareCubicleFragmentToReservationDetailFragment(viewModel.reservationId.value!!)

                validateTransition("Oferta Eliminada")

                NavHostFragment.findNavController(this).navigate(action)
            }
        })
        /*viewModel.isOfferDetail.observe(viewLifecycleOwner,{ isOffer ->
            Log.i("ShareCubicle: ",isOffer.toString())


            viewModel.appleTv.observe(viewLifecycleOwner,{apple ->
                binding.cbAppleTVShare.isChecked = apple
            })

            viewModel.board.observe(viewLifecycleOwner,{board->
                binding.cbBoardShared.isChecked = board
            })

            viewModel.seat.observe(viewLifecycleOwner,{seat->
                binding.asientosCompartir.setSelection(seat-1)
            })
            //binding.cbAppleTVShare.isChecked = viewModel.appleTv.value!!
            //binding.cbBoardShared.isChecked = viewModel.board.value!!

            //binding.asientosCompartir.setSelection(viewModel.seat.value!!-1)
        })*/

        viewModel.isToReservation.observe(viewLifecycleOwner,{ isTo ->
            if (isTo == true){
                val action = ShareCubicleFragmentDirections
                    .actionShareCubicleFragmentToReservationDetailFragment(viewModel.reservationId.value!!)

                validateTransition("Oferta Publicada")

                NavHostFragment.findNavController(this).navigate(action)
            }

        })

    }


    private fun createOffer() {
        viewModel.createOffer()
    }


    private fun onClickButtons(){
        binding.btnCreateOffer.setOnClickListener {
            createOffer()
        }

        binding.btnDeleteOffer.setOnClickListener {
            viewModel.deleteOffer()
        }

        binding.cbAppleTVShare.setOnClickListener {
            viewModel.appleTv.value = binding.cbAppleTVShare.isChecked
            validateTransition(viewModel.appleTv.value.toString())
        }

        binding.cbBoardShared.setOnClickListener {
            viewModel.board.value = binding.cbBoardShared.isChecked
            validateTransition(viewModel.board.value.toString())
        }

        binding.btnBackShareCubicle.setOnClickListener {

        }
    }

    private fun initSelectSeats(){

        if (viewModel.seat.value == null){
            viewModel.seat.value = 1
        }

        binding.asientosCompartir.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.seat.value = seatsAvailable[position]
                //viewModel.seat.postValue(seatsAvailable[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.i("ShareCubicle: ","NOTHING SELECTED")
            }

        }

    }

    private fun validateTransition(message:String) {
        Toast.makeText(this.context,message, Toast.LENGTH_LONG).show()
    }


}