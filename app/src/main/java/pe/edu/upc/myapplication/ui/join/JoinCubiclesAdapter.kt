package pe.edu.upc.myapplication.ui.join

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.edu.upc.myapplication.data.remote.offer.CreateOfferResponse
import pe.edu.upc.myapplication.databinding.PrototypeJoinBinding

class JoinCubiclesAdapter(
    val context: Context,
    private val listener:JoinCubiclesAdapter.CubicleItemListener):
RecyclerView.Adapter<JoinCubiclesAdapter.JoinCubiclePrototype>(){

    private val items:MutableList<CreateOfferResponse> = ArrayList()

    interface CubicleItemListener{
        fun onClickedCubicle(cubicle:CreateOfferResponse)
    }


    fun setItems(items:ArrayList<CreateOfferResponse>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JoinCubiclePrototype {
        val binding = PrototypeJoinBinding.inflate(LayoutInflater.from(context),parent,false)

        return JoinCubiclePrototype(binding,listener)
    }

    override fun onBindViewHolder(prototype: JoinCubiclePrototype, position: Int) {
        prototype.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }





    inner class JoinCubiclePrototype(
        private val binding:PrototypeJoinBinding,
        private val listener: CubicleItemListener
    ):RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        private lateinit var cubicle: CreateOfferResponse

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onClickedCubicle(cubicle)
        }

        fun bind(cubicle: CreateOfferResponse){
            this.cubicle = cubicle

            binding.ivAppleJoin.visibility = View.GONE
            binding.ivBoardJoin.visibility = View.GONE
            binding.ivChairJoin.visibility = View.GONE

            binding.tvIdCubicleJoin.text = this.cubicle.cubicleName
            binding.tvTemaJoin.text = this.cubicle.theme
            binding.tvHoraInicioJoin.text = this.cubicle.hourInit
            binding.tvHorafinJoin.text = this.cubicle.hourEnd

            if (this.cubicle.appleTv){
                binding.ivAppleJoin.visibility = View.VISIBLE
            }
            if (this.cubicle.board){
                binding.ivBoardJoin.visibility = View.VISIBLE
            }
            if (this.cubicle.seats >0){
                binding.ivChairJoin.visibility = View.VISIBLE
            }


        }

    }


}