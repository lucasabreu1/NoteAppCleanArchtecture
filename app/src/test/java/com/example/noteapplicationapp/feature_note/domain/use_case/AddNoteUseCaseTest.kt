package com.example.noteapplicationapp.feature_note.domain.use_case

import com.example.noteapplicationapp.feature_note.data.repository.FakeNoteRepository
import com.example.noteapplicationapp.feature_note.domain.model.InvalidNoteException
import com.example.noteapplicationapp.feature_note.domain.model.Note
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddNoteUseCaseTest {

    private lateinit var getNoteUseCase: GetNoteUseCase
    private lateinit var addNoteUseCase: AddNoteUseCase
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setup() {
        fakeNoteRepository = FakeNoteRepository()
        getNoteUseCase = GetNoteUseCase(fakeNoteRepository)
        addNoteUseCase = AddNoteUseCase(fakeNoteRepository)

    }

    @Test
    fun Add_note_title_content_both_not_empty(){

        val note = Note(
            title = "test",
            content = "test",
            timestamp = 1,
            color = 1,
            id = 1
        )

        runBlocking {

            var noteInserted: Boolean

            try {
                addNoteUseCase(note)
                noteInserted = true

            } catch (e: InvalidNoteException){
                noteInserted = false
            }

            assert(noteInserted)
        }
    }

    @Test
    fun Add_note_title_empty(){

        val note = Note(
            title = "",
            content = "test",
            timestamp = 1,
            color = 1,
            id = 1
        )

        runBlocking {

            var noteInserted: Boolean

            try {
                addNoteUseCase(note)
                noteInserted = true

            } catch (e: InvalidNoteException){
                noteInserted = false
            }

            assert(!noteInserted)
        }

    }
}