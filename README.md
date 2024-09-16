
# Android Chat Application

This repository contains an Android chat application with a clear and simple UI, leveraging a `RecyclerView` adapter for efficient message display.

## Features
- **Real-time Messaging**: Supports sending and receiving messages, with distinct layouts for sent and received messages.
- **Message Timestamps**: Displays message time in the `hh:mm a` format.
- **RecyclerView for Messages**: Efficient scrolling and display of chat history using a `RecyclerView` adapter.
- **Fragment-based Design**: Built with Android Fragments for modular UI and lifecycle management.
- **Profile Picture**: Allows users to select and set their profile picture from local storage.

## Tech Stack
- **Language**: Kotlin
- **UI Components**: `RecyclerView`, `ConstraintLayout`, `ImageView`
- **Data Handling**: `View Binding` for clean UI interaction
- **Architecture**: MVVM pattern for modular and scalable code
- **Message Handling**: Custom `ChatBotModel` for representing chat messages
- **Time Formatting**: Custom formatting for message timestamps

## Setup
1. Clone the repository:
   ```bash
   git clone [https://github.com/your-repository-url](https://github.com/Vinoth-Android/Chat-App/)
   ```
2. Open the project in Android Studio.
3. Sync Gradle files and build the project.

## UI Preview
- **Sent Messages**: Messages sent by the user are displayed on the right side.
- **Received Messages**: Incoming messages are displayed on the left side.
- **Profile Picture**: Click the profile picture to select an image from local storage.

## How It Works
- **MessageAdapter**: Handles the binding of chat messages to the `RecyclerView` based on message type (sent/received).
- **ChatFragment**: Manages the chat interface and interacts with the adapter to update messages in real-time.
- **Profile Selection**: Users can update their profile picture by selecting a local image through the `ImageView`.

---
