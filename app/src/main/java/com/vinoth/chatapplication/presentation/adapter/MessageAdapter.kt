package com.vinoth.chatapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vinoth.chatapplication.R
import com.vinoth.chatapplication.data.model.ChatBotModel
import java.text.SimpleDateFormat
import java.util.*

class MessageAdapter(private val messages: List<ChatBotModel> = mutableListOf()) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val layoutId = if (viewType == VIEW_TYPE_SENT) R.layout.sendingmessage else R.layout.receivingmessage
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]

        holder.messageTextView.text = message.text

        val messageTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(message.timestamp))
        holder.timeTextView.text = messageTime

        if (shouldShowDate(position)) {
            holder.dateTextView.visibility = View.VISIBLE
            holder.dateTextView.text = formatMessageDate(message.timestamp)
        } else {
            holder.dateTextView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = messages.size

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].isSent) VIEW_TYPE_SENT else VIEW_TYPE_RECEIVED
    }

    private fun shouldShowDate(position: Int): Boolean {
        if (position == 0) return true
        val currentMessageDate = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date(messages[position].timestamp))
        val previousMessageDate = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date(messages[position - 1].timestamp))
        return currentMessageDate != previousMessageDate
    }

    private fun formatMessageDate(timestamp: Long): String {
        val calendar = Calendar.getInstance().apply { timeInMillis = timestamp }
        val today = Calendar.getInstance()

        return when {
            isSameDay(calendar, today) -> "Today"
            isYesterday(calendar, today) -> "Yesterday"
            else -> SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(timestamp))
        }
    }

    private fun isSameDay(calendar1: Calendar, calendar2: Calendar): Boolean {
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)
    }

    private fun isYesterday(calendar: Calendar, today: Calendar): Boolean {
        today.add(Calendar.DAY_OF_YEAR, -1)
        return isSameDay(calendar, today)
    }

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageTextView: TextView = itemView.findViewById(R.id.messageTextView)
        val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
    }

    companion object {
        private const val VIEW_TYPE_SENT = 1
        private const val VIEW_TYPE_RECEIVED = 2
    }
}
