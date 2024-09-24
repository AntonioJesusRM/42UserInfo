package com.example.a42userinfo.data.repository.remote.backend

import android.util.Log
import com.example.a42userinfo.data.repository.remote.response.BaseResponse
import com.example.a42userinfo.data.repository.remote.response.ErrorResponse
import com.example.a42userinfo.domain.model.ErrorModel
import com.example.a42userinfo.ui.extensions.TAG
import com.google.gson.Gson
import retrofit2.Response

abstract class BaseService {
    suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): BaseResponse<T> {
        val response: Response<T>
        try {
            response = call.invoke()

            return if (!response.isSuccessful) {
                val errorResponse = mapErrorResponse(response)
                Log.e(TAG, "%> errorResponse: ${errorResponse.message}")
                BaseResponse.Error(errorResponse)
            } else {
                response.body()?.let { body ->
                    BaseResponse.Success(body)
                } ?: BaseResponse.Error(mapErrorResponse(response))
            }
        } catch (throwable: Throwable) {
            Log.e(TAG, "%> throwable: ${throwable.message}")
            throwable.printStackTrace()
            return BaseResponse.Error(mapErrorResponse(throwable))
        }
    }

    private fun <T> mapErrorResponse(response: Response<T>): ErrorModel {
        val errorBody = response.errorBody()?.string()
        val errorData = try {
            val parsedData = Gson().fromJson(errorBody, ErrorResponse::class.java)
            if (response.code() == 401) {
                parsedData.errorCode = 401.toString()
            }
            parsedData
        } catch (exception: java.lang.Exception) {
            Log.e(TAG, "l> exception: ${exception.message}")
            exception.printStackTrace()
            null
        }

        return ErrorModel(
            errorData?.error ?: "", errorData?.errorCode ?: "0", errorData?.message ?: ""
        )
    }

    private fun mapErrorResponse(throwable: Throwable): ErrorModel {
        return (ErrorModel(
            "Lo sentimos, estamos presentando problemas de conexión.",
            "0",
            throwable.message ?: "Vuelve a intentarlo más tarde."
        ))
    }
}