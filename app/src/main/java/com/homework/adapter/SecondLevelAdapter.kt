package com.homework.adapter

import android.animation.ValueAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.homework.R
import com.homework.models.Level2Node
import kotlinx.android.synthetic.main.second_level_item_layout.view.*

class SecondLevelAdapter(val context: Context, val itemList: ArrayList<Level2Node>, val parentView: View): RecyclerView.Adapter<SecondLevelAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.second_level_item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val level2Node = itemList.get(position)

        holder.tvTitle.setText(level2Node.title)
        holder.tvPayment.setText(level2Node.content.payment)
        holder.tvTargetDate.setText(level2Node.content.targetDate)
        holder.tvStartDate.setText(level2Node.content.startDate)

        val layoutParams = holder.llContent.layoutParams
        if(!level2Node.isContentOpen){
            layoutParams.height = 0
            holder.llContent.layoutParams = layoutParams
        }

        holder.llArrow.setOnClickListener(View.OnClickListener {
            val layoutParams = holder.llContent.layoutParams
            if(layoutParams.height != 0){
                itemList.get(position).isContentOpen = false
                startAnimator(holder.llContent, convertDpToPx(100), 0)


                val parentLayoutParams = parentView.layoutParams
                startAnimator(parentView, parentLayoutParams.height, parentLayoutParams.height - convertDpToPx(100))

                holder.ivImage.setImageResource(R.mipmap.right_arrow)
            }else{
                itemList.get(position).isContentOpen = true
                startAnimator(holder.llContent, 0, convertDpToPx(100))


                val parentLayoutParams = parentView.layoutParams
                startAnimator(parentView, parentLayoutParams.height, parentLayoutParams.height + convertDpToPx(100))

                holder.ivImage.setImageResource(R.mipmap.top_arrow)
            }
        })
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.tv_level_2_title
        val llArrow = itemView.ll_arrow
        val ivImage = itemView.iv_level_2_image
        val llContent = itemView.ll_content
        val tvPayment = itemView.tv_payment
        val tvTargetDate = itemView.tv_target_date
        val tvStartDate = itemView.tv_start_date
    }

    public fun getContentOpenCount(): Int{
        var count = 0
        for(item in itemList){
            if(item.isContentOpen == true){
                count += 1
            }
        }
        return count
    }

    private fun startAnimator(view: View, from: Int, to: Int){
        val animator = ValueAnimator.ofInt(from, to)
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