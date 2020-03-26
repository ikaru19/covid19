package com.ikaru.covid19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    private val mNotificationTime = Calendar.getInstance().timeInMillis + 5000 //Set after 5 seconds from the current time.
    private var mNotified = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!mNotified) {
            NotificationUtils().setNotification(mNotificationTime, this@MainActivity)
        }

        // get post data
        val postServices = DataRepository.create()
        postServices.getPosts().enqueue(object : Callback<CaseModel> {

            override fun onResponse(call: Call<CaseModel>, response: Response<CaseModel>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    Log.e("ASW",data.toString())

                    if (data != null) {
                        Text_jmlKonfirmasi.setText(""+data.confirmed)
                        text_jmlPerwatan.setText(""+data.activeCare)
                        text_jmlMeninggal.setText(""+data.deceased)
                        text_jmlSembuh.setText(""+data.recovered)

                    }
                }
            }

            override fun onFailure(call: Call<CaseModel>, error: Throwable) {
                Log.e("ASW", "errornya ${error.message}")
            }
        })
    }
}
