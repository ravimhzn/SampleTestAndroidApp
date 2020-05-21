package com.ravimhzn.sampletestandroidapplication.utils

class Constants {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
        const val PREFIX_PHOTOS = "/photos"

        const val NETWORK_TIMEOUT = 6000L //Will timeout our network if it takes more than 6 seconds
        const val TESTING_NETWORK_DELAY = 0L // fake network delay for testing
        const val TESTING_CACHE_DELAY = 0L // fake cache delay for testing

        const val PAGINATION_PAGE_SIZE = 10
    }
}