package com.digua.base.net

import android.util.Log

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class HttpHelp private constructor() {

    private var loggingInterceptor: LoggingInterceptor? = null
    private val DEFAULT_TIMEOUT: Long = 30

    companion object {
        @Volatile
        var instance: HttpHelp? = null
            get() {
                if (field == null) {
                    synchronized(HttpHelp::class.java) {
                        if (field == null) {
                            field = HttpHelp()
                        }
                    }
                }
                return field
            }
            private set
    }

    fun getServiceRetrofit(host: String, map: HashMap<String, String>?): Retrofit {
        if (loggingInterceptor == null) {
            loggingInterceptor = LoggingInterceptor(object : LoggingInterceptor.Logger {
                override fun log(message: String?) {
                    Log.e("LoggingInterceptor", message!!)
                }
            })
            if (true) {
                loggingInterceptor?.setLevel(LoggingInterceptor.Level.BODY)
            } else {
                loggingInterceptor?.setLevel(LoggingInterceptor.Level.NONE)
            }
        }

        //增加头部信息(根据BaseUrl,添加不同头部)
        val headerInterceptor = Interceptor { chain: Interceptor.Chain ->
            var request = chain.request()
            val newBuilder = request.newBuilder()

            if (map == null) {
                newBuilder.addHeader(
                    "Authorization",
                    "Bearer "
                )
            } else {
                map.forEach {
                    newBuilder.addHeader(it.key, it.value)
                }
            }
            newBuilder.addHeader("Charset", "UTF-8")
            newBuilder.addHeader("Content-Type", "application/json")
            val builder = request.url().newBuilder();

            request = newBuilder
                .method(request.method(), request.body())
                .url(builder.build())
                .build()

            chain.proceed(request)
        }

        val okHttpClient = OkHttpClient().newBuilder()
            .retryOnConnectionFailure(true)
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS) //设置超时时间
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS) //设置读取超时时间
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS) //设置写入超时时间
            .sslSocketFactory(getSSLSocketFactory(), MyX509TrustManager())
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor)
            .hostnameVerifier { s, sslSession -> true }
            .build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(host)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun getSSLSocketFactory(): SSLSocketFactory? {
        try {
            val trustAllCerts =
                arrayOf<TrustManager>(
                    object : X509TrustManager {
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    }
                )
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            return sslContext.socketFactory
        } catch (ex: java.lang.Exception) {
        }
        return null
    }
}