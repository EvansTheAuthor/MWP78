package com.example.mwp78

import android.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object Connection {

    private const val URL = "jdbc:postgresql://10.0.2.2:5432/postgres"
    private const val USER = "postgres"
    private const val PASSWORD = "si@labOSN"
    private const val TAG = "DBConnection"

    fun connection (): Connection?{
        return try{
            Class.forName("org.postgresql.Driver")
            DriverManager.getConnection(URL,USER, PASSWORD).also {
            }
        } catch (e: SQLException){
            Log.e(TAG, "Connection Failed", e)
            null
        } catch (e: ClassNotFoundException) {
            Log.e(TAG, "PostgreSQL JDBC Driver not found.", e)
            null
        }
    }
}