package fairy.fairy_eye

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.google.mediapipe.tasks.vision.objectdetector.ObjectDetectorResult

class BaseAdapterImpl : BaseAdapter() {

    data class Result(val category: String, val score: Float)

    private var list: List<Result> = emptyList()

    fun updateList(detectedList: ObjectDetectorResult) {
        list = emptyList()
        detectedList.detections().forEach {
            list += Result(it.categories()[0].categoryName(), it.categories()[0].score())
        }
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        if (convertView == null) {
            return LayoutInflater.from(parent?.context)
                .inflate(R.layout.list_view_item_layout, parent, false)
        }

        val textView = convertView.findViewById<TextView>(R.id.titleView)
        val category = convertView.findViewById<TextView>(R.id.subTitleView)

        textView?.text = list[position].category
        category?.text = String.format("%.2f", list[position].score)

        return convertView
    }
}