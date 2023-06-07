package com.alfaproject.alfapizza

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alfaproject.alfapizza.screens.LoginView
import com.alfaproject.alfapizza.ui.theme.AlfaPizzaTheme
import com.alfaproject.alfapizza.ui.theme.BackgroundColor

class MainActivity : ComponentActivity() {
    //private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Creazione del NavController
        //navController = rememberNavController()

        setContent {
            //navController = rememberNavController()

            AlfaPizzaTheme {
                /*NavHost(navController, startDestination = "login") {
                    composable("login") { LoginView(navController) }
                    composable("logged") { LoggedView(navController) }
                }*/

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    //color = MaterialTheme.colors.background
                    //color = Color.Black
                    color = BackgroundColor,
                    elevation = 4.dp,
                    //shape = RoundedCornerShape(16.dp)
                )
                {
                    /*Image(
                        painter = painterResource(R.drawable.pizza),
                        contentDescription = "Immagine di sfondo",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )*/
                    LoginView()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AlfaPizzaTheme {
        Greeting("Android")
    }
}