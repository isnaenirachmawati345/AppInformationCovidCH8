package com.isna.appinformationcovid.ui.edit

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.isna.appinformationcovid.R
import com.isna.appinformationcovid.data.DataStoreManager
import com.isna.appinformationcovid.data.room.UserEntity
import com.isna.appinformationcovid.databinding.FragmentEditBinding
import com.than.covidapp_challengeschapter7.ui.edit.EditFragmentViewModel
import com.than.fileprocessing.utils.PermissionUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditFragment : Fragment() {
    private var _binding : FragmentEditBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EditFragmentViewModel by viewModel()
    private var idUser: Int = DataStoreManager.DEF_ID
    private var uri = DataStoreManager.DEF_IMAGE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserPref()
        viewModel.dataUserPref.observe(viewLifecycleOwner){
            if (it != null) {
                idUser = it.id_user!!
                binding.apply {
                    etNama.setText(it.nama)
                    etEmail.setText(it.email)
                    etUsername.setText(it.username)
                    etPassword.setText(it.password)
                }
                if(it.image != DataStoreManager.DEF_IMAGE){
                    uri = it.image
                    loadImage(Uri.parse(it.image))
                }
            }
        }

        binding.profileImage.setOnClickListener {
            if(PermissionUtils.isPermissionsGranted(requireActivity(), getRequiredPermission())){
                openGallery()
            }
        }

        binding.btnCancel.setOnClickListener {
            findNavController().navigate(R.id.action_editFragment_to_homeFragment)
        }

        binding.btnEdit.setOnClickListener {
            when{
                binding.etNama.text.toString().isEmpty() ||
                        binding.etEmail.text.toString().isEmpty() ||
                        binding.etUsername.text.toString().isEmpty() ||
                        binding.etPassword.text.toString().isEmpty() -> {
                    Toast.makeText(requireContext(), "Form tidak boleh kosong!", Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    val user = UserEntity(
                        idUser,
                        binding.etNama.text.toString(),
                        binding.etEmail.text.toString(),
                        binding.etUsername.text.toString(),
                        binding.etPassword.text.toString(),
                        uri
                    )
                    viewModel.setUserPref(user)
                    viewModel.updateUser(user)
                    Toast.makeText(requireContext(), "Update Success", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_editFragment_to_homeFragment)
                }
            }
        }

    }

    private fun openGallery() {
        activity?.intent?.type = "image/*"
        galleryResult.launch(arrayOf("image/*"))
    }

    private val galleryResult =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { result ->
            result?.let {
                requireActivity().contentResolver.takePersistableUriPermission(
                    it, Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                uri = it.toString()
            }
            if (result != null) {
                loadImage(result)
            }
        }

    private fun loadImage(uri: Uri) {
        Log.d("Cek URI", uri.toString())
        binding.profileImage.setImageURI(uri)
//        val s: String = mUri.toString()
//        val mUri = Uri.parse(s)
    }

    private fun getRequiredPermission(): Array<String> {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
        } else {
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
        }
    }
}