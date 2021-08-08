package com.temper.myapplication.repositories

import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.tapadoo.alerter.Alerter
import com.temper.myapplication.R
import com.temper.myapplication.Shifts
import com.temper.myapplication.exceptions.NoConnectivityException
import com.temper.myapplication.managers.AlertManager
import com.temper.myapplication.services.response.Output
import retrofit2.Response
import java.io.IOException

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, error: String, view : ConstraintLayout?): T? {
        try {
            val result = apiOutput(call, error)
            var output: T? = null
            when (result) {
                is Output.Success ->{
                    output = result.output
                }

                is Output.Error -> {
                    val currentActivity = Shifts.applicationContext().getCurrentActivity()
                    Thread(Runnable {
                        currentActivity?.runOnUiThread {
                            if (view != null) {
                                view.visibility = View.GONE
                            }
                            if (Alerter.isShowing) {
                                Alerter.hide()
                            }

                            if (result.exception.message.toString() != "") {
                                AlertManager.showErrorAlert(
                                    currentActivity,
                                    result.exception.message.toString()
                                ).show()
                            }
                        }
                    }).start()

                }
            }
            return output
        } catch (noInternet: NoConnectivityException) {
            val currentActivity = Shifts.applicationContext().getCurrentActivity()
            if (currentActivity != null) {
                Thread(Runnable {
                    currentActivity.runOnUiThread(java.lang.Runnable {

                        if (Alerter.isShowing) {
                            Alerter.hide()
                        }

                        AlertManager.showWarningAlert(
                            currentActivity,
                            currentActivity.resources.getString(R.string.NO_INTERNET_CONNECTION)
                        ).show()
                    })
                }).start()
            }
            noInternet.message?.let { Log.e("Error", it) }
            return null
        } catch (e: Exception) {
            e.message?.let { Log.e("Error", it) }
            val currentActivity = Shifts.applicationContext().getCurrentActivity()
            Thread(Runnable {
                currentActivity?.runOnUiThread(java.lang.Runnable {
                    if (view != null) {
                        view.visibility = View.GONE
                    }


                    if (Alerter.isShowing) {
                        Alerter.hide()
                    }

                    AlertManager.showErrorAlert(
                        currentActivity,
                        error
                    ).show()
                })
            }).start()

            return null
        }
    }

    private suspend fun <T : Any> apiOutput(
        call: suspend () -> Response<T>,
        error: String
    ): Output<T> {
        val response = call.invoke()
        Log.i("response", response.toString())

        return if (response.isSuccessful) {
            Output.Success(response.body()!!)
        } else  {
            if(response.errorBody() != null) {
                Output.Error(IOException(error))
            } else {
                Output.Error(IOException(error))
            }
        }

    }

}