package com.vinoth.chatapplication

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vinoth.chatapplication.data.model.ChatBotModel
import com.vinoth.chatapplication.presentation.adapter.MessageAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var messageAdapter: MessageAdapter
    private val messages = mutableListOf<ChatBotModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.chatRecyclerView)
        val editTextMessage = findViewById<EditText>(R.id.messageEditText)
        val buttonSend = findViewById<ImageButton>(R.id.sendButton)
        val checkbox = findViewById<CheckBox>(R.id.checkbox)

        messageAdapter = MessageAdapter(messages)
        recyclerView.adapter = messageAdapter
        recyclerView.layoutManager = LinearLayoutManager(this).apply {
            stackFromEnd = true
        }

        buttonSend.setOnClickListener {
            val messageText = editTextMessage.text.toString()
            if (messageText.isNotBlank()) {
                val isSent = checkbox.isChecked
                val timestamp = System.currentTimeMillis()
                messages.add(ChatBotModel(messageText, isSent, timestamp))
                messageAdapter.notifyItemInserted(messages.size - 1)
                editTextMessage.text.clear()

                recyclerView.post {
                    recyclerView.scrollToPosition(messages.size - 1)
                }
            }
        }
        }
    }
