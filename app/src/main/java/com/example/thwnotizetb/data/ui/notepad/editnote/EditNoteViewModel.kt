



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thwnotizetb.data.model.notepad.Note


class EditNoteViewModel : ViewModel() {
    private val currentNote = MutableLiveData<Note>()

    private val editStatus = MutableLiveData<Boolean>()

    val observableCurrentNote: LiveData<Note>
        get() = currentNote

    val observableEditStatus: LiveData<Boolean>
        get() = editStatus

    fun editNote(id: Int, noteText: String) {
        editStatus.value = try {
            NotesManager.editNote(id, noteText)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    fun initNote(id: Int) {
        currentNote.value = NotesManager.getNote(id)
    }
}