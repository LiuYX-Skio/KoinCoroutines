package com.skio.coroutines.base

import com.skio.coroutines.network.RetrofitManager
import com.skio.coroutines.fragment.main.model.MineRepository
import com.skio.coroutines.fragment.main.model.MineViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.*

/**
 * @author LiuYX
 * @description
 */
val dataSourceModule = module {
    single { RetrofitManager.apiService }

//    single { AppDatabase.buildDatabase(androidContext()) }

    single { Calendar.getInstance() }
}

val viewModelModule = module {
  viewModel { MineViewModel(get()) }

}

val repositoryModule = module {
  single { MineRepository(get()) }

}

val fragmentModule = module {

}

val adapterModule = module {
}

val dialogModule = module {
}
