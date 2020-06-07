package com.ravimhzn.sampletestandroidapplication.di


import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap


class DependencyContainer {


//    lateinit var noteNetworkDataSource: NoteNetworkDataSource
//    lateinit var noteCacheDataSource: NoteCacheDataSource
//    lateinit var noteFactory: NoteFactory
//    lateinit var noteDataFactory: NoteDataFactory
//    private var notesData: HashMap<String, Note> = HashMap() // To avoid null-pointer
//
//    init {
//        isUnitTest = true // for Logger.kt
//    }
//
//    fun build() {
//        this.javaClass.classLoader?.let { classLoader ->
//            noteDataFactory = NoteDataFactory(classLoader)
//
//            //fake data set
//            notesData = noteDataFactory.produceHashMapOfNotes(
//                noteDataFactory.produceListOfNotes()
//            )
//        }
//
//        noteFactory =
//            NoteFactory(
//                dateUtil
//            )
//        noteNetworkDataSource = FakeNoteNetworkDataSourceImpl(
//            notesData = notesData,
//            deletedNotesData = HashMap()
//        )
//        noteCacheDataSource = FakeNoteCacheDataSourceImpl(
//            notesData = notesData,
//            dateUtil = dateUtil
//        )
//    }

}