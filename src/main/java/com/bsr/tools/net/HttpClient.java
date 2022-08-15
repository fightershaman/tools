package com.bsr.tools.net;

import org.apache.http.NoHttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.ssl.SSLContextBuilder;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

public class HttpClient {
    private static HttpClient instance;
    private CloseableHttpClient httpClient;
    private Integer maxConnPerRoute = 20;
    private Integer maxConnTotal = 200;
    private Integer socketTimeout = 60000;
    protected int readTimeout = 60000;
    protected int connectionTimeout = 5000;
    protected int maxTotalConnection = 5000;
    protected int retryCount = 0;
    protected String senderType;
    protected int validateAfterInactivityTime = 60000;
    private int maxPerRoute = 500;

    private HttpClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        init();
    }

    public static HttpClient getInstance() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        if (instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new HttpClient();
                }
            }
        }
        return instance;
    }

    private void init() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, (chain, authType) -> true);
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(), NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.INSTANCE).register("https", sslsf).build();
        ManagedHttpClientConnectionFactory connFactory = new ManagedHttpClientConnectionFactory(DefaultHttpRequestWriterFactory.INSTANCE, DefaultHttpResponseParserFactory.INSTANCE);
        DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(socketFactoryRegistry, connFactory, dnsResolver);
        SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();

        manager.setDefaultSocketConfig(socketConfig);
        manager.setMaxTotal(maxTotalConnection);
        manager.setDefaultMaxPerRoute(maxPerRoute);
        manager.setValidateAfterInactivity(validateAfterInactivityTime);
        RequestConfig defaulRequestConfig = RequestConfig.custom().setConnectTimeout(connectionTimeout).setSocketTimeout(readTimeout).setConnectionRequestTimeout(connectionTimeout).build();

        httpClient = HttpClients.custom().setConnectionManager(manager).setConnectionManagerShared(false).setMaxConnPerRoute(this.maxConnPerRoute).setMaxConnTotal(this.maxConnTotal).evictIdleConnections(60, TimeUnit.SECONDS).evictExpiredConnections().setConnectionTimeToLive(60, TimeUnit.SECONDS).setDefaultRequestConfig(defaulRequestConfig).setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE).setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE).setRetryHandler((exception, executionCount, context) -> {
            if (executionCount > 2) {
                return false;
            }
            if (exception instanceof NoHttpResponseException) {
                return true;
            }
            return false;
        }).build();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
