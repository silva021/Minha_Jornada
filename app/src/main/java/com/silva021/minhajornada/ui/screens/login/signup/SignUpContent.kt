package com.silva021.minhajornada.ui.screens.login.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Timestamp
import com.silva021.minhajornada.R
import com.silva021.minhajornada.domain.model.Profile
import com.silva021.minhajornada.ui.components.CustomTextField
import com.silva021.minhajornada.ui.components.Header
import com.silva021.minhajornada.ui.components.PrimaryButton
import com.silva021.minhajornada.ui.components.SecondButton
import com.silva021.minhajornada.ui.theme.Palette
import com.silva021.minhajornada.ui.theme.Palette.textSecondary
import com.silva021.minhajornada.ui.utils.fromHtml
import com.silva021.minhajornada.ui.utils.isValidEmail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpContent(
    errorMessage: String? = null,
    onBackPressed: () -> Unit,
    onRegister: (Profile, String) -> Unit
) {
    var fullName by remember { mutableStateOf("Lucas Silva") }
    var userName by remember { mutableStateOf("silva021") }
    var email by remember { mutableStateOf("lucasssilva021@gmail.com") }
    var password by remember { mutableStateOf("123456789") }
    var confirmPassword by remember { mutableStateOf("123456789") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Palette.backgroundColor)
            .verticalScroll(rememberScrollState())
    ) {
        Header("Criar sua Conta", onBackPressed = onBackPressed)

        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Nome Completo",
                    color = textSecondary,
                    fontWeight = FontWeight.Medium
                )

                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = fullName,
                    onValueChange = { fullName = it },
                    placeholder = "Ex: Lucas Silva",
                )

                Text(
                    text = "Nome de Usuário",
                    color = textSecondary,
                    fontWeight = FontWeight.Medium
                )

                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = userName,
                    onValueChange = { userName = it },
                    placeholder = "Ex: lucas_silva",
                )

                Text(
                    text = "Email",
                    color = textSecondary,
                    fontWeight = FontWeight.Medium
                )

                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "seu@exemplo.com"
                )

                Text(
                    text = "Senha",
                    color = textSecondary,
                    fontWeight = FontWeight.Medium
                )

                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "••••••••",
                    visualTransformation = PasswordVisualTransformation()
                )

                Text(
                    text = "Confirmar Senha",
                    color = textSecondary,
                    fontWeight = FontWeight.Medium
                )

                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    placeholder = "••••••••",
                    visualTransformation = PasswordVisualTransformation()
                )

                errorMessage?.let {
                    Text(
                        text = it,
                        color = Color.Red,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )
                }

                Text(
                    text = "Ao se cadastrar, você concorda com nossos <b>Termos de Serviço</b> e <b>Política de Privacidade.</b>".fromHtml(),
                    color = textSecondary,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp)
                )

                PrimaryButton(
                    onClick = {
                        onRegister.invoke(
                            Profile(
                                id = "",
                                name = fullName,
                                email = email,
                                userName = userName,
                                profilePictureUrl = "",
                                createdAt = Timestamp.now()
                            ),
                            password
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Cadastrar",
                    enabled = userName.isNotBlank() && fullName.isNotBlank() && email.isValidEmail() && password.isNotBlank() && confirmPassword.isNotBlank() && password == confirmPassword,
                )

//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Divider(
//                        modifier = Modifier.weight(1f),
//                        color = Color(0xFFCBD5E1)
//                    )
//
//                    Text(
//                        text = "Ou continue com",
//                        color = textSecondary,
//                        modifier = Modifier.padding(horizontal = 16.dp),
//                        fontSize = 14.sp
//                    )
//
//                    Divider(
//                        modifier = Modifier.weight(1f),
//                        color = Color(0xFFCBD5E1)
//                    )
//                }
//
//                SecondButton(
//                    onClick = { /* Login com Google */ },
//                    modifier = Modifier.fillMaxWidth(),
//                    painter = painterResource(id = R.drawable.ic_google),
//                    text = "Google",
//                )

                Spacer(Modifier.weight(1f))

                TextButton(
                    onClick = onBackPressed,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Já tem uma conta? ",
                        color = textSecondary
                    )
                    Text(
                        text = "Entrar",
                        color = Palette.primaryColor,
                        modifier = Modifier.padding(start = 4.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignUpContentPreview() {
    MaterialTheme {
        SignUpContent(
            onBackPressed = { /* No action */ },
            onRegister = { _,_ -> /* No action */ }
        )
    }
}