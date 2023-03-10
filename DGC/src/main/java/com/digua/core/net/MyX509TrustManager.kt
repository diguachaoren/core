package com.digua.base.net

import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

class MyX509TrustManager : X509TrustManager {

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

    override fun getAcceptedIssuers(): Array<X509Certificate?> {
        return arrayOfNulls(0)
    }
}