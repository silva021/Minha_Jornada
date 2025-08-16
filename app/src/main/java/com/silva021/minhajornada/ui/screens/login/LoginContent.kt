package com.silva021.minhajornada.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.silva021.minhajornada.R
import com.silva021.minhajornada.ui.components.CustomTextField
import com.silva021.minhajornada.ui.components.PrimaryButton
import com.silva021.minhajornada.ui.components.SecondButton
import com.silva021.minhajornada.ui.theme.Palette
import com.silva021.minhajornada.ui.theme.Palette.textSecondary
import com.silva021.minhajornada.ui.utils.isValidEmail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    errorMessage: String? = null,
    onLogin: (String, String) -> Unit,
    onSignUp: () -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Palette.backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Text(
                text = "Conecte-se na sua conta",
                color = Palette.textPrimary,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Junte-se a nós e comece seus desafios!",
                color = textSecondary,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
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

            Spacer(Modifier.height(8.dp))

            PrimaryButton(
                onClick = { onLogin.invoke(email, password) },
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Entrar",
                enabled = email.isValidEmail() && password.isNotEmpty()
            )

//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Divider(
//                    modifier = Modifier.weight(1f),
//                    color = Color(0xFFCBD5E1)
//                )

//                Text(
//                    text = "Ou continue com",
//                    color = textSecondary,
//                    modifier = Modifier.padding(horizontal = 16.dp),
//                    fontSize = 14.sp
//                )
//
//                Divider(
//                    modifier = Modifier.weight(1f),
//                    color = Color(0xFFCBD5E1)
//                )
//            }
//
//            SecondButton(
//                onClick = { /* Login com Google */ },
//                modifier = Modifier.fillMaxWidth(),
//                painter = painterResource(id = R.drawable.ic_google),
//                text = "Google",
//            )


            Spacer(Modifier.weight(1f))

            TextButton(
                onClick = onSignUp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Não tem uma conta?",
                    color = textSecondary
                )
                Text(
                    text = "Cadastre-se",
                    modifier = Modifier.padding(start = 4.dp),
                    color = Palette.primaryColor,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginContentPreview() {
    MaterialTheme {
        LoginContent(null, { _, _ ->}, {})
    }
}