package com.bagmanovam.news.core.database.di

import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bagmanovam.news.core.database.NewsDatabase
import org.koin.dsl.module

val databaseModule = module {

    single<NewsDatabase> {
        Room.databaseBuilder(
            context = get(),
            klass = NewsDatabase::class.java,
            name = "newsDatabase"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Log.i("Room", "onCreate: ")
                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    Log.i("Room", "onOpen: ${db.path}")
                }

                override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                    super.onDestructiveMigration(db)
                    Log.i("Room", "onDestructiveMigration: ${db.version}")
                }
            })
            .build()
    }

    single {
        get<NewsDatabase>().dao()
    }
}
