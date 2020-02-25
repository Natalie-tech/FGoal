package com.homework.adapter

import android.animation.ValueAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.homework.R
import com.homework.models.Level1Node
import com.homework.models.Level2Node
import kotlinx.android.synthetic.main.first_level_item_layout.view.*

/**
 * Adapter
 */
class FirstLevelAdapter(val context: Context, val itemList : ArrayList<Level1Node>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val VIEW_TYPE_HEADER = 0
    val VIEW_TYPE_NORMAL_VIEW = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == VIEW_TYPE_HEADER){
            val itemView = LayoutInflater.from(context).inflate( R.layout.header_layout, parent, false)
            return HeaderViewHolder(itemView)
        }else{
            val itemView = LayoutInflater.from(context).inflate( R.layout.first_level_item_layout, parent, false)
            return ViewHolder(itemView)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if(position == 0){
            return VIEW_TYPE_HEADER
        }else{
            return VIEW_TYPE_NORMAL_VIEW
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewHolder){
            val node = itemList.get(position - 1);
            holder.tvTitle.setText(node.title)

            //level2adapter
            val level2Adapter = SecondLevelAdapter(context, node.childs as ArrayList<Level2Node>, holder.rvLevel2List)
            holder.rvLevel2List.layoutManager = LinearLayoutManager(context)
            holder.rvLevel2List.adapter = level2Adapter

            //height=0
            val layoutParams = holder.rvLevel2List.layoutParams
            layoutParams.height = 0
            holder.rvLevel2List.layoutParams = layoutParams

            holder.tvShowMore.setOnClickListener(View.OnClickListener {
                if("QUICK VIEW".equals(holder.tvShowMore.text.toString())){

                    val openContentCount = (holder.rvLevel2List.adapter as SecondLevelAdapter).getContentOpenCount()
                    val totalHeight = convertDpToPx(node.childs.size * 55 + openContentCount * 100)

                    closeAnimation(holder.rvLevel2List, totalHeight)
                    holder.tvShowMore.text = "LEARN MORE"
                }else{

                    val openContentCount = (holder.rvLevel2List.adapter as SecondLevelAdapter).getContentOpenCount()
                    val totalHeight = convertDpToPx(node.childs.size * 55 + openContentCount * 100)

                    openAnimation(holder.rvLevel2List, totalHeight)
                    holder.tvShowMore.text = "QUICK VIEW"
                }
            })
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvTitle = itemView.tv_level_1_title
        val tvShowMore = itemView.tv_level_1_show_more
        val rvLevel2List = itemView.level_2_recycler_view
    }

    class HeaderViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    }

    private fun openAnimation(view: View, height: Int){
        val animator = ValueAnimator.ofInt(0, height)
        animator.addUpdateListener(ValueAnimator.AnimatorUpdateListener {
            val value: Int = it.animatedValue as Int
            val layoutParams = view.layoutParams
            layoutParams.height = value
            view.layoutParams = layoutParams
        })
        animator.duration = 200
        animator.start()
    }

    private fun closeAnimation(view: View, height: Int){
        val animator = ValueAnimator.ofInt(height, 0)
        animator.addUpdateListener(ValueAnimator.AnimatorUpdateListener {
            val value: Int = it.animatedValue as Int
            val layoutParams = view.layoutParams
            layoutParams.height = value
            view.layoutParams = layoutParams
        })
        animator.duration = 200
        animator.start()
    }

    private fun convertDpToPx(dpValue: Int): Int{
        val scale = context.resources.displayMetrics.density;
        return (dpValue * scale + 0.5).toInt()
    }
}