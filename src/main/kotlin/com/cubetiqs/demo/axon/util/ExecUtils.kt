package com.cubetiqs.demo.axon.util

import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream

object ExecUtils {
    private const val MYSQLDUMP_FILE = "mysqldump"

    fun execMySqlDump(): ByteArray? {
        var results: ByteArray?
        try {
            val command: MutableList<String> = mutableListOf()
            command.add(MYSQLDUMP_FILE)
            command.add("--databases")
            command.add("orderwebapp")
            command.add("--host")
            command.add("192.168.0.204")
            command.add("-usombochea")
            command.add("-p@Csb632612")
            
            val builder = ProcessBuilder(*command.toTypedArray())
                .redirectErrorStream(false)
            val process = builder.start()
            BufferedInputStream(process.inputStream).use {
                ByteArrayOutputStream().use { stdout ->
                    while (true) {
                        val x = it.read()
                        if (x == -1) {
                            break
                        }
                        stdout.write(x)
                    }
                    results = stdout.toByteArray()
                    process.waitFor()
                }
            }
        } catch (e: Exception) {
            println(e.message)
            return null
        }
        return results
    }
}