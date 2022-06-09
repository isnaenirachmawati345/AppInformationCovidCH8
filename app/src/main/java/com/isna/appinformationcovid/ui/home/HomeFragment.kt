package com.isna.appinformationcovid.ui.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.isna.appinformationcovid.R
import com.isna.appinformationcovid.data.DataStoreManager
import com.isna.appinformationcovid.data.Status
import com.isna.appinformationcovid.data.model.DetailCountryCases
import com.isna.appinformationcovid.data.model.GetAllCountryCases
import com.isna.appinformationcovid.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeFragmentViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n", "StringFormatInvalid")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserPref()
        viewModel.getAllDataCases()
        viewModel.getAllCountryCases()
        var dataUser = "Halo!"
        viewModel.userPref.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.tvWelcome.text = getString(R.string.welcome, it.nama)
                dataUser = """
                    Nama : ${it.nama}
                    Email : ${it.email}
                    Username : ${it.username}
                """.trimIndent()

                if (it.image != DataStoreManager.DEF_IMAGE){
                    loadImage(Uri.parse(it.image))
                }
            }
        }
        binding.toolbar.setOnClickListener {
            showAlertDialog(dataUser)
        }
        fetchData()
    }

    private fun showAlertDialog(dataUser: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("User Menu")
            .setMessage(dataUser)
            .setNeutralButton("Favorite") { dialog, _ ->
                findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
                dialog.dismiss()
            }
            .setNegativeButton("Edit Profile") { dialog, _ ->
                findNavController().navigate(R.id.action_homeFragment_to_editFragment)
                dialog.dismiss()
            }
            .setPositiveButton("Logout") { dialog, _ ->
                AlertDialog.Builder(requireContext())
                    .setTitle("Konfirmasi")
                    .setMessage("Yakin Mau Logout?")
                    .setPositiveButton("Iya") { dialog1, _ ->
                        viewModel.deleteUserPref()
                        Toast.makeText(requireContext(), "Logout Success!", Toast.LENGTH_SHORT)
                            .show()
                        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                        dialog1.dismiss()
                    }
                    .setNegativeButton("Tidak"){ d, _ ->
                        d.dismiss()
                    }
                    .show()
                dialog.dismiss()
            }
            .show()
    }

    @SuppressLint("StringFormatInvalid")
    private fun fetchData() {
        viewModel.countryCases.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.LOADING -> {
                    binding.pbMain.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    val adapter = HomeAdapter(object : HomeAdapter.OnClickListener {
                        override fun onClickItem(data: GetAllCountryCases) {
                            val dataDetail = DetailCountryCases(
                                data.country,
                                data.countryInfo.flag,
                                data.cases,
                                data.active,
                                data.deaths,
                                data.recovered
                            )
                            val moveToDetail =
                                HomeFragmentDirections.actionHomeFragmentToDetailFragment2(dataDetail)
                            findNavController().navigate(moveToDetail)
                        }
                    })
                    adapter.submitData(resource.data)
                    binding.rvMain.adapter = adapter
                    binding.pbMain.visibility = View.GONE
                }
                else -> {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Error")
                        .setMessage("Error Get Data : ${resource.message}")
                        .setCancelable(false)
                        .show()
                }
            }

        }
        viewModel.allDataCases.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.LOADING -> {
                    binding.pbMain.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.tvAllCases.text = getString(
                        R.string.jumlah_kasus_seluruh_dunia_s,
                        resource.data?.cases.toString()
                    )
                    binding.pbMain.visibility = View.GONE
                }
                else -> {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Error")
                        .setMessage("Error Get Data : ${resource.message}")
                        .setCancelable(false)
                        .show()
                }
            }
        }
    }

    private fun loadImage(uri: Uri) {
        Log.d("Cek URI", uri.toString())
        binding.profileImage.setImageURI(uri)
//        val s: String = mUri.toString()
//        val mUri = Uri.parse(s)
    }
}