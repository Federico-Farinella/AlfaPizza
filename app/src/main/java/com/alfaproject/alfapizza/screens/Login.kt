package com.alfaproject.alfapizza.screens

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

@Composable
fun LoginView() {
    val context: Context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    //var email by remember{mutableStateOf("") }
    val int = remember{mutableStateOf("")}
    val email = remember{mutableStateOf("")}
    val password = remember{ mutableStateOf("") }
    val code = remember{ mutableStateOf("") }
    var firstTeacher: MutableState<JsonObject> = remember{ mutableStateOf(JsonObject()) }
    if (!firstTeacher.value.entrySet().isEmpty())  {
        //Toast.makeText(context, "name", Toast.LENGTH_SHORT).show()
    Toast.makeText(context, firstTeacher.value.get("name").asString, Toast.LENGTH_SHORT).show()
    }
    //firstTeacher.addProperty("tel", 335)

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

        /*Button(onClick = {if (email.value.isNotEmpty()) {
            //LoggedView(context = context)
        // Continua da qui...

        } },*/
        Button(onClick = {if (email.value.isNotEmpty()) {
            //Toast.makeText(context, "name", Toast.LENGTH_SHORT).show()
            //LoggedView(context = context)
            // Prova (poi cancella tutto il Button)
            //Trovo URL
            //var firstTeacher: JsonObject
            val storage = Firebase.storage
            val storageRef = storage.reference
            val fileRef = storageRef.child("esempio.json")

            fileRef.downloadUrl
                .addOnSuccessListener { uri ->
                    val fileUrl = uri.toString()

                    val client = OkHttpClient()
                    val request = Request.Builder()
                        .url(fileUrl)
                        .build()

                    client.newCall(request).enqueue(object : Callback {
                        override fun onFailure(call: Call, e: IOException) {
                            // Gestisci eventuali errori durante il download del file
                            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                            Log.d("File Download", "Error downloading file: ${e.message}")
                            Log.e("File Download", "Error downloading file: ${e.message}")
                        }

                        override fun onResponse(call: Call, response: Response) {
                            if (response.isSuccessful) {
                                val json = response.body?.string()

                                val gson = Gson()
                                val jsonObject = gson.fromJson(json, JsonObject::class.java)
                                //val jsonArray = jsonObject.getAsJsonArray("teachers")
                                //firstTeacher = jsonArray[0].asJsonObject

                                val jsonArray = jsonObject.getAsJsonArray("teachers")
                                jsonArray[0].asJsonObject.addProperty("name", email.value)
                                firstTeacher.value.addProperty("name", email.value)

                                //firstTeacher.addProperty("name", email.value)
                                Log.d("File Download", "JSON: $email.value\n$jsonObject\n${firstTeacher.value}")


                                // Converti l'oggetto JSON modificato in una stringa JSON
                                val modifiedJson = gson.toJson(jsonObject)

                                // Crea una richiesta HTTP POST per caricare il file JSON modificato nel Firebase Storage
                                val postRequest = Request.Builder()
                                    .url(fileUrl) // Usa lo stesso URL del file originale
                                    .post(modifiedJson.toRequestBody("application/json".toMediaTypeOrNull()))
                                    .build()

                                client.newCall(postRequest).enqueue(object : Callback {
                                    override fun onFailure(call: Call, e: IOException) {
                                        // Gestisci eventuali errori durante l'invio della richiesta POST
                                        Log.e("File Upload", "Error uploading file: ${e.message}")
                                    }

                                    override fun onResponse(call: Call, response: Response) {
                                        if (response.isSuccessful) {
                                            // Il file JSON è stato modificato e caricato con successo
                                            Log.d("File Upload", "File modified and uploaded successfully")
                                        } else {
                                            // Gestisci la risposta non riuscita
                                            Log.e("File Upload", "Error response: ${response.code}")
                                        }
                                    }
                                })

                                // Ora puoi utilizzare l'oggetto JSON come desideri
                                Log.d("File Download", "JSON: $jsonObject")
                            } else {
                                // Gestisci la risposta non riuscita
                                Log.e("File Download", "Error response: ${response.code}")
                            }
                        }
                    })
                }
                .addOnFailureListener { exception ->
                    // Gestisci eventuali errori durante l'ottenimento dell'URL di download
                    Log.e("Firebase Storage", "Error getting download URL: ${exception.message}")
                }
            //Toast.makeText(context, "name", Toast.LENGTH_SHORT).show()
        //Toast.makeText(context, firstTeacher.get("name").asString, Toast.LENGTH_SHORT).show()
        }
            //firstTeacher.addProperty("tel", 335)
                         },
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






