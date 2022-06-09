package com.isna.appinformationcovid.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.isna.appinformationcovid.data.model.GetAllCountryCases
import com.isna.appinformationcovid.databinding.ListItemBinding


class HomeAdapter(private val onItemClick: OnClickListener): RecyclerView.Adapter<HomeAdapter.MainViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<GetAllCountryCases>() {
        override fun areItemsTheSame(
            oldItem: GetAllCountryCases,
            newItem: GetAllCountryCases
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: GetAllCountryCases,
            newItem: GetAllCountryCases
        ): Boolean {
            return oldItem.country == newItem.country
        }
    }
    private val differ = AsyncListDiffer(this, diffCallBack)
    fun submitData(value: List<GetAllCountryCases>?) = differ.submitList(value)

    inner class MainViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: GetAllCountryCases){
            with(binding){
                tvCases.text = data.cases.toString()
                tvCountry.text = data.country
                Glide.with(binding.root)
                    .load(data.countryInfo.flag)
                    .centerCrop()
                    .into(ivFlag)
                root.setOnClickListener {
                    onItemClick.onClickItem(data)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MainViewHolder(ListItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = differ.currentList[position]
        data.let {
            holder.bind(data)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    interface OnClickListener{
        fun onClickItem(data: GetAllCountryCases)
    }
}