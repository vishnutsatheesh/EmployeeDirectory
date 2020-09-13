package com.test.empdirect.rest
import android.util.Log
import com.test.empdirect.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


object ApiClient {

    private var retrofit: Retrofit? = null

    val client: Retrofit
        get() {

            if (retrofit == null) {

                retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(provideOkHttpClient())
                    .build()
            }

            return this.retrofit!!
        }




    private fun provideOkHttpClient(): OkHttpClient {

        val okhttpClientBuilder = OkHttpClient.Builder()
        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        okhttpClientBuilder.readTimeout(2, TimeUnit.MINUTES)
        okhttpClientBuilder.writeTimeout(2, TimeUnit.MINUTES)
        okhttpClientBuilder.addInterceptor(object : CustomNetworkConnectionInterceptor() {
            override fun isInternetAvailable(): Boolean {
                Log.e("ApiClient", "isInternetAvailable")
                // return this.isInternetAvailable();
                return false
            }

            override fun onInternetUnavailable() {
                Log.e("ApiClient", "onInternetUnavailable")

            }

            override fun onCacheUnavailable() {
                Log.e("ApiClient", "onCacheUnavailable")

            }

            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {

                return chain.proceed(chain.request())

            }

        })

        // add logging as last interceptor
      //  okhttpClientBuilder.addInterceptor(logging)  // <-- this is the important line!

        return okhttpClientBuilder.build()
    }


}
