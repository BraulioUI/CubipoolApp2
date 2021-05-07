package pe.edu.upc.myapplication.viewmodel.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class UserViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserViewModelFactory::class.java)){
            return UserViewModel () as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}