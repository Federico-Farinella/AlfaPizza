package com.alfaproject.alfapizza.screens

import android.content.Context
import androidx.compose.foundation.Image
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.alfaproject.alfapizza.R
import com.alfaproject.alfapizza.ui.theme.BoxColor
import com.alfaproject.alfapizza.ui.theme.MyTextFieldColor
import com.alfaproject.alfapizza.screens.MyOutlinedTextField

@Composable
fun LoginView() {
    val context: Context = LocalContext.current
    //var email by remember{mutableStateOf("") }
    val int = remember{mutableStateOf("")}
    val email = remember{mutableStateOf("")}
    val password = remember{ mutableStateOf("") }
    val code = remember{ mutableStateOf("") }

    Column(modifier = Modifier
        //.fillMaxHeight()
        .padding(0.dp, 40.dp), verticalArrangement = Arrangement.spacedBy(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        CustomRow()

        //Spacer(modifier = Modifier.height(0.dp))

        MyOutlinedTextField(field = email, label = stringResource(R.string.email),
            modifier = Modifier
                //.padding(50.dp)
                .height(80.dp)
                .weight(1.0f, false) /*onValueChange = {}*/)


        /*TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(stringResource(R.string.code)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,  // così ogni oggetto presente nella mia interfaccia sarà oggetto textfield con una sola riga
            textStyle = TextStyle(color = Color.Black),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedBorderColor = BoxColor,
                unfocusedBorderColor = BoxColor,
                disabledBorderColor = BoxColor,
                backgroundColor = MyTextFieldColor
            )
        )*/

        MyOutlinedTextField(
            field = password,
            onValueChange = { password.value = it },
            label = stringResource(R.string.password),
            modifier = Modifier
                .height(80.dp)
                .weight(1.0f, false),
            isPassword = true
        )
        
        //Spacer(modifier = Modifier.height(30.dp))
        //onClick = {Toast.makeText(context, "Stai provando il login", Toast.LENGTH_SHORT).show()}

        Button(onClick = {if (email.value.isNotEmpty()) {
            //LoggedView(context = context)
        // Continua da qui...

        } },
            colors = ButtonDefaults.buttonColors(backgroundColor = BoxColor, contentColor = Color.White),
            modifier = Modifier
                //.padding(50.dp)
                .height(50.dp)
                .width(80.dp)
                .weight(1.0f, false),
                //.border(1.dp, MyTextFieldColor, shape = RoundedCornerShape(10.dp)),
            shape = RoundedCornerShape(10.dp))  {
            Text("Login"/*, color = Color.Black*/)
        }
    }
}

@Composable
fun CustomRow() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            //.padding(horizontal = 16.dp)
            .padding(horizontal = 50.dp)
            .padding(bottom = 0.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        //Text("Titolo", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
        //Spacer(Modifier.width(8.dp))
        Box (modifier = Modifier
            .size(160.dp)//.clip(RoundedCornerShape(20))
            .fillMaxWidth()) {//.background(MyTextFieldColor)){
            Image(
                painter = painterResource(R.drawable.alfa_pizza_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(160.dp)
                    .align(Alignment.Center)
            )
        }
        /*Spacer(Modifier.width(8.dp))
        var expanded by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .clickable { expanded = true }
                .background(BoxColor, RoundedCornerShape(4.dp))
                .clip(RoundedCornerShape(5.dp))
                .border(BorderStroke(1.dp, Color.Black))
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                tint = Color.White,
                contentDescription = "Menu",
                modifier = Modifier
                    .clickable { expanded = true }
                    .size(40.dp).border(1.dp, Color.Black)
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(120.dp)
                    .background(color = BoxColor)
                    .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
                    //.border(2.dp, color = BackgroundColor)

            ) {
                DropdownMenuItem(onClick = { /* Azione 1 */ }) {
                    //modifier = Modifier.border(BorderStroke(2.dp, color = BackgroundColor))) {
                    Text("Activity 1", color = Color.White)
                }

                Divider(color = BackgroundColor, thickness = 2.dp)

                DropdownMenuItem(onClick = { /* Azione 2 */ }) {
                    Text("Activity 2", color = Color.White)
                }
                // Altre voci del menu...
            }
        }*/
    }
}






