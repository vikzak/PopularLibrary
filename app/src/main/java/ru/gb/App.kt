package ru.gb

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ru.gb.popularlibrary.di.ApplicationComponent
import ru.gb.popularlibrary.di.DaggerApplicationComponent


class App : Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.create()

//        startKoin {
//            androidLogger()
//            androidContext(this@App)
//            modules(appModule)
//        }
    }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext() as App