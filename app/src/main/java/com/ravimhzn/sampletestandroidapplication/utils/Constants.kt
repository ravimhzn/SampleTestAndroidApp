package com.ravimhzn.sampletestandroidapplication.utils

class Constants {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
        const val PREFIX_USERS = "/users"
        const val PREFIX_PHOTOS = "/photos"

        const val TESTING_CACHE_DELAY = 0L // fake cache delay for testing
        const val PAGINATION_PAGE_SIZE = 10
        const val UNKNOWN_ERROR = "Unknown error"

        const val TAG = "AppDebug" // Tag for logs
        const val DEBUG = true // enable logging

        //Network Related..
        const val NETWORK_ERROR = "Network error"
        const val NETWORK_ERROR_TIMEOUT = "Network timeout"
        const val NETWORK_TIMEOUT = 6000L //Will timeout our network if it takes more than 6 seconds
        const val TESTING_NETWORK_DELAY = 0L // fake network delay for testing
        const val NETWORK_DELAY = 1000L // ms (Fake network delay to make app more realistic)
    }
}