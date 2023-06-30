package com.example.weatherfetcher.base

import android.content.Context
import android.content.res.Configuration
import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.core.widget.TextViewCompat

inline fun <reified T> attempt(func: () -> T): Either<Throwable, T> = try {
    Either.Right(func.invoke())
} catch (e: Throwable) {
    Either.Left(e)
}

//поменять шрифт программно
fun TextView.setTextAppearanceCompat(@StyleRes styleRes: Int) {
    TextViewCompat.setTextAppearance(this, styleRes)
}

//расширяющая функция-проверка, включен ли темный режим
fun Context.isDarkModeEnabled(): Boolean {
    return resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
}