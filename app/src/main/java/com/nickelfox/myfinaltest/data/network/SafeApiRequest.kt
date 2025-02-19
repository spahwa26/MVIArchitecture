package com.nickelfox.myfinaltest.data.network

import com.nickelfox.myfinaltest.utils.ApiException
import com.nickelfox.myfinaltest.data.models.CustomResult
import com.nickelfox.myfinaltest.utils.UnAuthorizedException
import com.nickelfox.myfinaltest.utils.localizedException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): CustomResult<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful)
                CustomResult.Success(response.body()!!)
            else
                CustomResult.Error(response.getApiException().localizedException)
        } catch (e: Exception) {
            CustomResult.Error(e.localizedException)
        }
    }

    //TODO: delete is not in use
    @Throws(Exception::class)
    suspend fun <T : Any> apiRequestWithException(call: suspend () -> Response<T>): T {
        return try {
            val response = call.invoke()
            if (response.isSuccessful)
                response.body()!!
            else
                throw response.getApiException()
        } catch (e: Exception) {
            throw e.localizedException
        }
    }

    @Throws(Exception::class)
    fun <T : Any> Response<T>.getApiException(): Exception {
        val error = errorBody()?.string()
        val errorObj = JSONObject(error ?: "{}")
        if (errorObj.getInt("code") == 401) {
            return UnAuthorizedException()
        }
        return ApiException(errorObj.getJSONObject("error").getJSONArray("message").getString(0))
    }
}
