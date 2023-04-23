package com.sourav.multicontactpicker

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.sourav.contact_picker.design.PopText
import com.sourav.contact_picker.entity.ListOfContact
import com.sourav.contact_picker.util.CONTACT_LIST_PARAM
import com.sourav.multicontactpicker.navGraph.NavigationItem

@Composable
fun HomeScreen(navigateTo: (String) -> Unit, navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedButton(
            onClick = {
                navigateTo(NavigationItem.ContactPickerScreen.route)
            },
            border = BorderStroke(1.dp, color = Color.Black),
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.width(200.dp).height(100.dp),
        ) {
            Text(
                text = "Go and pick contacts",
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                fontSize = 12.sp,
            )
        }

        val secondScreenResult = navController.currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<String>(CONTACT_LIST_PARAM)?.observeAsState()
        secondScreenResult?.value?.let {
            // Read the result
            Log.d("FATAL", "HomeScreen: FATAL $it")

            val obj = Gson()
                .fromJson<ListOfContact>(
                    it,
                    ListOfContact::class.java,
                )

            LazyColumn {
                items(obj.list.size) { index ->
                    obj.list[index].let { contact ->
                        ContactItem(
                            contact.name ?: "",
                            contact.phoneNo ?: "",
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ContactItem(name: String, phoneNo: String) {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth().padding(16.dp),
    ) {
        PopText(
            modifier = Modifier
                .padding(8.dp),
            text = name,
            fontColor = Color.Black,
            fontSize = 20.sp
        )
        Log.d("FATAL", "ContactItem: $name  ")
        Spacer(modifier = Modifier.width(20.dp))
        PopText(text = phoneNo)
    }
}
