package com.example.asm24107

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.asm24107.ui.theme.Asm24107Theme
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(snackbarHostState: SnackbarHostState,navHostController: NavHostController) {
    val padding = 16.dp
    var message by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }
    var info by remember { mutableStateOf("") }
    var infoarray = mutableListOf<String>()


    val coroutineScope = rememberCoroutineScope()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Email")
        TextField(
            maxLines = 1,
            value = message,
            onValueChange = { message = it }
        )

        Spacer(Modifier.size(padding))
        Text("Password")
        TextField(
            maxLines = 1,
            value = pw,
            onValueChange = { pw = it }
        )
        Text("Name")

        TextField(
            maxLines = 1,
            value = name,
            onValueChange = { name = it }
        )
        Text(text = "Contact")
        TextField(
            maxLines = 1,
            value = contact,
            onValueChange = { contact = it }
        )
        Spacer(Modifier.size(padding))

        val options = listOf("18-29", "30-45", "46-60", "61-65", "65 and above")
        var expanded by remember { mutableStateOf(false) }
        var text by remember { mutableStateOf(options[0]) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            TextField(
                // The `menuAnchor` modifier must be passed to the text field for correctness.
                modifier = Modifier.menuAnchor(),
                value = text,
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                label = { Text("Age Group") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option, style = MaterialTheme.typography.bodyLarge) },
                        onClick = {
                            text = option
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }

        val (checkedState, onStateChange) = remember { mutableStateOf(false) }

        Text(text = "About me and remark")
        TextField(
            maxLines = 3,
            value = info,
            onValueChange = { info = it }
        )
        Row(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .toggleable(
                    value = checkedState,
                    onValueChange = { onStateChange(!checkedState) },
                    role = Role.Checkbox
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkedState,
                onCheckedChange = null // null recommended for accessibility with screenreaders
            )
            Text(
                text = "agree to terms and conditions",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        infoarray.add(message)
        infoarray.add(pw)
        infoarray.add(name)
        infoarray.add(contact)
        infoarray.add(text)
        infoarray.add(info)
        infoarray.add(checkedState.toString())

        Button(onClick = {
            var vo=KtorClient.volunteer(message,pw,name,contact,text,info,checkedState.toString())

            //pass 曬過去
            //then create a new porjcet
            coroutineScope.launch {
                if(message==""||pw==""||name==""||contact==""||checkedState==false){
                    snackbarHostState.showSnackbar("Register fail. You have not fill in the" +
                            " nesscary information to register")
                }
                else{
                val stringBody: String = KtorClient.postRegister(vo)
                snackbarHostState.showSnackbar("Register successful")
                navHostController.navigate("login")

            }}
        }) {
            Text(text = "Register")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    Asm24107Theme {
        Column {
            //RegisterScreen(SnackbarHostState())
        }
    }
}

@Composable
fun RegisterScreen(snackbarHostState: SnackbarHostState,navHostController: NavHostController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Register(snackbarHostState,navHostController)
    }
}