package fr.inria.npw

import java.io.File
import java.io.IOException
import java.util.*

class FileSaver(private val receivedPackets: MutableList<Long>) : Runnable {

    override fun run() {
        println("Enter [name of the file] to save packets:")

        val keyboard = Scanner(System.`in`)
        while (true) {
            val input = keyboard.nextLine()
            if (input != null) {
                when (input) {
                    "dump" -> dumpPackets()
                    "size" -> print("Current number of packets: ${receivedPackets.size}\n")
                    "add" -> receivedPackets.add(0)
                    else -> saveFile(input)
                }
            }
        }

    }

    private fun dumpPackets() {
        println("Dumped ${receivedPackets.size} packets")
        receivedPackets.clear()
    }

    private fun saveFile(name: String) {

        val dir = File("./files")
        dir.mkdirs()

        val file = File("./files/$name.json")

        try {

            val packets = receivedPackets.toList()

            if (packets.isEmpty()) {
                println("Couldn't create file, there are no received packets.")
            } else {

                if (file.exists()) {
                    println("Couldn't create file $name: it already exists.")
                } else {

                    file.writeText(createJsonString(packets))
                    receivedPackets.clear()

                    println("Created file files/$name.json with ${packets.size} packets.")

                }
            }

        } catch (e: IOException) {
            println("This file name is not valid.")
        }

    }

    private fun createJsonString(packets: List<Long>): String {
        var packetsToString = ""
        for (i in 0 until packets.size) {
            packetsToString += if (i == packets.size - 1) "    ${packets[i]}\n" else "    ${packets[i]},\n"
        }

        return "{\n" +
                "  \"timesReceivedInNanoseconds\": [\n" +
                "$packetsToString  ]\n" +
                "}"
    }

}