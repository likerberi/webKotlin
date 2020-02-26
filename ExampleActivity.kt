package com.example.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Button
import android.os.AsyncTask.execute
import android.R
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import android.widget.Toast
import android.os.AsyncTask
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.sql.DriverManager


class ExampleActivity : AppCompatActivity() {

    private val url = "SAMPLE_URL"
    private val username = "username"
    private val password = "password"
    var fetchBtn :Button? = null
    var clearBtn :Button? = null
    var txtData: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)

        txtData = this.findViewById(R.id.txtData) as TextView
        fetchBtn = findViewById(R.id.fetchBtn) as Button
        clearBtn = findViewById(R.id.clearBtn) as Button
        fetchBtn!!.setOnClickListener(object : View.OnClickListener() {

            override fun onClick(v: View) {
                // TODO Auto-generated method stub
                val connectMySql = ConnectMySql()
                connectMySql.execute("")
            }
        })
        clearBtn!!.setOnClickListener(object : View.OnClickListener() {
            override fun onClick(view: View) {

            }
        })
    }

    private inner class ConnectMySql : AsyncTask<String, Void, String>() {
        internal var res = ""

        override fun onPreExecute() {
            super.onPreExecute()
            Toast.makeText(
                this@MainActivity,
                "Please wait...",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun doInBackground(vararg params: String): String {
            try {
                Class.forName("SAMPLE_URL")
                val con = DriverManager.getConnection(url, username, password)
                println("Database conn")

                var result = "Database is connected\n"
                val st = con.createStatement()
                val rs = st.executeQuery("select distinct Country from tblCountries")
                val rsmd = rs.getMetaData()

                while (rs.next()) {
                    result += rs.getString(1).toString() + "\n"
                }
                res = result
            } catch (e: Exception) {
                e.printStackTrace()
                res = e.toString()
            }

            return res
        }

        override fun onPostExecute(result: String) {
            //txtData.setText(result)
        }
    }

}
