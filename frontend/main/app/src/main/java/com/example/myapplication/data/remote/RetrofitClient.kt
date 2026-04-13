package com.example.myapplication.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Объект (Singleton) для создания и хранения экземпляра Retrofit.
 *
 * Используется как единая точка доступа к API во всём приложении.
 * Гарантирует, что Retrofit и OkHttpClient создаются только один раз.
 */
object RetrofitClient {

    /**
     * Базовый URL сервера.
     *
     * Важно:
     * - должен заканчиваться на "/"
     * - все endpoint'ы из ApiService будут добавляться к этому URL
     *
     * Пример:
     * BASE_URL = https://api.site.com/
     * @POST("login") → https://api.site.com/login
     */
    private const val BASE_URL = "https://api.zxc1000-7.online/"

    /**
     * Логирующий interceptor для OkHttp.
     *
     * Позволяет видеть:
     * - URL запросов
     * - body запросов
     * - ответы сервера
     *
     * Level.BODY — самый подробный уровень (удобно для разработки).
     * ⚠️ В продакшене лучше переключать на NONE или BASIC.
     */
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    /**
     * HTTP-клиент, используемый Retrofit.
     *
     * Здесь можно:
     * - добавлять interceptors (логирование, токены, headers)
     * - настраивать таймауты
     */
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    /**
     * Экземпляр API (ApiService).
     *
     * lazy означает:
     * - создаётся только при первом обращении
     * - затем переиспользуется (Singleton внутри объекта)
     */
    val api: ApiService by lazy {
        Retrofit.Builder()
            // Устанавливаем базовый URL
            .baseUrl(BASE_URL)

            // Подключаем кастомный OkHttpClient
            .client(client)

            // Конвертер JSON → Kotlin (Gson)
            .addConverterFactory(GsonConverterFactory.create())

            // Создаём реализацию интерфейса ApiService
            .build()
            .create(ApiService::class.java)
    }
}