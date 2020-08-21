package com.cubetiqs.demo.axon

import com.cubetiqs.demo.axon.util.ExecUtils
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.MediaType
import org.springframework.util.FileCopyUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@SpringBootApplication
class AxonApplication

fun main(args: Array<String>) {
    runApplication<AxonApplication>(*args)
}

@RestController
class MyDumper {
    @GetMapping("/dump")
    fun dumper(
        response: HttpServletResponse
    ) {
        response.contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE
        val data = ExecUtils.execMySqlDump()
        FileCopyUtils.copy(data ?: ByteArray(0), response.outputStream)
    }
}