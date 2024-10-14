package com.example.noteapplicationapp.feature_note.domain.use_case

import com.example.noteapplicationapp.feature_note.data.repository.FakeNoteRepository
import com.example.noteapplicationapp.feature_note.domain.model.Note
import com.example.noteapplicationapp.feature_note.domain.util.NoteOrder
import com.example.noteapplicationapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetNotesUseCaseTest {

    private lateinit var getNotesUseCase: GetNotesUseCase
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setup() {
        fakeNoteRepository = FakeNoteRepository()
        getNotesUseCase = GetNotesUseCase(fakeNoteRepository)

        val notesToInsert = mutableListOf<Note>()
        ('a'..'z').forEachIndexed { index, c ->
            notesToInsert.add(
                Note(
                    title = c.toString(),
                    content = c.toString(),
                    timestamp = index.toLong(),
                    color = index
                )
            )
        }
        notesToInsert.shuffle()
        runBlocking {
            notesToInsert.forEach { fakeNoteRepository.insertNote(it)}
        }
    }

    @Test
    fun Order_notes_by_title_ascending_correct_order(){

        runBlocking {
            val notes = getNotesUseCase(NoteOrder.Title(OrderType.Ascending)).first()

            for(i in 0..notes.size - 2){
                assert(notes.get(i).title < notes.get(i+1).title)
            }
        }

    }

    @Test
    fun Order_notes_by_title_descending_correct_order(){

        runBlocking {
            val notes = getNotesUseCase(NoteOrder.Title(OrderType.Descending)).first()

            for(i in 0..notes.size - 2){
                assert(notes.get(i).title > notes.get(i+1).title)
            }
        }

    }

    @Test
    fun Order_notes_by_date_ascending_correct_order(){

        runBlocking {
            val notes = getNotesUseCase(NoteOrder.Date(OrderType.Ascending)).first()

            for(i in 0..notes.size - 2){
                assert(notes.get(i).timestamp < notes.get(i+1).timestamp)
            }
        }

    }

    @Test
    fun Order_notes_by_date_descending_correct_order(){

        runBlocking {
            val notes = getNotesUseCase(NoteOrder.Date(OrderType.Descending)).first()

            for(i in 0..notes.size - 2){
                assert(notes.get(i).timestamp > notes.get(i+1).timestamp)
            }
        }

    }


    @Test
    fun Order_notes_by_color_ascending_correct_order(){

        runBlocking {
            val notes = getNotesUseCase(NoteOrder.Color(OrderType.Ascending)).first()

            for(i in 0..notes.size - 2){
                assert(notes.get(i).color < notes.get(i+1).color)
            }
        }

    }

    @Test
    fun Order_notes_by_color_descending_correct_order(){

        runBlocking {
            val notes = getNotesUseCase(NoteOrder.Color(OrderType.Descending)).first()

            for(i in 0..notes.size - 2){
                assert(notes.get(i).color > notes.get(i+1).color)
            }
        }

    }

}