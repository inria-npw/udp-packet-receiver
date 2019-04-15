package fr.inria.npw

import java.io.IOException
import java.net.BindException
import java.net.DatagramPacket
import java.net.DatagramSocket

const val PORT_NUMBER = 5020

const val DATA_BUFFER_SIZE = 2500

fun main(args: Array<String>) {

    val socket = try {
        DatagramSocket(PORT_NUMBER)
    } catch (e: BindException) {
        println("Trying to open a socket on an already used port... Terminating test connection.")
        return
    }

    println("The connection was successfully started.")

    val dataBuffer = ByteArray(DATA_BUFFER_SIZE)
    val receivedPacket = DatagramPacket(dataBuffer, dataBuffer.size)

    try {

        val receivedPackets = mutableListOf<Long>()
        val saveFileThread = Thread(FileSaver(receivedPackets))
        saveFileThread.start()

        while (true) {
            socket.receive(receivedPacket)
            receivedPackets.add(System.nanoTime())
        }

    } catch (e: IOException) {
        println("An exception was raised during the reception of packets.")
    } finally {
        println("Closing the test connection.")
        socket.close()
    }
}

