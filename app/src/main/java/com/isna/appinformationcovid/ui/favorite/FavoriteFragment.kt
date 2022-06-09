package com.isna.appinformationcovid.ui.favorite

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.isna.appinformationcovid.R
import com.isna.appinformationcovid.data.DataStoreManager
import com.isna.appinformationcovid.data.model.DetailCountryCases
import com.isna.appinformationcovid.data.room.FavoriteEntity
import com.isna.appinformationcovid.databinding.FragmentFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteFragmentViewModel by viewModel()
    private var idUser = DataStoreManager.DEF_ID

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("StringFormatInvalid")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserPref()
        viewModel.userPref.observe(viewLifecycleOwner){
            if (it != null){
                idUser = it.id_user!!
                binding.tvWelcome.text = getString(R.string.favorite, it.nama)
                viewModel.getDataFavorite(idUser)
            }
        }

        viewModel.dataFavorite.observe(viewLifecycleOwner){
            when {
                it.isEmpty() -> {
                    binding.apply {
                        tvEmpty.visibility = View.VISIBLE
                        rvMain.visibility = View.GONE
                        pbMain.visibility = View.GONE
                    }
                }
                else -> {
                    binding.pbMain.visibility = View.GONE
                    val adapter = FavoriteAdapter(object : FavoriteAdapter.OnClickListener{
                        override fun onClickItem(data: FavoriteEntity) {
                            val dataDetail = DetailCountryCases(
                                data.country_name,
                                data.image,
                                data.cases,
                                data.active,
                                data.death,
                                data.recovered
                            )
                            val moveToDetail = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment2(dataDetail)
                            findNavController().navigate(moveToDetail)
                        }
                    })
                    adapter.submitData(it)
                    binding.rvMain.adapter = adapter
                }
            }
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_favoriteFragment_to_homeFragment)
        }
    }
}