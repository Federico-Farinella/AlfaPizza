package com.alfaproject.alfapizza.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height

import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alfaproject.alfapizza.ui.theme.BackgroundColor
import com.alfaproject.alfapizza.ui.theme.BoxColor
import com.alfaproject.alfapizza.ui.theme.MyTextFieldColor

@Composable
fun MyOutlinedTextField(
    field: MutableState<String>,
    label: String,
    enabled: Boolean = true,
    modifier: Modifier,
    keyboardActions: KeyboardActions = KeyboardActions { },
    onValueChange: (String) -> Unit = { },
    isPassword: Boolean = false
) {
    val visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
    val keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text

    OutlinedTextField(
        value = field.value,
        onValueChange = {
            field.value =
                it    // senza questo il text della TextField non verrebbe aggiornato con i nostri input da tastiera
            onValueChange(it)       // qua verrà svolto ciò che noi inseriremo nella lambda function su
        },
        //modifier = modifier.padding(4.dp).border(2.dp, BoxColor, RoundedCornerShape(20.dp))
        //    .background(color = MyTextFieldColor, shape = RoundedCornerShape(20.dp)),
        label = {
            Text(text = label, color = BoxColor, textAlign = TextAlign.Center)
        },
        enabled = enabled,
        keyboardActions = keyboardActions,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        // così invio non dato da tasto invio keyboard ma dal segno di spunta su tastiera che se viene premuto mi permetterà di mettere in moto
        // eventualmente la keyboardAction che sarà nella lambda a riga 20 circa
        singleLine = true,  // così ogni oggetto presente nella mia interfaccia sarà oggetto textfield con una sola riga
        textStyle = TextStyle(color = Color.Black),
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BoxColor,
            unfocusedBorderColor = BoxColor,
            disabledBorderColor = BoxColor,
            backgroundColor = MyTextFieldColor
        )
    )

}

/*@Composable
fun MyDropDownMenu(

) {
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
    }
}*/