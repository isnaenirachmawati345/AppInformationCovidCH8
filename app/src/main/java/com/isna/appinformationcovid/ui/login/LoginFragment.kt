package com.isna.appinformationcovid.ui.login

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import com.isna.appinformationcovid.R
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
import com.isna.appinformationcovid.data.DataStoreManager
import com.isna.appinformationcovid.ui.theme.Chapter8Theme
import com.than.covidapp_challengeschapter7.ui.login.LoginFragmentViewModel
//import com.than.covidapp_challengeschapter7.data.DataStoreManager
//import com.than.covidapp_challengeschapter7.ui.login.LoginFragmentViewModel
//import com.than.covidapp_challengeschapter7.ui.theme.Chapter8Theme
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            viewModel.getUserPref()
            viewModel.userPref.observe(viewLifecycleOwner){
                if (it.id_user != DataStoreManager.DEF_ID && findNavController().currentDestination?.id == R.id.loginFragment){
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    setContent {
                        Chapter8Theme {
                            Surface(
                                color = MaterialTheme.colors.background,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Login()
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun Login() {
        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val passwordVisibility = remember { mutableStateOf(false) }
        Image(
            painter = painterResource(
                id = R.drawable.gradation_background
            ),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            val (box, icon, btnLogin, formLogin, btnRegister, textRegister) = createRefs()
            Image(
                modifier = Modifier
                    .constrainAs(box) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    },
                painter = painterResource(
                    id = R.drawable.bg_login
                ),
                contentDescription = ""
            )

            Image(
                modifier = Modifier
                    .constrainAs(icon) {
                        top.linkTo(box.top)
                        bottom.linkTo(box.top)
                        end.linkTo(box.end)
                        start.linkTo(box.start)
                    },
                painter = painterResource(
                    id = R.drawable.icon_login
                ),
                contentDescription = ""
            )

            Column(
                modifier = Modifier
                    .padding(0.dp)
                    .constrainAs(formLogin) {
                        top.linkTo(box.top)
                        bottom.linkTo(box.bottom)
                        end.linkTo(box.end)
                        start.linkTo(box.start)
                    }
            ) {
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
            }

            Button(
                onClick = {
                    if (username.value == "" || password.value == "") {
                        AlertDialog.Builder(requireContext())
                            .setTitle("")
                            .setMessage("Username/Password Kosong!")
                            .setPositiveButton("Iya"){dialog,_ ->
                                dialog.dismiss()
                            }
                            .show()
                    } else {
                        viewModel.loginUser(username.value, password.value)
                        viewModel.loginData.observe(viewLifecycleOwner) {
                            if (it == null) {
                                AlertDialog.Builder(requireContext())
                                    .setTitle("")
                                    .setMessage("Username/Password Salah!")
                                    .setPositiveButton("Iya"){dialog,_ ->
                                        dialog.dismiss()
                                    }
                                    .show()
                            } else {
                                viewModel.setUserPref(it)
                                if (findNavController().currentDestination?.id == R.id.loginFragment) {
                                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                                }
                            }
                        }
                    }
                },
                modifier = Modifier
                    .constrainAs(btnLogin) {
                        top.linkTo(box.bottom)
                        bottom.linkTo(box.bottom)
                        end.linkTo(box.end)
                        start.linkTo(box.start)
                    },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.puple_uyee))
            ) {
                Text(
                    text = "LOGIN",
                    color = colorResource(id = R.color.white),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 128.dp, vertical = 8.dp)
                )
            }

            Text(
                text = "Belom Punya Akun?",
                modifier = Modifier.constrainAs(textRegister) {
                    bottom.linkTo(btnRegister.top)
                    start.linkTo(btnRegister.start)
                    end.linkTo(btnRegister.end)
                },
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.white)
            )

            Button(
                onClick = {
                    findNavController().navigate(R.id.action_loginFragment_to_registerFragmen)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp, start = 30.dp, end = 30.dp)
                    .constrainAs(btnRegister) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.puple_uyee))
            ) {
                Text(
                    text = "REGISTER",
                    color = colorResource(id = R.color.white),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}