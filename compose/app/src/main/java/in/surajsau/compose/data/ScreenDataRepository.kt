package `in`.surajsau.compose.data

import `in`.surajsau.compose.App
import com.google.gson.Gson
import men.ngopi.zain.jsonloaderlibrary.JSONArrayLoaderListener
import men.ngopi.zain.jsonloaderlibrary.JSONLoader
import org.json.JSONArray
import java.lang.Exception

class ScreenDataRepository {

    fun watchScreens(
        onSuccess: (List<ScreenData>) -> Unit
    ) {
        JSONLoader.with(App.instance)
            .fileName("data.json")
            .getAsJSONArray(object: JSONArrayLoaderListener {
                override fun onResponse(response: JSONArray?) {
                    val size = response?.length() ?: return
                    val result = mutableListOf<ScreenData>()

                    for(i in 0 until size) {
                        val item = response.getJSONObject(i)
                        result.add(Gson().fromJson(item.toString(), ScreenData::class.java))
                    }

                    onSuccess(result)
                }

                override fun onFailure(error: Exception?) {
                    TODO("Not yet implemented")
                }
            })
    }
}