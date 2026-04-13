package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.myapplication.navigation.AuthNavGraph

/**
 * Главная Activity приложения.
 *
 * Отвечает за:
 * - инициализацию UI;
 * - подключение Compose;
 * - запуск навигационного графа.
 *
 * Это точка входа в приложение (launcher activity).
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Включает edge-to-edge режим:
         * - контент может рисоваться под статус-баром и навбаром;
         * - позволяет делать современный UI "во весь экран".
         */
        enableEdgeToEdge()

        /**
         * Устанавливает Compose UI как содержимое Activity.
         */
        setContent {

            /**
             * MaterialTheme — глобальная тема приложения.
             *
             * Здесь задаются:
             * - цвета (colorScheme)
             * - типографика
             * - формы
             */
            MaterialTheme {

                /**
                 * Surface — базовый контейнер Material3.
                 *
                 * Используется для:
                 * - задания фона;
                 * - применения темы;
                 * - корректной работы elevation и ripple.
                 */
                Surface(
                    modifier = Modifier.fillMaxSize(),

                    // Фоновый цвет из темы приложения
                    color = MaterialTheme.colorScheme.background
                ) {

                    /**
                     * Запуск навигационного графа авторизации.
                     *
                     * Внутри:
                     * - Splash
                     * - Welcome
                     * - Login
                     * - Register
                     */
                    AuthNavGraph()
                }
            }
        }
    }
}