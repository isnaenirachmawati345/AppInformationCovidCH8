package com.isna.appinformationcovid.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.isna.appinformationcovid.data.room.FavoriteEntity
import com.isna.appinformationcovid.databinding.ListItemBinding



class FavoriteAdapter(private val onItemClick: OnClickListener): RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    private val diffCallBack = object : DiffUtil.ItemCallback<FavoriteEntity>(){
        override fun areItemsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {
            return oldItem.id_favorite == newItem.id_favorite
        }

        override fun areContentsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
    private val differ = AsyncListDiffer(this, diffCallBack)
    fun submitData(value: List<FavoriteEntity>?) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ListItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.ViewHolder, position: Int) {
        val data = differ.currentList[position]
        data.let {
            holder.bind(data)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: FavoriteEntity){
            binding.apply {
                tvCountry.text = data.country_name
                tvCases.text = data.cases.toString()
                Glide.with(binding.root)
                    .load(data.image)
                    .centerCrop()
                    .into(ivFlag)
                root.setOnClickListener{
                    onItemClick.onClickItem(data)
                }
            }
        }
    }
    interface OnClickListener{
        fun onClickItem(data: FavoriteEntity)
    }

}