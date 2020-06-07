package com.ravimhzn.sampletestandroidapplication.utils

import androidx.lifecycle.MutableLiveData
import kotlinx.android.parcel.IgnoredOnParcel
import java.lang.IndexOutOfBoundsException

const val ERROR_STACK_BUNDLE_KEY = "com.ravimhzn.sampletestandroidapplication.utils.ErrorStack"

/**
 * If we have multiple job running inside a coroutine thread (Multiple api calls)
 * and we get errors from multiple jobs then this class will get the error from the stack of errors in the form of LiveData
 * It will always get the error in [0]th position.
 * It's a overkill for this particular project but could be used as a base for other projects that needs to handle multiple jobs at once.
 */

class ErrorStack: ArrayList<ErrorState>() {

    @IgnoredOnParcel
    val errorState: MutableLiveData<ErrorState> = MutableLiveData()

    override fun addAll(elements: Collection<ErrorState>): Boolean {
        for(element in elements){
            add(element)
        }
        return true // always return true. We don't care about result bool.
    }

    override fun add(element: ErrorState): Boolean {
        if(this.size == 0){
            setErrorState(errorState = element)
        }
        if(this.contains(element)){ // prevent duplicate errors added to stack
            return false
        }
        return super.add(element)
    }

    override fun removeAt(index: Int): ErrorState {
        try{
            val transaction = super.removeAt(index)
            if(this.size > 0){
                setErrorState(errorState = this[0])
            }
            else{
                setErrorState(null)
            }
            return transaction
        }catch (e: IndexOutOfBoundsException){
            e.printStackTrace()
        }
        return ErrorState(
            "does nothing"
        ) // this does nothing
    }

    private fun setErrorState(errorState: ErrorState?){
        this.errorState.value = errorState
    }
}