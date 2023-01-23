package com.abdallah_abdelazim.asteroidradar.data.di

import android.content.Context
import androidx.room.Room
import com.abdallah_abdelazim.asteroidradar.data.Constants
import com.abdallah_abdelazim.asteroidradar.data.local.db.AppDatabase
import com.abdallah_abdelazim.asteroidradar.data.remote.api.NasaApi
import com.abdallah_abdelazim.asteroidradar.data.repository.nasa.*
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {

    /* Local */

    single { provideRoomDatabase(androidContext()) }

    single { get<AppDatabase>().asteroidDao() }

    single { get<AppDatabase>().pictureOfDayDao() }


    /* Remote */

    single { provideOkHttpClient(androidContext()) }

    single { provideRetrofit(get()) }

    single { get<Retrofit>().create(NasaApi::class.java) }

    /* Repositories */

    factoryOf(::NasaLocalDataSourceImpl) { bind<NasaLocalDataSource>() }

    factoryOf(::NasaRemoteDataSourceImpl) { bind<NasaRemoteDataSource>() }

    factoryOf(::NasaRepositoryImpl) { bind<NasaRepository>() }

}

private fun provideRoomDatabase(appContext: Context) = Room.databaseBuilder(
    appContext,
    AppDatabase::class.java,
    AppDatabase.DB_NAME
).build()

private fun provideOkHttpClient(appContext: Context): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(Level.BASIC)
    @Suppress("DEPRECATION")
    return OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .addInterceptor(ChuckerInterceptor(appContext))
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create())
    .build();