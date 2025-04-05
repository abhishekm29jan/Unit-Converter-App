package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {
    var inputvalue by remember { mutableStateOf("") }            // gives the value on input side
    var outputvalue by remember { mutableStateOf("") }           // gives the value on output side
    var inputUnit by remember { mutableStateOf("Meters") }       // show what unit is in use in the inout side
    var outputUnit by remember { mutableStateOf("Meters") }      // show what unit we got on the outout side
    var iExpanded by remember { mutableStateOf(false) }          // lets to operate the dropdown icon in input side
    var oExpanded by remember { mutableStateOf(false) }          // lets to operate the dropdown icon in output side
    var Convertionfactor by remember { mutableStateOf(1.0) }     // converts the input to definite unit in input side
    var oConvertionfactor by remember { mutableStateOf(1.0) }    // converts the unit of input side in the output side

    val customtextstyle = TextStyle(
        fontFamily = FontFamily.Cursive,
        fontSize = 27.sp,
        color = Color.Blue
    )


    fun convertunits() {
        // ?: -> elvis operator, it behaves just like an ifelse statement ie if the user is returning null then give 0.0
        var inputvaluedouble = inputvalue.toDoubleOrNull() ?: 0.0
        var result = (inputvaluedouble * Convertionfactor * 100.0 / oConvertionfactor).roundToInt()/ 100
        outputvalue = result.toString()
    }


    Column(modifier = Modifier.fillMaxSize(),                      // In columns all the UI elements are stacked below each other
        verticalArrangement = Arrangement.Top ,                   // arrangement of template vertically
        horizontalAlignment = Alignment.CenterHorizontally )       // alignment of the template horizontally
    {
        Text("Unit Converter", style = customtextstyle)
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(value = inputvalue, onValueChange = {
            inputvalue = it
            convertunits()
        },
        label = {Text("Enter the value")} )
        Spacer(modifier = Modifier.height(15.dp))
        Row {                                    // Here all the UI elements are stacked next to each other
            // Input Box
            Box(){
                Button(onClick = {iExpanded = true}) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow Drop Down")     // this icon inserts a dropdown icon into the button of the box
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded = false}) {         // dropdown menu is present inside the box but outside the button.
                    DropdownMenuItem(
                        text ={ Text("Centimeters") },
                        onClick = {
                            iExpanded = false
                            inputvalue = "Centimeters"
                            Convertionfactor = 0.01
                            convertunits()
                        }
                    )
                    DropdownMenuItem(
                        text ={ Text("Meters") },
                        onClick = {
                            iExpanded = false
                            inputvalue = "Meters"
                            Convertionfactor = 1.0
                            convertunits()
                        }
                    )
                    DropdownMenuItem(
                        text ={ Text("Feet") },
                        onClick = {
                            iExpanded = false
                            inputvalue = "Feet"
                            Convertionfactor = 0.3048
                            convertunits()
                        }
                    )
                    DropdownMenuItem(
                        text ={ Text("Millimeters") },
                        onClick = {
                            iExpanded = false
                            inputvalue = "Millimeters"
                            Convertionfactor = 0.001
                            convertunits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(15.dp))
            // Output Box
            Box() {
                Button(onClick = {oExpanded = true}) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow Drop Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = {oExpanded = false}) {  // here expanded refers to simply switching on the dropdown option within the button
                    DropdownMenuItem(
                        text ={ Text("Centimeters") },
                        onClick = {
                            oExpanded = false
                            outputvalue = "Centimeters"
                            oConvertionfactor = 0.01
                            convertunits()
                        }
                    )
                    DropdownMenuItem(
                        text ={ Text("Meters") },
                        onClick = {
                            oExpanded = false
                            outputvalue = "Meters"
                            oConvertionfactor = 1.0
                            convertunits()
                        }
                    )
                    DropdownMenuItem(
                        text ={ Text("Feet") },
                        onClick = {
                            oExpanded = false
                            outputvalue = "Feet"
                            oConvertionfactor = 0.3048
                            convertunits()
                        }
                    )
                    DropdownMenuItem(
                        text ={ Text("Millimeters") },
                        onClick = {
                            oExpanded = false
                            outputvalue = "Millimeters"
                            oConvertionfactor = 0.001
                            convertunits()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text("Results : ${outputvalue} ${outputUnit}",
            style = customtextstyle)     // this styles the text in terms of fonts size, colour, and many more..


       /* Row {  // Here all the UI elements are stacked next to each other
            val context = LocalContext.current                  // a local context is created so that it can be accessed by local users
            Button(onClick = { Toast.makeText(                 // creates a button in which onclick ensures what to do when the button is clicked
                context, "Thanks for clicking!!",             //this msg will be popped up on clicking the button when using toast
                Toast.LENGTH_LONG).show()}                   // this will show the msg for long duration
            )
            {
                Text("Click Me!!")
            }

        }
        */
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}