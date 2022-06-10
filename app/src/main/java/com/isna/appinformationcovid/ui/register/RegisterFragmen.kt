package com.isna.appinformationcovid.ui.register

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.isna.appinformationcovid.R
import com.isna.appinformationcovid.data.DataStoreManager
import com.isna.appinformationcovid.data.room.UserEntity
import com.than.covidapp_challengeschapter7.ui.register.RegisterFragmentViewModel
import com.than.covidapp_challengeschapter7.ui.theme.Chapter8Theme
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragmen : Fragment() {

    //    private var _binding: FragmentRegisterBinding? = null
//    private val binding get() = _binding!!
    private val viewModel: RegisterFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setContent {
                Chapter8Theme {
                    Surface(
                        color = MaterialTheme.colors.background,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        RegisterBackground()
                        RegisterForm()
                    }
                }
            }
        }
    }

    @Composable
    fun RegisterBackground() {
        Image(
            painter = painterResource(
                id = R.drawable.gradation_background
            ),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }

    @Composable
    fun RegisterForm() {
        val nama = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }
        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val confirmPassword = remember { mutableStateOf("") }
        val passwordVisibility = remember { mutableStateOf(false) }
        val confirmPasswordVisibility = remember { mutableStateOf(false) }
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            val (spacer, box, icon, form, btnRegister, textLogin, btnLogin) = createRefs()
            Spacer(modifier = Modifier
                .height(89.dp)
                .constrainAs(spacer) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                })
            Image(
                painter = painterResource(id = R.drawable.bg_register),
                "",
                modifier = Modifier
                    .constrainAs(box) {
                        top.linkTo(spacer.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.ic_register),
                contentDescription = "",
                modifier = Modifier
                    .constrainAs(icon) {
                        top.linkTo(box.top)
                        bottom.linkTo(box.top)
                        end.linkTo(box.end)
                        start.linkTo(box.start)
                    }
            )
            Column(
                modifier = Modifier.constrainAs(form) {
                    top.linkTo(box.top)
                    bottom.linkTo(box.bottom)
                    end.linkTo(box.end)
                    start.linkTo(box.start)
                }
            ) {
                OutlinedTextField(
                    value = nama.value,
                    onValueChange = { nama.value = it },
                    label = { Text(text = "Nama") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = { Text(text = "Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = username.value,
                    onValueChange = { username.value = it },
                    label = { Text(text = "Username") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text(text = "Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisibility.value = !passwordVisibility.value
                        }) {
                            Icon(
                                imageVector = if (passwordVisibility.value)
                                    Icons.Filled.Visibility
                                else
                                    Icons.Filled.VisibilityOff, ""
                            )
                        }
                    },
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it },
                    label = { Text(text = "Confirm Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = {
                            confirmPasswordVisibility.value = !confirmPasswordVisibility.value
                        }) {
                            Icon(
                                imageVector = if (confirmPasswordVisibility.value)
                                    Icons.Filled.Visibility
                                else
                                    Icons.Filled.VisibilityOff, ""
                            )
                        }
                    },
                )
            }
            Button(
                onClick = {
                    when {
                        nama.value == ""
                                || email.value == ""
                                || username.value == ""
                                || password.value == ""
                                || confirmPassword.value == ""
                        -> {
                            Toast.makeText(
                                requireContext(),
                                "Form tidak boleh kosong!",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                        password.value != confirmPassword.value -> {
                            Toast.makeText(
                                requireContext(),
                                "Password tidak sama!",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                        else -> {
                            val user = UserEntity(
                                null,
                                nama.value,
                                email.value,
                                username.value,
                                password.value,
                                DataStoreManager.DEF_IMAGE
                            )
//                    viewModel.checkUsernameExists(username)
                            var isUsed = false
//                    val checkUser = viewModel.checkUsernameExists(username)
                            viewModel.userData.observe(viewLifecycleOwner) {
                                if (it != null) {
                                    if (it.username == username.value) {
                                        isUsed = true
                                    }
                                    if (isUsed) {
                                        AlertDialog.Builder(requireContext())
                                            .setMessage("Username Sudah digunakan!")
                                    } else {
                                        viewModel.insertUser(user)
                                        Toast.makeText(
                                            requireContext(),
                                            "Register Success!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        findNavController().navigate(R.id.action_registerFragmen_to_loginFragment)
                                    }
                                }
                            }
//                    if (checkUser == null){
//                        binding.tilUsername.error = ""
//                        viewModel.insertUser(user)
//                        Toast.makeText(
//                            requireContext(),
//                            "Register Success!",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
                        }
                    }
                },
                modifier = Modifier
                    .constrainAs(btnRegister) {
                        top.linkTo(box.bottom)
                        bottom.linkTo(box.bottom)
                        end.linkTo(box.end)
                        start.linkTo(box.start)
                    },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.puple_uyee))
            ) {
                Text(
                    text = "REGISTER",
                    color = colorResource(id = R.color.white),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 113.dp, vertical = 8.dp)
                )
            }
            Text(
                text = "Sudah Punya Akun?",
                modifier = Modifier
                    .padding(top = 8.dp)
                    .constrainAs(textLogin) {
                        top.linkTo(btnRegister.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.white)
            )

            Button(
                onClick = {
                    findNavController().navigate(R.id.action_registerFragmen_to_loginFragment)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp, start = 30.dp, end = 30.dp)
                    .constrainAs(btnLogin) {
                        top.linkTo(textLogin.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.puple_uyee))
            ) {
                Text(
                    text = "LOGIN",
                    color = colorResource(id = R.color.white),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}