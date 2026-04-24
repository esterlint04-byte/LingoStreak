package com.lingostreak.util.di
import android.content.Context
import androidx.room.Room
import com.lingostreak.data.local.AppDatabase
import com.lingostreak.data.local.dao.*
import com.lingostreak.data.repository.UsuarioRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides @Singleton fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "lingostreak_db").fallbackToDestructiveMigration().build()
    @Provides fun provideUsuarioDao(db: AppDatabase): UsuarioDao = db.usuarioDao()
    @Provides fun provideTemaDao(db: AppDatabase): TemaDao = db.temaDao()
    @Provides fun providePreguntaDao(db: AppDatabase): PreguntaDao = db.preguntaDao()
    @Provides @Singleton fun provideUsuarioRepository(usuarioDao: UsuarioDao, temaDao: TemaDao, preguntaDao: PreguntaDao, @ApplicationContext context: Context): UsuarioRepository = UsuarioRepository(usuarioDao, temaDao, preguntaDao, context)
}
