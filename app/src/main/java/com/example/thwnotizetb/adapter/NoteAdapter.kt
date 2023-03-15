

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thwnotizetb.R
import com.example.thwnotizetb.data.model.notepad.Note


class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ItemViewHolder>() {

    private var dataset = listOf<Note>()

    // der ViewHolder weiß welche Teile des Layouts beim Recycling angepasst werden
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val pen : ImageView = view.findViewById(R.id.pen_iv)
        val thwLogo1 : ImageView = view.findViewById(R.id.blogo_iv)
        val id : TextView = view.findViewById(R.id.noteId_tv)
        val text : TextView = view.findViewById(R.id.notetext_tv)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(noteList: List<Note>) {
        noteList.also { dataset = it }
        notifyDataSetChanged()
    }

    // hier werden neue ViewHolder erstellt
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // das itemLayout wird gebaut
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false)

        // und in einem ViewHolder zurückgegeben
        return ItemViewHolder(adapterLayout)
    }

    // hier findet der Recyclingprozess statt
    // die vom ViewHolder bereitgestellten Parameter werden verändert
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val noteList = dataset[position]

       /*holder.image.load(rec.image) {
            transformations(CircleCropTransformation())
        }*/

        holder.id.text = dataset[position].id.toString()
        holder.text.text = dataset[position].text



    }

    // damit der LayoutManager weiß wie lang die Liste ist
    override fun getItemCount(): Int {
        return dataset.size
    }
}