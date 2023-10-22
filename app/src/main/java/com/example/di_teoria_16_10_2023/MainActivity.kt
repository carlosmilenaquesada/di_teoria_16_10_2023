package com.example.di_teoria_16_10_2023


import android.app.ProgressDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.waterfallPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.di_teoria_16_10_2023.ui.theme.Di_teoria_16_10_2023Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Di_teoria_16_10_2023Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Ejecución del ejemplo de checkbox conjunto
                    /*Column {
                        var estado by rememberSaveable {
                            mutableStateOf(false)
                        }
                        val checkInfo = CheckInfo(
                            titulo = "Ejemplo 1",
                            selected = estado,
                            onCheckedChange = { estado = it }
                        )
                        EjemploCheckConjunto(checkInfo = checkInfo)

                        val myOptions = getOptions(titulos = listOf("uno", "dos", "tres"))
                        myOptions.forEach { EjemploCheckConjunto(checkInfo = it) };
                        Text(text = myOptions.get(0).selected.toString())
                    }*/

                    //ejemplo de radiobutton con visibilidad pública
                    var selected by rememberSaveable {
                        mutableStateOf("Radio 1")
                    }
                    EjemploRadioButtonPublico(selected, {selected=it})
                    Text(text = selected)
                }
            }
        }
    }
}

//TEXTBUTTON es un boton a efectos prácticos, pero en lugar de pulsar el butón, se pulsa un texto
@Composable
fun EjemploTextButton() {
    TextButton(onClick = { /*TODO*/ }) {

    }
}

//IMAGE muestra una imagen
@Composable
fun EjemploImage() {
    Image(
        painter = painterResource(id = R.drawable.download),//las imágenes se guardarn en res->drawable (R es res, drawable es la carpeta drawable y ic_launcher_background es el nombre de la imagen)
        contentDescription = "Minion",//este es la palabra que aparece cuando no se puede mostrar la imagen
        alpha = .5f//la transparencia de la imagen
        //podemos trare imágenes de cualquier lado, y metiéndolas en la carpeta drawable las podremos usar
    )

    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Imagen"
    )
}

//Imagen redonda
@Composable
fun EjemploImagenRedonda() {
    Image(
        painter = painterResource(id = R.drawable.download), contentDescription = "imagen",
        modifier = Modifier
            .clip(RoundedCornerShape(50f))//para poner la imagen circular
            .border(5.dp, Color.Blue, CircleShape)//bordes
    )
}

//ICON iconos
@Composable
fun EjemploIconos() {
    Icon(
        imageVector = Icons.Rounded.Build,//el icono
        contentDescription = "Icon",//la palabra que se muestra si la imagen no se puede mostrar.
        tint = Color.Blue//El color

        //Solo se pueden usar los iconos por defecto, si queremos otros, debemos aimportarolos
        //desde Gradle Script -> bulid.gradle.kts(Module:app) e incluimos la dependencia con:
        //implementation("androidx.compose.material:material-icons-extended:1.5.3") para incluir otros iconos
        //estos iconos son un repositorio de google
    )
}

//ProgressBar
@Composable
fun EjemploProgressBar() {
    Column {//si no se pone en un contenedor, pilla toda la pantalla
        LinearProgressIndicator(//barra de carga lineal

            color = Color.Green,//color de fondo
            trackColor = Color.Blue//color del progreso
            //,progress = .5f//si se pone progress, podemos configurar el % de carga
            //si no se pone progress, imita a una barra de carga infinita
        )
        CircularProgressIndicator(//barra de carga circular
            color = Color.Green,
            strokeWidth = 2.dp
        )
    }
}


//PROGRESSBAR CON OPCION DE APARECER/OCULTAR
@Composable
fun EjemploProgressBarDos() {
    var show by rememberSaveable() {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        if (show) {
            CircularProgressIndicator(color = Color.Red)
        }
        Button(
            onClick = { show = !show },
            modifier = Modifier.padding(20.dp)
        ) {
            Text(text = "Pulsar")
        }
    }
}

//progressbar controlando el porcentaje de carga

@Composable
fun EjemploProgressBarTres() {
    var valor by rememberSaveable {
        mutableStateOf(0f)
    }
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(progress = valor)
        Row {
            Button(onClick = {
                if (valor >= 0) {
                    valor -= .1f
                }
            }) {
                Text(text = "-")
            }
            Button(onClick = {
                if (valor < 1f) {
                    valor += .1f;
                }
            }) {
                Text(text = "+")
            }

        }
    }
}

