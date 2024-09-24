package com.example.a42userinfo.data.repository.remote.backend

import android.util.Log
import com.example.a42userinfo.data.constants.GeneralConstants.Companion.BASE_URL
import com.example.a42userinfo.data.constants.GeneralConstants.Companion.RETROFIT_TIMEOUT_IN_SECOND
import com.example.a42userinfo.data.repository.preferences.PreferencesDataSource
import com.example.a42userinfo.ui.extensions.TAG
import com.google.gson.GsonBuilder
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier

@Singleton
class RetrofitClient @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource
) {
    companion object {
        const val HEADER_KEY_TOKEN = "Authorization"
        const val HEADER_VALUE_TOKEN_START = "Bearer "
        const val URL = "api.intra.42.fr"
        private const val CERTIFICATE_1 = "sha256/2Y978dibgREZMyX2Nd039BgRao9y1yvEZaZo4En0mTs="
        private const val CERTIFICATE_2 = "sha256/kIdp6NNEd8wsugYyyIYFsi1ylMCED3hZbSR8ZFsa/A4="
        private const val CERTIFICATE_3 = "sha256/mEflZT5enoR1FuXLgYYGqnVEoZvmf9c2bVBpiOjYQ0c="
    }

    val retrofit: Retrofit

    init {
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()

        val certificatePinner =
            CertificatePinner.Builder().add(URL, CERTIFICATE_1).add(URL, CERTIFICATE_2)
                .add(URL, CERTIFICATE_3).build()
        httpClient.certificatePinner(certificatePinner)

        val hostnamesAllow = listOf(
            URL,
        )
        val hostnameVerifier = HostnameVerifier { hostname, _ ->
            hostname in hostnamesAllow
        }
        httpClient.hostnameVerifier(hostnameVerifier)

        httpClient.connectTimeout(RETROFIT_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
            .readTimeout(RETROFIT_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
            .writeTimeout(RETROFIT_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)

        httpClient.interceptors().clear()
        httpClient.interceptors().add(Interceptor { chain ->
            val original = chain.request()

            val request = when {
                needAddBearer(chain.request()) -> {
                    original.newBuilder().header(
                        HEADER_KEY_TOKEN,
                        HEADER_VALUE_TOKEN_START + preferencesDataSource.getAuthToken()
                    ).method(original.method, original.body).build()
                }

                else -> {
                    original.newBuilder().method(original.method, original.body).build()
                }
            }

            chain.proceed(request)
        })

        httpClient.addInterceptor(LoggingInterceptor())
        val gson = GsonBuilder().setLenient().create()

        retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)).client(httpClient.build())
            .callbackExecutor(Executors.newSingleThreadExecutor()).build()
    }

    private fun needAddBearer(request: Request): Boolean {
        val buffer = okio.Buffer()
        request.body?.writeTo(buffer)
        val requestUrl = request.url.toString()

        return when {
            requestUrl.endsWith("oauth/token", true) -> {
                Log.d(TAG, "%> No needAddBearer endsWith(oauth/v2/token")
                false
            }

            requestUrl.endsWith("oauth/v2/token/revoke", true) -> {
                Log.d(TAG, "%> No needAddBearer endsWith(oauth/v2/token/revoke")
                false
            }

            else -> {
                Log.d(TAG, "%> No needAddBearer contemplated")
                true
            }
        }
    }

    class LoggingInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val response = chain.proceed(originalRequest)

            val responseBodyString = response.body?.string() ?: ""

            Log.d(TAG, "%> Request: ${originalRequest.url}, ${originalRequest.headers}")
            Log.d(TAG, "%> Response: ${response.code}, $responseBodyString")

            val newResponseBody = responseBodyString.toResponseBody(response.body?.contentType())

            return response.newBuilder().body(newResponseBody).build()
        }
    }

}

