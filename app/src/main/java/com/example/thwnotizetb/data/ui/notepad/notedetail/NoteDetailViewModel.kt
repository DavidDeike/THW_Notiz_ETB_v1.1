import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thwnotizetb.data.model.notepad.Note


class NoteDetailViewModel : ViewModel() {
    private val note = MutableLiveData<Note>()

    val observableNote: LiveData<Note>
        get() = note

    fun getNote(id: Int) {
        note.value = NotesManager.getNote(id)
    }
}