package fairy.fairy_eye

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class BaseAdapterImpl(
    private var arrayList: ArrayList<String>
) : BaseAdapter() {

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return arrayList[position]
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
        textView?.text = arrayList[position]

        return convertView
    }
}