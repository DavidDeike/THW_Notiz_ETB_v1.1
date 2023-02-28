




import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thwnotizetb.data.model.notepad.Note


class NoteListViewModel : ViewModel() {
    private val noteList = MutableLiveData<List<Note>>()

    val observableNoteList: LiveData<List<Note>>
        get() = noteList

    init {
        load()
    }

    fun load() {
        noteList.value = NotesManager.getNoteList()
    }
}