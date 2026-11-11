package com.weatherapp

import android.R.attr.enabled
import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.enableLiveLiterals
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat.enableEdgeToEdge
import com.weatherapp.ui.theme.WeatherAppTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegisterPage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPage(modifier: Modifier = Modifier) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    val activity = LocalActivity.current as Activity

    val fieldModifier = Modifier.fillMaxWidth(fraction = 0.9f)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Registre-se",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.size(14.dp))

        OutlinedTextField(
            value = name,
            label = { Text(text = "Digite seu nome") },
            modifier = fieldModifier,
            onValueChange = { name = it }
        )

        Spacer(modifier = Modifier.size(12.dp))

        OutlinedTextField(
            value = email,
            label = { Text(text = "Digite seu email") },
            modifier = fieldModifier,
            onValueChange = { email = it },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.size(12.dp))

        OutlinedTextField(
            value = password,
            label = { Text(text = "Digite a senha") },
            modifier = fieldModifier,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.size(12.dp))

        OutlinedTextField(
            value = confirmPassword,
            label = { Text(text = "Digite a senha novamente") },
            modifier = fieldModifier,
            onValueChange = { confirmPassword = it },
            visualTransformation = PasswordVisualTransformation()
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    Toast.makeText(activity, "Registro confirmado!", Toast.LENGTH_LONG).show()
                    activity.startActivity(
                        Intent(activity, LoginActivity::class.java).setFlags(
                            FLAG_ACTIVITY_SINGLE_TOP
                        )
                    )
                    activity.finish()
                },
                enabled = name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()
            ) {
                Text("Registrar")
            }

            Spacer(modifier = Modifier.size(12.dp))

            Button(
                onClick = {
                    name = ""
                    email = ""
                    password = ""
                    confirmPassword = ""
                }
            ) {
                Text("Limpar")
            }
        }
    }
}