//CONMUTADOR on/off
@Composable
fun EjemploSwitchConmutador() {
    var estado by rememberSaveable {
        mutableStateOf(true)
    }

    Switch(
        checked = estado,//qué variable guarda el estado
        onCheckedChange = { estado = !estado },//que pasa cuando el estado cambia
        colors = SwitchDefaults.colors(//configuración de colores
            uncheckedThumbColor = Color.Red,
            checkedThumbColor = Color.Gray,
            uncheckedBorderColor = Color.Blue,
            checkedBorderColor = Color.Cyan,
            uncheckedTrackColor = Color.Green,
            checkedTrackColor = Color.Magenta
        )
    )
}

//CHECKBOX

@Composable
fun EjemploCheckBox() {
    Column {

        var estado by rememberSaveable {
            mutableStateOf(false)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {

            //Los atributos son igual que el switch
            Checkbox(checked = estado, onCheckedChange = { estado = !estado })
            //los colores también se pueden configurar como en el switch
            Spacer(modifier = Modifier.width(2.dp))//esto es para dar un espacio entre elementos
            //le vamos a poner un texto al lado
            Text(text = "Marcar si está de acuerdo")
        }

        //vamos a poner un ejemplo para que si está activado, se muestre un lineal progres
        //y si no que se muestre un circular progress
        if (estado) {
            LinearProgressIndicator()
        } else {
            CircularProgressIndicator()
        }
    }
}

//EJEMPLO DE DATA CLASS PARA CONJUNTO DE CHECKBOX
@Composable
fun getOptions(titulos: List<String>): List<CheckInfo> {
    return titulos.map {
        var estado by rememberSaveable {
            mutableStateOf(false)
        }
        CheckInfo(titulo = it, selected = estado, onCheckedChange = { estado = it })
    }
}

//EJemplo  de checkbox conjunto
@Composable
fun EjemploCheckConjunto(checkInfo: CheckInfo) {
    Row {
        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = { checkInfo.onCheckedChange(!checkInfo.selected) })
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = checkInfo.titulo)
    }

}

//CHECKBOX triestado, es un check que tiene tres estados: on, off e indeterminado
//por ejemplo, se usa cuando tenemos varioos atributos seleccionados que tienen distintos valores
//en un campo, y queremos mmostrar el tick de indeterminado o diferent values
@Composable
fun EjemploTriestateCheckbox() {
    var estado by rememberSaveable {
        mutableStateOf(ToggleableState.Off)
    }
    TriStateCheckbox(state = estado, onClick = {
        estado = when (estado) {
            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.On -> ToggleableState.Off
            ToggleableState.Indeterminate -> ToggleableState.On
        }
    })

}


//RADIOBUTTON (solo uno)
@Composable
fun EjemploRadioButton() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = false, onClick = { },
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Blue,
                unselectedColor = Color.Green,
                disabledSelectedColor = Color.Red
            )
        )
        Text(text = "Radio 1")
    }
}

//RADIOBUTTON (varios)
@Composable
fun EjemploRadioButtonGrupo() {
    var selected by rememberSaveable {
        mutableStateOf("Radio 1")
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
            RadioButton(selected = selected == "Radio 1", onClick = {
                selected = "Radio 1"
            })
            Text(text = "Radio button 1")
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
            RadioButton(selected = selected == "Radio 2", onClick = {
                selected = "Radio 2"
            })
            Text(text = "Radio button 2")
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
            RadioButton(selected = selected == "Radio 3", onClick = {
                selected = "Radio 3"
            })
            Text(text = "Radio button 3")
        }
        Text(text = selected)
    }

}


//RADIOBUTTON CON VALORES DE VISIBILIDAD PÚBLICA (tenemos que declarar la variable mutable fuera, en el main)
@Composable
fun EjemploRadioButtonPublico(name: String, onItemSelected: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Radio 1", onClick = { onItemSelected("Radio 1") })
            Text(text = "Radio 1")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Radio 2", onClick = { onItemSelected("Radio 2") })
            Text(text = "Radio 2")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Radio 3", onClick = { onItemSelected("Radio 3") })
            Text(text = "Radio 3")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Radio 4", onClick = { onItemSelected("Radio 4") })
            Text(text = "Radio 4")
        }
    }
}





