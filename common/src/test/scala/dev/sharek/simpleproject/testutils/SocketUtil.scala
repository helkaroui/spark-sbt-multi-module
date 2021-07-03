package dev.sharek.simpleproject.testutils

import java.io.{BufferedReader, BufferedWriter, InputStreamReader, OutputStreamWriter}
import java.net.{ServerSocket, Socket}

class SocketUtil(port: Int, messages: Array[String], waitBeforeClosing: Int = 5000) extends Runnable {

  var socketServer: ServerSocket = _
  var socket: Socket = _
  private var is: BufferedReader = _
  private var os: BufferedWriter = _

  override def run(): Unit = {
    startServer()
    messages.foreach(send)
    Thread.sleep(waitBeforeClosing)
    closeServer()
  }

  def startServer(): Unit = {
    socketServer = new ServerSocket(port)
    println("initialized socket server")
    println(s"socket started on address: ${socketServer.getInetAddress} port: ${socketServer.getLocalPort}")
    socket = socketServer.accept()

    is = new BufferedReader(new InputStreamReader(socket.getInputStream))
    os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream))
  }

  def closeServer(): Unit = {
    if (socket != null && !socket.isClosed) socket.close()
    println("socket client stopped")
    if (socketServer != null && !socketServer.isClosed) socketServer.close()
    println("socket server stopped")
  }

  private def send(message: String): Unit = {
    println(s"Sending: '$message'")
    os.write(message)
    os.newLine()
    os.flush()
    Thread.sleep(500)
  }
}


object SocketUtil {

  def apply(port: Int, messages: Array[String]): SocketUtil = new SocketUtil(port, messages)

}